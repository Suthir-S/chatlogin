package com.quinbay.chatlogin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Authenticate {
    int id;
    String username;
    String phonenum;
    String password;
}
