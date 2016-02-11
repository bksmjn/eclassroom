package cs544.myblog.web;

import cs544.myblog.web.usermgmt.control.UserManager;
import cs544.myblog.web.usermgmt.entity.Role;
import cs544.myblog.web.usermgmt.entity.User;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bikesh
 */
public class StartUpBean {

    @Autowired
    private UserManager usermanager;
    private static String userName;
    private static String role;

    public StartUpBean() {

    }

    @PostConstruct
    public void init() {
        User u = new User();
        u.setEmailAddress("abc@gmail.com");
        u.setUserName("bksmjn");
        u.setPassword("12345");
        u.setRole(Role.ROLE_ADMIN.toString());
        usermanager.addUser(u);
    }

    public UserManager getUsermanager() {
        return usermanager;
    }

    public void setUsermanager(UserManager usermanager) {
        this.usermanager = usermanager;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        StartUpBean.userName = userName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        StartUpBean.role = role;
    }
    
    
    

}
