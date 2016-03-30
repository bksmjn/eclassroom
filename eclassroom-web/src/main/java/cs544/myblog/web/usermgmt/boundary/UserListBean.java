package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.usermgmt.control.UserFinder;
import cs544.myblog.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FaRiii
 */
@Controller
@ViewScoped
@Lazy
@Scope("prototype")
public class UserListBean implements Serializable {

    private List<User> users;
    private String emailAddress;
    private String roleId;

    @Autowired
    private UserFinder userFinder;

    public UserListBean() {

    }

    @PostConstruct
    public void init() {
        this.users = this.userFinder.findAll();
        System.out.println("USERLIST size=" + this.users.size());
    }

    public void searchButtonClickedHandler() {
        this.users = new ArrayList<User>();
        this.users.add(this.userFinder.findUserByEmailAddress(emailAddress));
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UserFinder getUserFinder() {
        return userFinder;
    }

    public void setUserFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
