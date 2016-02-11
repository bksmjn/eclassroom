/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs544.myblog.web.utils;

/**
 *
 * @author bikesh
 */
public class Identity {

    public static final String SESSION_KEY = "myblog.security";
    private final String username;

    public static Identity createDefaultInstacne() {
        return new Identity("");
    }

    public Identity(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }
    
    

}
