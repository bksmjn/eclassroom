package cs544.myblog.web.chapters.boundary;

import cs544.myblog.web.chapters.control.ChapterManager;
import cs544.myblog.web.chapters.entity.Chapter;
import cs544.myblog.web.chapters.entity.Topic;
import cs544.myblog.web.usermgmt.control.CourseFinder;
import cs544.myblog.web.utils.Messages;
import cs544.myblog.web.utils.TemplateController;
import cs544.myblog.web.utils.UIHelperUtils;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bikesh maharjan v1.2
 * 
 * 
 * This
 */
@Controller
@ViewScoped
public class ChapterAddBean implements Serializable {
    
    private Chapter chapter;
    private String courseCode;
    private Topic topic;
    @Autowired
    private CourseFinder courseFinder;
    @Autowired
    private Messages messages;
    @Autowired
    private ChapterManager chapterManager;
    @Autowired
    private TemplateController templateController;
    
    public ChapterAddBean() {
        this.topic = new Topic();
        this.chapter = new Chapter();
    }
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveButtonClickedHandler() {
        try {
            this.chapter.setUserId(this.templateController.getUserName());
            System.out.println("Inside SaveButtonClicked Handler" + this.chapter.getTopics().size());
            this.chapterManager.add(chapter);
            this.chapter = new Chapter();
            messages.addInfo(null, "Chapter Add", "Chapter Added Successfully");
            
        } catch (IllegalArgumentException ex) {
            messages.addError(null, "Chapter Add", ex.getMessage());
        } catch (AccessDeniedException e) {
            messages.addError(null, "Chapter Add", e.getMessage());
        }
    }
    
    public void addTopic() {
        this.topic.setChapter(this.chapter);
        this.chapter.getTopics().add(topic);
        this.topic = new Topic();
        
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        this.topic.setFile(event.getFile().getContents());
    }
    
    public SelectItem[] getAllCourses() {
        return UIHelperUtils.toArrayOfSelectItem(this.courseFinder.findAll());
    }
    
    public Chapter getChapter() {
        return chapter;
    }
    
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
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
    
    public Topic getTopic() {
        return topic;
    }
    
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    
    public Messages getMessages() {
        return messages;
    }
    
    public void setMessages(Messages messages) {
        this.messages = messages;
    }
    
    public ChapterManager getChapterManager() {
        return chapterManager;
    }
    
    public void setChapterManager(ChapterManager chapterManager) {
        this.chapterManager = chapterManager;
    }
    
}
