package cs544.myblog.web.chapters.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bikesh
 */
@Entity
@Table(name = "chapters")
@NamedQueries({
    @NamedQuery(name = Chapter.FIND_ALL, query = "SELECT c FROM Chapter c"),
    @NamedQuery(name = Chapter.FIND_BY_CHAPTER_ID, query = "SELECT c FROM Chapter c WHERE c.id=:id"),
    @NamedQuery(name = Chapter.FIND_BY_COURSE_ID, query = "SELECT c FROM Chapter c where c.courseCode=:courseCode")
})
public class Chapter implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Column(name = "title")
    private String title;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "user_id")
    private String userId;
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Topic> topics;

    private static final String DOMAIN_PREFIX = "cs544.myblog.web.chapters.entity.Chapter";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_CHAPTER_ID = DOMAIN_PREFIX + "FIND_BY_CHAPTER_ID";
    public static final String FIND_BY_COURSE_ID = DOMAIN_PREFIX + "FIND_BY_COURSE_ID";

    public Chapter() {
        this.topics = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
