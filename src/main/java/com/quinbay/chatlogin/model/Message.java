package com.quinbay.chatlogin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    int messageid;
    String senderid;
    String groupid;
    String messagetext;
    Date timestamp;
}
