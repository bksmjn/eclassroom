/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.usermgmt.control.CourseManager;
import cs544.myblog.web.usermgmt.entity.Course;
import cs544.myblog.web.utils.Messages;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FaRiii
 */
@Controller
@ViewScoped
public class CourseAddBean implements Serializable {

    private Course course;

    @Autowired
    private CourseManager courseManager;
    @Autowired
    Messages message;

    public CourseAddBean() {

        this.course = new Course();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveButtonClickedHandler() {

        try {
            this.courseManager.addCourse(course);
            message.addInfo(null, "Course", "Course Added Successfully");
            this.course = new Course();
        } catch (IllegalArgumentException ex) {
            message.addError(null, "Course", ex.getMessage());
        } catch (AccessDeniedException e) {
            message.addError(null, "Chapter Add", e.getMessage());
        }
    }

    public CourseManager getCourseManager() {
        return courseManager;
    }

    public void setCourseManager(CourseManager courseManager) {
        this.courseManager = courseManager;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }

}
