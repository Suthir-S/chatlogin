package com.quinbay.chatlogin.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;
import sun.util.calendar.BaseCalendar;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    public UserDetails(String username, String mobilenum, String password, String about, MultipartFile file) throws IOException {
        this.username = username;
        this.mobilenum = mobilenum;
        this.password = password;
        this.about = about;
        this.filedata = file.getBytes();
        this.filename=file.getOriginalFilename();
        this.filetype=file.getContentType();
    }

    @Column(name = "username")
    String username;
    @Column(name = "mobilenum")
    String mobilenum;
    @JsonIgnore
    @Column(name = "password")
    String password;


//    @Column(name = "filepath")
//    String filepath;

    @Column(name = "filename")
    String filename;
    @Column(name = "filetype")
    String filetype;
    //@Lob
    @Column(name = "filedata")
    byte[] filedata;

    @Column(name = "about")
    String about;
    @Column(name = "createdon")
    @CreationTimestamp
    LocalDateTime createdon;
    @Column(name = "updatedon")
    @UpdateTimestamp
    LocalDateTime updatedon;
    @Column(name = "loginstatus")
    String loginstatus;
//    @Column(name = "createdon")
//    @Temporal(TemporalType.DATE)
//    Date createdon;
//    @Temporal(TemporalType.TIME)
//    Date publicationTime;
}
