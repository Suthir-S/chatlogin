package com.quinbay.chatlogin.repository;


import com.quinbay.chatlogin.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails,Integer> {
    //UserDetails findByUsernameAndMobilenum(String username,String mobilenum);
    UserDetails findById(int userid);
    UserDetails findByUsernameAndMobilenumAndPassword(String username,String mobilenum,String password);
}
