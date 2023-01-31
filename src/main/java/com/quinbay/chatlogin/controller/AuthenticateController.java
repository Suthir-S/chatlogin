package com.quinbay.chatlogin.controller;

import com.quinbay.chatlogin.model.GroupMembers;
import com.quinbay.chatlogin.model.Message;
import com.quinbay.chatlogin.model.UserDetails;
import com.quinbay.chatlogin.repository.UserDetailsRepo;
import com.quinbay.chatlogin.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthenticateController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    UserDetailsRepo userDetailsRepo;


    UserDetails loginUser = new UserDetails();
    @GetMapping("/loginUser")
    public Map<String, Object> displayuser(@RequestParam String username ,@RequestParam String mobilenum ,@RequestParam String password ){
        loginUser = userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
        return authenticateService.displayuser(username,mobilenum,password);
    }

    @GetMapping("/showGroup")
    public Map<String,Message> getMembers(){
        GroupMembers[] members = authenticateService.getMembers();
        List<UserDetails> user = userDetailsRepo.findAll();
        Map<String,Message> grouplist = new HashMap<>();
        List<String> group = new ArrayList<>();
        for(GroupMembers g:members){
            if(g.getUserid().equals(loginUser.getMobilenum())){
                    group.add(g.getGroupid());
            }
            }
        for(int i=0;i<group.size();i++){
            Message[] grpmessage = authenticateService.getMessages();
            for(Message msg : grpmessage){
                if(group.get(i).equals(msg.getGroupid())){
                    grouplist.put(group.get(i),msg);
                }
            }
        }
        return grouplist;
        }


    @GetMapping("/logout")
    public String logoutUser(){
        loginUser.setLoginstatus("Inactive");
        userDetailsRepo.save(loginUser);
        return "Logout Successful";
    }

    }

