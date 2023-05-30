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
public class MailFileEntity {
    private int mailfile_idx;
    private String file_url;

    public void setIdx(int mailfile_idx) {
        this.mailfile_idx = mailfile_idx;
    }
    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }    
}
