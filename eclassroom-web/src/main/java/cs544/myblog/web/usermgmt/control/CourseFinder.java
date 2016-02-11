/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.usermgmt.control;

import cs544.myblog.web.usermgmt.entity.Course;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bikesh
 */
@Repository
public class CourseFinder {

    @Autowired
    private SessionFactory sessionFactory;

    public CourseFinder() {
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findAll() {
        try {
            return sessionFactory.getCurrentSession().getNamedQuery(Course.FIND_ALL).list();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Course findByCourseCode(String courseCode) {
        try {
            return (Course) sessionFactory.getCurrentSession().getNamedQuery(Course.FIND_BY_COURSE_CODE).setParameter("code", courseCode).uniqueResult();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
