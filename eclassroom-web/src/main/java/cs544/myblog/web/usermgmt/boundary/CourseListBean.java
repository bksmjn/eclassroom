package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.usermgmt.control.CourseFinder;
import cs544.myblog.web.usermgmt.entity.Course;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FaRiii
 */
@Controller
@ViewScoped
@Lazy
@Scope("prototype")
public class CourseListBean implements Serializable {

    private String courseCode;
    private List<Course> courses;

    @Autowired
    private CourseFinder courseFinder;

    public CourseListBean() {

    }

    @PostConstruct
    public void init() {
        this.courses = this.courseFinder.findAll();
        System.out.println("SIZE" + this.courses.size());
    }

    public void searchButtonClickedHandler() {
        System.out.println("inside courselistbean" + this.courses.size());
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public CourseFinder getCourseFinder() {
        return courseFinder;
    }

    public void setCourseFinder(CourseFinder courseFinder) {
        this.courseFinder = courseFinder;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
