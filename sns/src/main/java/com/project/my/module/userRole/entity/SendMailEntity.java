package com.project.my.module.userRole.entity;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class SendMailEntity {
    private int sendmail_idx;
    private String email;
    private String title;
    private String content;
    private String sendTime;

    public void setIdx(int sendmail_idx) {
        this.sendmail_idx = sendmail_idx;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    } 
}
