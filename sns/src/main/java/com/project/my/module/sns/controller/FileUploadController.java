package com.project.my.module.sns.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.my.module.sns.service.FileUploadService;
import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.GuestBookEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.FileUploadRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadRepository fileUploadRepository;  

    //private final GuestBookEntity guestBookEntity;
    private final FileUploadService fileUploadService;
  
    private String uploadPath = "C:/spring/sns_spring_project/sns/src/main/resources/static/profileImages";
    private String bgUploadPath = "C:/spring/sns_spring_project/sns/src/main/resources/static/backgroundImages";
    
    // 프로필사진 업로드
    @PostMapping("/profile-upload")
    @ResponseBody 
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("userEmail") String userEmail) {
        if (!file.isEmpty()) {
            try {
                System.out.print(userEmail + "컨트롤러진입");
                String fileName = file.getOriginalFilename();   
                String filePath = uploadPath + File.separator + fileName;
                File destFile = new File(filePath);
                file.transferTo(destFile);
                // 파일 URL 반환
                String fileUrl = "/profileImages/" + fileName;
                fileUploadRepository.updateUserImage(fileUrl, userEmail);
                return fileUrl;
            } catch (Exception e) {
                System.out.println("업로드실패");
            }
        }
        return "컨트롤러 진입실패";
    }

    // 배경사진 업로드
    @PostMapping("/profile-bgUpload")
    @ResponseBody 
    public String bgUploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("userEmail") String userEmail) {
        if (!file.isEmpty()) {
            try {
                System.out.println("컨트롤러진입");
                String fileName = file.getOriginalFilename();   
                String filePath = bgUploadPath + File.separator + fileName;
                File destFile = new File(filePath);
                file.transferTo(destFile);
                // 파일 URL 반환
                String fileUrl = "/backgroundImages/" + fileName;

                if(fileUploadRepository.selectBackImage(userEmail) == 1){
                    fileUploadRepository.updateBackImage(fileUrl, userEmail);
                    System.out.println("배경화면 업데이트");
                }else{
                    fileUploadRepository.insertBackImage(fileUrl, userEmail);
                    System.out.println("배경화면 추가");
                }
                return fileUrl;
            } catch (Exception e) {
                System.out.println("업로드실패");
            }
        }
        return "컨트롤러 진입실패";
    }

    // 배경사진 변경
    @PostMapping("/profile-bgImage")
    @ResponseBody
    public ArrayList<GuestBookEntity> bgImage(@Param("userEmail") String userEmail){
        ArrayList<GuestBookEntity> result  = fileUploadService.findBgImage(userEmail);
        return result;
    }

    // 방명록 변경
    @PostMapping("/profile-guestBook")
    @ResponseBody
    public String UploadGuestBook(@Param("gbComment") String gbComment, @Param("userEmail") String userEmail){
        if(fileUploadRepository.selectBackImage(userEmail) == 1){
            fileUploadService.updateGuestBook(gbComment, userEmail);
            System.out.println("방명록 업데이트");
            return gbComment;
        }
        else{
            fileUploadService.insertGuestBook(gbComment, userEmail);
            System.out.println("방명록 추가");  
        }
        return gbComment;
    }

    @PostMapping("/profile-friendList")
    @ResponseBody
    public ArrayList<FriendmanagerEntity> friendList(@Param("userEmail") String userEmail){
        ArrayList<FriendmanagerEntity> friendList = fileUploadService.getFriendList(userEmail);
        return friendList;
    }

    @PostMapping("/profile-friendInfo")
    @ResponseBody
    public ArrayList<UserInfoEntity> getFriendInfo(@Param("userEmail") String userEmail){
        ArrayList<UserInfoEntity> getFriendInfo = fileUploadService.getFriendInfo(userEmail);
        return getFriendInfo;
    }
}
