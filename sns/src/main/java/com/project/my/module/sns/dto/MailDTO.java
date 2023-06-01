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
    private int sendmail_idx;
    private String attachFile;
    private String fileName;
    private String email;
    private String title;
    private String content;
    private String sendTime;
    
    public void setSendmail_idx(int sendmail_idx) {
        this.sendmail_idx = sendmail_idx;
    }
    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
