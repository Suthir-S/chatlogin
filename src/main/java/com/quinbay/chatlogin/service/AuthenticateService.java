package com.quinbay.chatlogin.service;


import com.quinbay.chatlogin.apis.AuthenticateInterface;
import com.quinbay.chatlogin.model.GroupMembers;
import com.quinbay.chatlogin.model.Message;
import com.quinbay.chatlogin.model.UserDetails;
import com.quinbay.chatlogin.repository.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticateService implements AuthenticateInterface {

    @Autowired
    UserDetailsRepo userDetailsRepo;

    @Autowired
    RestTemplate restTemplate;

//    @Override
//    public UserDetails findUsername(String username , String mobilenum , String password ){
//        UserDetails repo = null;
//        repo = userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
//        if(repo != null){
//            repo.setLoginstatus("Login Successful");
//            userDetailsRepo.save(repo);
//            return repo;
//        }
//        repo.setLoginstatus("Invalid Credentials");
//        userDetailsRepo.save(repo);
//        return repo;
//    }

    Map<String, Object> addUser = new HashMap<>();
    @Override
    public Map<String, Object> displayuser(String username , String mobilenum , String password ){
        UserDetails repo = null;
        repo = userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
        if(repo != null){
            UserDetails user = userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
            user.setLoginstatus("Active");
            userDetailsRepo.save(user);
            addUser.put("loginstatus",true);
            addUser.put("userid", user.getId());
            addUser.put("mobilenum",user.getMobilenum());
            addUser.put("about",user.getAbout());
            addUser.put("username",user.getUsername());
            addUser.put("timestamp",user.getCreatedon());
            addUser.put("updatedtimestamp",user.getUpdatedon());
            return addUser;
        }
        Map<String, Object> empUser = new HashMap<>();
        UserDetails user = userDetailsRepo.findByUsernameAndMobilenumAndPassword(username,mobilenum,password);
        empUser.put("loginstatus",false);
        return empUser;
    }


    public GroupMembers[] getMembers(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<GroupMembers> entity = new HttpEntity<GroupMembers>(headers);
        return  restTemplate.exchange("http://10.30.1.7:8085/displayGroupMembers",HttpMethod.GET,entity,GroupMembers[].class).getBody();
    }

    public Message[] getMessages(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Message> entity = new HttpEntity<Message>(headers);
        return  restTemplate.exchange("http://10.30.1.7:8085/displayGroupMessages",HttpMethod.GET,entity,Message[].class).getBody();
    }



}
