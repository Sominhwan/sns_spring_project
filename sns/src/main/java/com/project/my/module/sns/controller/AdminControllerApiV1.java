package com.project.my.module.sns.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.my.module.sns.service.AdminServiceAp1V1;
import com.project.my.util.Gmail.GmailService;
import com.project.my.util.S3.AwsS3Service;
import com.project.my.util.S3.MailLogService;

import lombok.RequiredArgsConstructor;

@RestController // 비동기기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AdminControllerApiV1 {
    private final AdminServiceAp1V1 adminServiceAp1V1;
    private final AwsS3Service awsS3Service;
    private final GmailService gmailService;
    private final MailLogService mailLogService;
        
    // 회원 아이디 찾기
    @PostMapping("/admin/UserSearch")
    @ResponseBody 
    public String userSearch(@Param("userName") String userName) {      
        return adminServiceAp1V1.getUserInfo(userName);
    } 
    // 회원 데이터 삭제
    @PostMapping("/admin/UserInfoDelete")
    @ResponseBody 
    public void userInfoDelete(@Param("userEmail") String userEmail, @Param("userEmailAll") String []userEmailAll) {      
        // 회원데이터 하나만 삭제
        if(userEmail != null){
            adminServiceAp1V1.deleteUserInfo(userEmail);
        }
        // 체크된 회원데이터 모두 삭제
        if(userEmailAll != null){
            for (String string : userEmailAll) {
                adminServiceAp1V1.deleteUserInfo(string);            
            }
        }
    } 
    // 게시물(이메일) 찾기
    @PostMapping("/admin/PostSearch")
    @ResponseBody 
    public String postSearch(@Param("userEmail") String userEmail) {      
        return adminServiceAp1V1.getUserPost(userEmail);
    } 
    // 게시물 데이터 삭제
    @PostMapping("/admin/PostDelete")
    @ResponseBody 
    public void postDelete(@Param("postId") String postId, @Param("postIdAll") String []postIdAll) {      
        // 게시물데이터 하나만 삭제
        if(postId != null){
            adminServiceAp1V1.deleteUserPost(postId);
        }
        // 체크된 게시물 데이터 모두 삭제
        if(postIdAll != null){
            for (String string : postIdAll) {
                adminServiceAp1V1.deleteUserPost(string);            
            }
        }
    } 
    // 주소록 이메일 검색
    @PostMapping("/admin/UserEmailSearch")
    @ResponseBody 
    public String userEmailSearch(@Param("userEmail") String userEmail) {      
        return adminServiceAp1V1.getUserEmail(userEmail);
    }    
    
    // 메일 전송하기
    @PostMapping("/admin/UserAdEmailSend")
    @ResponseBody 
    public Map userAdEmailSend(@Param("titleInput") String titleInput, @Param("userAllEmail") String []userAllEmail, @Param("mailContent") String mailContent,  @RequestPart("files") List<MultipartFile> files) throws IOException{       
        Map result = new HashMap<String, Object>();
        
        if(files.isEmpty()){
            List<String> fileEmptyList = new ArrayList<>();
            fileEmptyList.add("-");
            boolean flag = gmailService.sendMutlEmail(userAllEmail, titleInput, mailContent, files);
            if(flag) {
                mailLogService.saveSendMailLog(userAllEmail, titleInput, mailContent, fileEmptyList);
                result.put("result", "전송완료");
            } else{
                result.put("result", "전송실패");
            }
        } else{
            List<String> fileUrl =  awsS3Service.uploadFile(files);
            boolean flag = gmailService.sendMutlEmail(userAllEmail, titleInput, mailContent, files);
            if(flag) {
                mailLogService.saveSendMailLog(userAllEmail, titleInput, mailContent, fileUrl);
                result.put("result", "전송완료");
            } else{
                result.put("result", "전송실패");
            }
        }

        return result;
    }         
}
