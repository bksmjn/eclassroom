package cs544.myblog.web.usermgmt.boundary;

import cs544.myblog.web.StartUpBean;
import cs544.myblog.web.usermgmt.control.UserFinder;
import cs544.myblog.web.usermgmt.control.UserManager;
import cs544.myblog.web.usermgmt.entity.Role;
import cs544.myblog.web.usermgmt.entity.User;
import cs544.myblog.web.utils.HttpUtils;
import cs544.myblog.web.utils.Identity;
import cs544.myblog.web.utils.Messages;
import cs544.myblog.web.utils.SpringUtils;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author bikesh
 */
@Component
@Scope("singleton")
public class CustomAuthenticator implements AuthenticationProvider {
    
    @Autowired
    UserFinder userFinder;
    @Autowired
    UserManager userManager;
    @Autowired
    Messages messages;
    
    public CustomAuthenticator() {
        System.out.println("Inside CustomAuthenticator");
    }
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        StartUpBean.setUserName(a.getName());
        System.out.println("Inside Authentication=" + StartUpBean.getUserName());
        String email = a.getName();
        String password = a.getCredentials().toString();
        User user = userFinder.findUserByEmailAddress(email);
        if (user == null) {
            return null;
            
        }
        if (email.equals(user.getEmailAddress()) && password.equals(user.getPassword())) {
            User u = this.userFinder.findUserByEmailAddress(email);
            StartUpBean.setRole(u.getRole());
            List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
            grantedAuth.add(new SimpleGrantedAuthority(u.getRole()));
            Authentication auth = new UsernamePasswordAuthenticationToken(email, password, grantedAuth);
            return auth;
            
        }
        return null;
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    public UserFinder getUserFinder() {
        return userFinder;
    }
    
    public void setUserFinder(UserFinder userFinder) {
        this.userFinder = userFinder;
    }
    
    public UserManager getUserManager() {
        return userManager;
    }
    
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
    
    public Messages getMessages() {
        return messages;
    }
    
    public void setMessages(Messages messages) {
        this.messages = messages;
    }
    
}
