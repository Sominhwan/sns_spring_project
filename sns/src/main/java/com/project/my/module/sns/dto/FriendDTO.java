package com.project.my.module.sns.dto;

import com.project.my.module.userRole.repository.FriendRepository;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendDTO {
    private int idx;
    private String userEmail;
    private String friendEmail;
    private int friendSign;

    
    // 아이디 찾기 데이터 저장
    @Data
    @Builder
    public static class friendCheck{
        private final FriendRepository friendRepository;
        private int idx;
        private String userEmail;
        private String friendEmail;
        private int friendSign;
    }
}
