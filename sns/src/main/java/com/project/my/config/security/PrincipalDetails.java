package com.project.my.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.my.module.userRole.entity.UserInfoEntity;

public class PrincipalDetails implements UserDetails{
    private UserInfoEntity userInfoEntity;

    // 일반 유저 로그인시 사용하는 생성자
    public PrincipalDetails(UserInfoEntity userInfoEntity){
        this.userInfoEntity = userInfoEntity;
    }
    // OAuth2User 를 사용한 SNS 유저 로그인 시 사용하는 생성자
    // TODO

    // 해당 유저의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> role = new ArrayList<>();	
		role.add(new GrantedAuthority() {		
			@Override
			public String getAuthority() {
				return userInfoEntity.getRole();
			}
		});
		return role;
    }

    // 해당 유저의 패스워드 리턴
    @Override
    public String getPassword() {
		return userInfoEntity.getUserPwd();
    }
    // 해당 유저의 아이디(이메일) 리턴
    @Override
    public String getUsername() {
        return userInfoEntity.getUserEmail();
    }
    // 계정 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정 잠금
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 계정 정보 변경
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정 활성화
    @Override
    public boolean isEnabled() {
        return true;
    }    
}
