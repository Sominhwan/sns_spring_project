package com.project.my.module.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDTO { 
    private String attachFile;
    private String email;
    private String title;
    private String sendTime;
    
    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    } 
}
