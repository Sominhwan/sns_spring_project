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
public class MailFileLogEntity {
    private int idx;
    private int sendmail_idx;
    private int mailfile_idx;

    public void setIdx(int idx) {
        this.idx = idx;
    }
    public void setSendmail_idx(int sendmail_idx) {
        this.sendmail_idx = sendmail_idx;
    }
    public void setMailfile_idx(int mailfile_idx) {
        this.mailfile_idx = mailfile_idx;
    }    
}
