package com.quinbay.chatlogin.apis;

import com.quinbay.chatlogin.model.UserDetails;

public interface UserDetailsInterface {
    UserDetails findUsername(String username , String mobilenum , String password );
}
