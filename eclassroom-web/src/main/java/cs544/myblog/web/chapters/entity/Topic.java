package cs544.myblog.web.chapters.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bikesh
 */
@Entity
@Table(name = "topics")
@NamedQueries({
    @NamedQuery(name = Topic.FIND_ALL, query = "SELECT t FROM Topic t"),
    @NamedQuery(name = Topic.FIND_BY_TOPIC_ID, query = "SELECT t FROM Topic t WHERE t.topicId=:topicId"),
    @NamedQuery(name = Topic.FIND_BY_CHAPTER_ID, query = "SELECT t FROM Topic t WHERE t.chapter.id=:chapterId")

})
public class Topic implements Serializable {

    @Id
    @GeneratedValue
    private int topicId;
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    private String description;
    @ManyToOne
    private Chapter chapter;
    @Lob
    private byte[] file;

    private static final String DOMAIN_PREFIX = "cs544.myblog.web.chapters.entity.Topic";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_TOPIC_ID = DOMAIN_PREFIX + "FIND_BY_TOPIC_ID";
    public static final String FIND_BY_CHAPTER_ID = DOMAIN_PREFIX + "FIND_BY_CHAPTER_ID";

    public Topic() {
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}
