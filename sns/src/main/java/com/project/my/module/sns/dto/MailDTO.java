package com.project.my.module.sns.dto;

import com.project.my.module.userRole.entity.MailFileEntity;
import com.project.my.module.userRole.entity.SendMailEntity;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class MailDTO { 
    // 발송내역 저장
    @Data
    @Builder
    public static class SaveSendMail{
        private int idx;
        private String email;
        private String title;
        private String content;
        private String sendTime; 

        public SendMailEntity sendMailEntity(){
            return SendMailEntity.builder()
                    //.idx(idx)
                    .build();
        }       
    }
    // 첨부파일 내역 저장
    @Data
    @Builder
    public static class SaveMailFile{
        private String file_url;


        public MailFileEntity mailFileEntity(){
            return MailFileEntity.builder()
                    .file_url(file_url)
                    .build();
        }       
    }    
    // 모든 첨부파일 내역 저장
    // @Data
    // @Builder
    // public static class SaveMailFileLog{
    //     private String file_url;


    //     public MailFileEntity mailFileEntity(){
    //         return MailFileEntity.builder()
    //                 .file_url(file_url)
    //                 .build();
    //     }       
    // }  
}
