package com.project.my.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.my.module.sns.service.UserLoginService;
import com.project.my.module.userRole.entity.UserInfoEntity;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final UserLoginService userLoginService;
    //private final UserRoleRepository

    public MyUserDetailsService(UserLoginService userLoginService) {
        
        this.userLoginService = userLoginService;
    }

    @Override
    public UserDetails loadUserByUsername(String insertedUserEmail) throws UsernameNotFoundException {
        System.out.println("로그인 중");

        UserInfoEntity userInfoEntity = userLoginService.findById(insertedUserEmail);
        
       //Optional<UserInfoEntity> findOne = userLoginService.findOne(insertedUserEmail);
        //UserInfoEntity userInfoEntity = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));
        
        System.out.println(userInfoEntity.getUserEmail());
        System.out.println(userInfoEntity.getUserPwd());
        System.out.println(userInfoEntity.getRole());

        //return new CustomUserDetails(userInfoEntity);
        return User.builder()
            .username(userInfoEntity.getUserEmail())
            .password(userInfoEntity.getUserPwd())
            .roles(userInfoEntity.getRole())
            .build();       
    }
}