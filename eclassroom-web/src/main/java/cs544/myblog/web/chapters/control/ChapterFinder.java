/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.chapters.control;

import cs544.myblog.web.chapters.entity.Chapter;
import cs544.myblog.web.chapters.entity.Topic;
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
public class ChapterFinder {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Chapter> findAll() {
        return sessionFactory.getCurrentSession().getNamedQuery(Chapter.FIND_ALL).list();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Chapter findById(int id) {
        return (Chapter) sessionFactory.getCurrentSession().getNamedQuery(Chapter.FIND_BY_CHAPTER_ID).setParameter("id", id).uniqueResult();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Chapter> findChapterByCourseId(String courseId) {
        return sessionFactory.getCurrentSession().getNamedQuery(Chapter.FIND_BY_COURSE_ID).setParameter("courseCode", courseId).list();
    }

    public List<Topic> findTopicByChapterId(int chapterId) {
        return sessionFactory.getCurrentSession().getNamedQuery(Topic.FIND_BY_CHAPTER_ID).setParameter("chapterId", chapterId).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
