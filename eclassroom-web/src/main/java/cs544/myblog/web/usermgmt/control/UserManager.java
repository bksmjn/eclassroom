package cs544.myblog.web.usermgmt.control;

import cs544.myblog.web.usermgmt.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *@author FaRiii
 */
@Repository
public class UserManager {

    @Autowired
    private SessionFactory sessionFactory;

    public UserManager() {
        System.out.println("UserManager");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addUser(User user) {
        try {
            sessionFactory.getCurrentSession().merge(user);
            this.sessionFactory.getCurrentSession().flush();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
