package com.project.my.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
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
        Optional<UserInfoEntity> findOne = userLoginService.findOne(insertedUserEmail);
        UserInfoEntity userInfoEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));
        
        return User.builder()
                .username(userInfoEntity.getUserEmail())
                .password(userInfoEntity.getUserPwd())
                .roles()
                .build();
    }
}