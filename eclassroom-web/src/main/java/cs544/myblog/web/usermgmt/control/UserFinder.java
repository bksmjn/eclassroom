package cs544.myblog.web.usermgmt.control;

import cs544.myblog.web.usermgmt.entity.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author FaRiii
 */
@Repository
public class UserFinder {

    @Autowired
    private SessionFactory sessionFactory;

    public UserFinder() {
        System.out.println("INSIDE USER FINDER");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().getNamedQuery(User.FIND_ALL).list();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User findUserByEmailAddress(String emailAddress) {
        return (User) sessionFactory.getCurrentSession().getNamedQuery(User.FIND_BY_USERNAME).setParameter("email", emailAddress).uniqueResult();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
