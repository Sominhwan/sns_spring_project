package com.project.my.module.sns.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.my.config.security.PrincipalDetails;
import com.project.my.module.sns.dto.AuthDTO;
import com.project.my.module.sns.service.AuthServiceApiV1;
import com.project.my.module.userRole.repository.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController // 비동기기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AuthControllerApiV1 {
    private final AuthServiceApiV1 authServiceApiV1;
    private final UserRepository userRepository;
    // 로그인 성공후 해당 유저 정보
    @GetMapping("/loginOk.action")
    @ResponseBody 
    public HashMap<String, String> loginOkPage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("userEmail", principalDetails.getUsername()); // 아이디 반환
        map.put("userNickName", principalDetails.getUserNickName()); // 닉네임 반환
        map.put("userImage", principalDetails.getUserImage()); // 프로필 이미지 반환
        map.put("emailcertification", principalDetails.getEmailcertification()); // 이메일 인증 반환
        map.put("userEmailHash", principalDetails.getUserEmailHash()); // 이메일 해쉬 리턴
        map.put("userRole", principalDetails.getRole()); // 유저 권한 리턴
        return map;
    }
    // 로그아웃 
    @GetMapping("/logOut.action")
    @ResponseBody 
    public HashMap<String, String> logOutPage(@AuthenticationPrincipal PrincipalDetails principalDetails){
        HashMap<String, String> map = new HashMap<String,String>();
        //map.put("userEmail",  principalDetails.getUsername()); 
        map.put("logOutMsg", "true"); 
        return map;
    }
    // 회원가입 성공 여부
    @PostMapping("/signUpInfoCheck")
    @ResponseBody 
    public Map memberJoin(@Validated @RequestBody AuthDTO.ReqJoin reqDTO) {       
        Map result = new HashMap<String, Object>();  // 회원가입 성공 여부 메시지
        System.out.println(reqDTO.getUserEmail());
        if(userRepository.userEmailChk(reqDTO.getUserEmail()) == 1){ // 이메일 중복 검사
            result.put("error", "이미 존재하는 이메일 입니다.");
            return result;
        }
        if(reqDTO.getGender().equals("없음")){ // 성별 선택 유무 검사
            result.put("error", "성별을 선택해주세요.");
            return result;
        }
        if(userRepository.userPhoneNumChk(reqDTO.getUserPhoneNum()) == 1){ // 휴대폰 중복 검사
            result.put("error", "이미 존재하는 휴대폰번호 입니다.");
            return result;
        }

        result = authServiceApiV1.memberValidation(reqDTO); 
        return result;
    } 
    // 회원 아이디 찾기
    @PostMapping("/findUserId")
    @ResponseBody 
    public Map findUserId(@Validated @RequestBody AuthDTO.ReqFindId reqDTO) {       
        Map result = new HashMap<String, Object>();  // 아이디 찾기 성공 여부 메시지
        result = authServiceApiV1.getUserId(reqDTO);
        return result;
    } 
    // 회원 비밀번호 찾기
    @PostMapping("/findUserPwd")
    @ResponseBody 
    public Map findUserId(@Validated @RequestBody AuthDTO.ReqFindPwd reqDTO) {       
        Map result = new HashMap<String, Object>();  // 비밀번호 찾기 성공 여부 메시지
        result = authServiceApiV1.getUserPwd(reqDTO);
        return result;
    }  
    // 회원 비밀번호 변경하기
    @PostMapping("/changeUserPwd")
    @ResponseBody 
    public Map changeUserPwd(@Validated @RequestBody AuthDTO.ReqChangePwd reqDTO) {       
        Map result = new HashMap<String, Object>();  // 비밀번호 변경 성공 여부 메시지
        result = authServiceApiV1.changeUserPwd(reqDTO);
        return result;
    }        
    
}
