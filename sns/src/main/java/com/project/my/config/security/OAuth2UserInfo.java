package com.project.my.config.security;

public interface OAuth2UserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getNickName();
    String getGender();
    String getMobile();
}