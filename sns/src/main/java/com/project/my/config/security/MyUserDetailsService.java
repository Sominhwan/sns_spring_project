package com.project.my.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.my.module.sns.service.UserLoginService;
import com.project.my.module.userRole.entity.UserInfoEntity;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final UserLoginService userLoginService;

    public MyUserDetailsService(UserLoginService userLoginService) {      
        this.userLoginService = userLoginService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserEmail) throws UsernameNotFoundException {
        UserInfoEntity userInfoEntity = userLoginService.findById(insertedUserEmail);
        if (userInfoEntity == null) {
            throw new UsernameNotFoundException("잘못된 접근입니다.");
        }
        
        return new PrincipalDetails(userInfoEntity);     
    }
}