/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author bikesh
 */
public class SpringUtils {
        public static CustomSpringSecurityUser getPrinciple() {
        CustomSpringSecurityUser user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                user = (CustomSpringSecurityUser) principal;
            }
        }
        return user;
    }

    public static String getUserName() {
        CustomSpringSecurityUser user = getPrinciple();
        if (user != null) {
            return user.getUsername();
        }
        return "";
    }

    public static boolean isFirstTime() {
        CustomSpringSecurityUser user = getPrinciple();
        if (user != null) {
            return user.isFirstTime();
        }
        return true;
    }

    public static boolean hasUserLogIn() {
        return getPrinciple() == null ? false : true;
    }
}
