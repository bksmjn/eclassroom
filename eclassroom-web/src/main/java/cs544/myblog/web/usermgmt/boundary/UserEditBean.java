package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.usermgmt.control.UserFinder;
import cs544.myblog.web.usermgmt.entity.User;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

/**
 *
 * @author FaRiii
 */
@Controller
@ViewScoped
@Lazy
public class UserEditBean implements Serializable {

    private String emailAddress;
    private User user;

    @Autowired
    private UserFinder userFinder;

    public UserEditBean() {

    }

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        this.emailAddress = this.parameterForEdit(fc);
        this.user = this.userFinder.findUserByEmailAddress(emailAddress);
    }

    private String parameterForEdit(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("emailAddress");
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserFinder getUserFinder() {
        return userFinder;
    }

    public void setUserFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
