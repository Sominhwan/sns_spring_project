package com.project.my.module.sns.dto;

import com.project.my.module.userRole.entity.SMSEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SMSDTO {
    private String userPN;

    public void setUserPN(String userPN) {
        this.userPN = userPN;
    }  
    
    // 아이디 찾기 데이터 저장
    @Data
    @Builder
    public static class setSmsData{
        private String userPN;
        private String content;
        private String userRegTime;

        public SMSEntity smsEntity() {
            return SMSEntity.builder()
                    .userPN(userPN)
                    .content(content)
                    .build();         
        }
    }       
}
