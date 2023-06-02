package com.project.my.module.sns.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
}
