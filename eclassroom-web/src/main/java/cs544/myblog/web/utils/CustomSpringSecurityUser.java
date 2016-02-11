/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.utils;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author bikesh
 */
public class CustomSpringSecurityUser extends User implements Serializable {

    private String userFirstName;
    private String userFullName;
    private boolean firstTime;
    private boolean ableToPerformTransactionFromWeb;

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, boolean firstTime) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userFirstName, String userMiddleName, String userLastName, boolean firstTime) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userFirstName = userFirstName;
        this.userFullName = userFirstName + " " + userMiddleName + " " + userLastName;
        this.firstTime = firstTime;
    }

    public CustomSpringSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userFirstName, String userMiddleName, String userLastName, boolean isOnlyFromExternalChannel, boolean firstTime) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userFirstName = userFirstName;
        this.firstTime = firstTime;
        this.userFullName = userFirstName + " " + userMiddleName + " " + userLastName;
        this.ableToPerformTransactionFromWeb = !isOnlyFromExternalChannel;

    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public boolean isAbleToPerformTransactionFromWeb() {
        return ableToPerformTransactionFromWeb;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
