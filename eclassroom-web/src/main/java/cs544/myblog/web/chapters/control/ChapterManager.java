package cs544.myblog.web.chapters.control;

import cs544.myblog.web.chapters.entity.Chapter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bikesh
 */
@Repository
public class ChapterManager {

    @Autowired
    private SessionFactory sessionFactory;

    public ChapterManager() {

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PreAuthorize("hasRole('ROLE_PROFESSOR')")
    public void add(Chapter chapter) {
        sessionFactory.getCurrentSession().persist(chapter);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
