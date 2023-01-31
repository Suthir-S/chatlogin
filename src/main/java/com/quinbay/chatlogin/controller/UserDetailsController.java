package com.quinbay.chatlogin.controller;


import com.quinbay.chatlogin.model.UserDetails;
import com.quinbay.chatlogin.repository.UserDetailsRepo;
import com.quinbay.chatlogin.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@RestController
public class UserDetailsController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserDetailsRepo userDetailsRepo;


    @PostMapping("/addUser")
    public UserDetails returnUser(@RequestParam String username, @RequestParam String mobilenum, @RequestParam String password, @RequestParam String about , @RequestParam("file") MultipartFile file) throws IOException {
        UserDetails person = new UserDetails(username,mobilenum,password,about,file);
        userDetailsRepo.save(person);
        return userDetailsService.findUsername(username,mobilenum,password);
    }

    @PutMapping("/editUser")
    public UserDetails editUser(@RequestParam int userid, @RequestParam String username ,@RequestParam String about){
        return userDetailsService.editUser(userid ,username ,about);
    }

    @GetMapping("/displayUser")
    public ArrayList<UserDetails> getAllUsers() {
        return userDetailsService.findAllUsers();
    }
}
