package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.usermgmt.control.CourseFinder;
import cs544.myblog.web.usermgmt.control.UserManager;
import cs544.myblog.web.usermgmt.entity.Course;
import cs544.myblog.web.usermgmt.entity.Role;
import cs544.myblog.web.usermgmt.entity.User;
import cs544.myblog.web.utils.Messages;
import cs544.myblog.web.utils.UIHelperUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FaRiii
 */
@Controller
@ViewScoped
public class UserAddBean implements Serializable {

    private User user;
    private Course course;
    private String courseCode = "";

    @Autowired
    private UserManager userManager;

    @Autowired
    private CourseFinder courseFinder;

    @Autowired
    private Messages messages;

    public UserAddBean() {

    }

    @PostConstruct
    public void init() {
        this.user = new User();
        this.course = new Course();
    }

    public void saveButtonClickedHandler() {
        try {
            this.userManager.addUser(user);
            messages.addInfo(null, "User", "User Added Successfully");
            this.user = new User();
            this.course = new Course();
        } catch (IllegalArgumentException ex) {
            messages.addError(null, "User", ex.getMessage());
        } catch (AccessDeniedException e) {
            messages.addError(null, "Chapter Add", e.getMessage());
        }
    }

    public void addCourseButtonClickedHandler() {
        try {
            System.out.println("course id" + courseCode);
            this.user.getCourses().add(this.courseFinder.findByCourseCode(courseCode));
        } catch (IllegalArgumentException ex) {
            messages.addError(null, "Error", ex.getMessage());
        }

    }

    public SelectItem[] getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.STUDENT);
        roles.add(Role.PROFESSOR);
        roles.add(Role.ROLE_ADMIN);
        return UIHelperUtils.toArrayOfSelectItem(roles);
    }

    public SelectItem[] getAllCourses() {
        return UIHelperUtils.toArrayOfSelectItem(this.courseFinder.findAll());
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public CourseFinder getCourseFinder() {
        return courseFinder;
    }

    public void setCourseFinder(CourseFinder courseFinder) {
        this.courseFinder = courseFinder;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

}
