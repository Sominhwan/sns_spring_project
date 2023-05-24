package com.project.my.module.sns.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.project.my.config.security.KakaoUserInfo;
import com.project.my.config.security.NaverUserInfo;
import com.project.my.config.security.OAuth2UserInfo;
import com.project.my.config.security.PrincipalDetails;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        // log.info("getAttributes : {}", oAuth2User.getAttributes());
       
        OAuth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();
        if(provider.equals("naver")){
            oAuth2UserInfo = new NaverUserInfo((Map)oAuth2User.getAttributes().get("response"));
        } 
        if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes() );
        }
        String providerId = oAuth2UserInfo.getProviderId(); // 소셜 id
        String email = oAuth2UserInfo.getEmail(); // 소셜 이메일
        String name = oAuth2UserInfo.getName(); // 소셜 이름
        String nickname = oAuth2UserInfo.getNickName(); // 소셜 닉네임
        String gender = oAuth2UserInfo.getGender(); // 소셜 성별
        String mobile = oAuth2UserInfo.getMobile(); // 소셜 휴대폰 번호

        Optional<UserInfoEntity> optionalUser = userRepository.findByLoginId(providerId);
        UserInfoEntity user;

        if(!optionalUser.isPresent()){
            // 회원가입 
            user = UserInfoEntity.builder()
                    .userSocialId(providerId)
                    .userEmail(email)
                    .userName(name)
                    .userNickName(nickname)
                    .userGender(gender)
                    .userPN(mobile)
                    .userInfoType(provider)
                    .role("USER")
                    .build();
            try {
                userRepository.insertSocialMember(user);                
            } catch (DuplicateKeyException e) { // 중복 유저 데이터가 존재할 경우 
                user = UserInfoEntity.builder()
                .userEmail("false")
                .build();
                return new PrincipalDetails(user, oAuth2User.getAttributes());
            }
        } else{
            user = optionalUser.get();
        }
        return new PrincipalDetails(user, oAuth2User.getAttributes());
    }
    
}
