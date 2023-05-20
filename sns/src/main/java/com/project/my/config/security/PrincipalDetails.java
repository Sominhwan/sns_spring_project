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
				return "ROLE_" + userInfoEntity.getRole();
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
    // 해당 유저 이름 리턴
    public String getName(){
        return userInfoEntity.getUserName();
    }
    // 해당 유저 성별 리턴
    public String getUserGender(){
        return userInfoEntity.getUserGender();
    }
    // 해당 유저 닉네임 리턴
    public String getUserNickName(){
        return userInfoEntity.getUserNickName();
    }
    // 해당 유저 휴대폰번호 리턴
    public String getUserPN(){
        return userInfoEntity.getUserPN();
    }
    // 해당 유저 학교 리턴
    public String getUserSchool(){
        return userInfoEntity.getUserSchool();
    }
    // 해당 유저 주소 리턴
    public String getUserAddress(){
        return userInfoEntity.getUserAddress();
    }
    // 해당 유저 소셜주소 리턴
    public String getUserSocial(){
        return userInfoEntity.getUserSocial();
    }
    // 해당 유저 이메일 인증확인 리턴
    public String getEmailcertification(){
        return Integer.toString(userInfoEntity.getEmailcertification());
    }
    // 해당 유저 프로필 이미지 리턴
    public String getUserImage(){
        return userInfoEntity.getUserImage();
    }
    // 해당 유저 가입날짜 리턴
    public String getUserRegDate(){
        return userInfoEntity.getUserRegDate();
    }
    // 해당 유저 회원가입 타입 리턴
    public String getUserInfoType(){
        return userInfoEntity.getUserInfoType();
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
