package com.project.my.module.sns.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MailDTO {
    private String title;
    private String userAllEmail;
    private String content;
    private List<MultipartFile> multipartFile;    
}
