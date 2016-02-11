package cs544.myblog.web.chapters.boundary;

import cs544.myblog.web.chapters.control.ChapterFinder;
import cs544.myblog.web.chapters.entity.Topic;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
@Lazy
@Scope("prototype")
public class TopicListBean implements Serializable {

    private List<Topic> topics;
    private String chapterId = "";
    @Autowired
    private ChapterFinder chapterFinder;

    public TopicListBean() {

    }

    @PostConstruct
    public void init() {

        FacesContext fc = FacesContext.getCurrentInstance();
        this.chapterId = this.parameterForEdit(fc);
        System.out.println("Inside the Topic List Bean" + this.chapterId);
        this.topics = this.chapterFinder.findTopicByChapterId(Integer.parseInt(chapterId));
    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("chapterId");
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public ChapterFinder getChapterFinder() {
        return chapterFinder;
    }

    public void setChapterFinder(ChapterFinder chapterFinder) {
        this.chapterFinder = chapterFinder;
    }

}
