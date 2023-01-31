package com.quinbay.chatlogin.service;

import com.quinbay.chatlogin.apis.UserDetailsInterface;
import com.quinbay.chatlogin.model.UserDetails;
import com.quinbay.chatlogin.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserDetailsService implements UserDetailsInterface {

    @Autowired
    UserDetailsRepo userDetailsRepo;



    public UserDetails findUsername(String username , String mobilenum , String password ){
        return userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
    }


    public UserDetails editUser(int userid ,  String username ,String about ) {
        UserDetails edituser = null;
        edituser = userDetailsRepo.findById(userid);
        edituser.setAbout(about);
        edituser.setUsername(username);
        userDetailsRepo.save(edituser);
        return edituser;
    }

    public ArrayList<UserDetails> findAllUsers() {
        return (ArrayList<UserDetails>) userDetailsRepo.findAll();
    }

}
