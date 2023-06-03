package com.project.my.module.sns.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.my.module.sns.dto.MailDTO;
import com.project.my.module.sns.dto.PostDTO;
import com.project.my.module.sns.service.AdminServiceAp1V1;
import com.project.my.util.Gmail.GmailService;
import com.project.my.util.HighChart.HighChartService;
import com.project.my.util.S3.AwsS3Service;
import com.project.my.util.S3.MailLogService;
import com.project.my.util.SMS.SmsCountService;
import com.project.my.util.SMS.SmsSendService;

import lombok.RequiredArgsConstructor;

@RestController // 비동기기 데이터 처리후 데이터 반환
@RequiredArgsConstructor
public class AdminControllerApiV1 {
    private final AdminServiceAp1V1 adminServiceAp1V1;
    private final AwsS3Service awsS3Service;
    private final GmailService gmailService;
    private final MailLogService mailLogService;
    private final SmsCountService countService;
    private final SmsSendService sendService;
    private final HighChartService chartService;
        
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
    public Map userAdEmailSend(@Param("titleInput") String titleInput, @Param("userAllEmail") String []userAllEmail, @Param("mailContent") String mailContent,  @RequestPart(value = "files", required = false) List<MultipartFile> files){       
        Map result = new HashMap<String, Object>();
  
        if(files == null){
            List<String> fileEmptyList = new ArrayList<>();
            List<String> fileNameEmptyList = new ArrayList<>();
            fileEmptyList.add("-");
            fileNameEmptyList.add("-");
            boolean flag = gmailService.sendMutlEmail(userAllEmail, titleInput, mailContent, files);
            if(flag) {
                mailLogService.saveSendMailLog(userAllEmail, titleInput, mailContent, fileEmptyList, fileNameEmptyList);
                result.put("result", "전송완료");
            } else{
                result.put("result", "전송실패");
            }
        } else{
            List<String> fileNameList = new ArrayList<>();
            files.forEach(file -> {
                String fileName = file.getOriginalFilename();
                fileNameList.add(fileName);
              });
            List<String> fileUrl =  awsS3Service.uploadFile(files);
            boolean flag = gmailService.sendMutlEmail(userAllEmail, titleInput, mailContent, files);
            if(flag) {
                mailLogService.saveSendMailLog(userAllEmail, titleInput, mailContent, fileUrl, fileNameList);
                result.put("result", "전송완료");
            } else{
                result.put("result", "전송실패");
            }
        }
        return result;
    }   
    
    // 보낸 메일함 데이터 가져오기
    @PostMapping("/admin/getSentMailData")
    @ResponseBody 
    public List<?> getSentMailData() {      
        List<MailDTO> mailList = adminServiceAp1V1.getSentMailData();
        return mailList;
    } 

    // 보낸 메일함 특정 데이터 가져오기
    @PostMapping("/admin/getSentMailDetailData")
    @ResponseBody 
    public List<?> getSentMailDetailData(@Param("num") int num) {      
        List<MailDTO> mailList = adminServiceAp1V1.getSelectMailData(num);
        return mailList;
    }   

    // 메일함 첨부파일 다운로드
    @GetMapping("/admin/downloadFile")
    @ResponseBody 
    public ResponseEntity<UrlResource> downloadFile(@Param("num") int num, @Param("file") String file) {      
        return awsS3Service.downloadImage(file);
    }
    
    // 광고수신 유저 휴대폰 리스트 가져오기
    @PostMapping("/admin/userPhoneList")
    @ResponseBody 
    public List<?> userPhoneList() {  
        return adminServiceAp1V1.getUserPhoneList();    
    }  
    
    // 카페24 SMS 잔여 문자량 가져오기
    @GetMapping("/admin/mySMSCount")
    @ResponseBody 
    public String mySMSCount() {  
        try {
            return countService.getCount();
        } catch (Exception e) {
            return "실패";
        }   
    } 
    
    // 카페24 SMS 문자 보내기
    @PostMapping("/admin/smsSend")
    @ResponseBody 
    public Map smsSend(@Param("msg") String msg, @Param("rphone") String rphone, @Param("action") String action, @Param("sphone1") String sphone1,  @Param("sphone2") String sphone2,  @Param("sphone3") String sphone3) {  
        //Map result = new HashMap<String, Object>();
        return sendService.sendSMS(msg, rphone, action, sphone1, sphone2, sphone3);
    }  
    
    // 카페24 SMS 문자 전송내역 가져오기
    @PostMapping("/admin/getSmsData")
    @ResponseBody 
    public List<?> getSmsData() {  
        return adminServiceAp1V1.getSmsData();
    }   
    
    // HighChart 유저 회원가입수 가져오기
    @GetMapping("/admin/userInfoCount")
    @ResponseBody 
    public List<?> userInfoCount() {  
        return chartService.getUserCount();
    }  

    // HighChart 유저 게시물수 가져오기
    @GetMapping("/admin/userPostCount")
    @ResponseBody 
    public List<?> userPostCount() {  
        return chartService.getUserPostCount();
    }   
    
    // HighChart 상위 5개 좋아요 수 가져오기
    @GetMapping("/admin/postInfoCount")
    @ResponseBody 
    public List<PostDTO> postInfoCount() {  
        return chartService.getPostInfoCount();
    }        
}
