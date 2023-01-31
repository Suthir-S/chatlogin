package com.quinbay.chatlogin.apis;

import com.quinbay.chatlogin.model.UserDetails;

import java.util.Map;

public interface AuthenticateInterface {
    //UserDetails findUsername(String username , String mobilenum , String password );
    Map<String, Object> displayuser(String username , String mobilenum , String password );
}
