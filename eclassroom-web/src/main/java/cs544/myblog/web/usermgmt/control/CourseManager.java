package cs544.myblog.web.usermgmt.control;

import cs544.myblog.web.usermgmt.entity.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FaRiii
 */
@Repository
public class CourseManager {

    @Autowired
    private SessionFactory sessionFactory;

    public CourseManager() {
        System.out.println("CourseManager");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCourse(Course course) {
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(course);
            this.sessionFactory.getCurrentSession().flush();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
