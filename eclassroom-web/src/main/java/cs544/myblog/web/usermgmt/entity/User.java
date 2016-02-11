package cs544.myblog.web.usermgmt.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author FaRiii
 */
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = User.FIND_ALL, query = "SELECT u from User u where 1=1"),
    @NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u from User u where u.emailAddress=:email")
})
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int userId;
    @NotNull
    private String userName;
    @NotNull
    @Column(unique = true)
    private String emailAddress;
    @NotNull
    private String password;
    @NotNull
    private String role;
    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<Course> courses;
    private static final String DOMAIN_PREFIX = "cs544.myblog.web.usermgmt.entity.User";
    public static final String FIND_ALL = DOMAIN_PREFIX + "FIND_ALL";
    public static final String FIND_BY_USERNAME = DOMAIN_PREFIX + "FIND_BY_USERNAME";

    public User() {
        this.courses = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public void setCourses(Collection<Course> courses) {
        this.courses = courses;
    }

 

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
