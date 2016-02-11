
package cs544.myblog.web.chapters.boundary;

import cs544.myblog.web.chapters.control.ChapterFinder;
import cs544.myblog.web.chapters.entity.Chapter;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author bikesh
 */
@Controller
@ViewScoped
public class ChapterListBean implements Serializable {

    private List<Chapter> chapters;
    private String courseCode;

    @Autowired
    private ChapterFinder chapterFinder;

    public ChapterListBean() {

    }

    public void searchButtonClickedHandler() {
        this.chapters = this.chapterFinder.findChapterByCourseId(courseCode);
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

}
