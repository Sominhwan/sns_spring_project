package com.project.my.module.sns.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.annotation.Validated;

import com.project.my.module.sns.dto.AuthDTO;
import com.project.my.module.sns.dto.MainDTO;
import com.project.my.module.sns.service.MainService;
import com.project.my.module.userRole.entity.CommentEntity;
import com.project.my.module.userRole.entity.PostEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.PostRepository;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainControllerApiV1 {
    private final MainService mainService;

    @GetMapping("/getListPMember")
    @ResponseBody
    public List<UserInfoEntity> getListPMember(@RequestParam("userEmail") String userEmail) {
        return mainService.getListPMember(userEmail);
    }

    @GetMapping("/friendCheck")
    @ResponseBody
    public boolean friendCheck(@RequestParam("userEmail") String userEmail, @RequestParam("friendEmail") String friendEmail){
        return mainService.friendCheck(userEmail,friendEmail);
    }
    @GetMapping("/replycheck")
    @ResponseBody
    public boolean replycheck(@RequestParam("commentId") int commentId){
      System.out.println("코멘트아이디:"+commentId);
      return mainService.replycheck(commentId);
    }

    @GetMapping("/plist")
    @ResponseBody
    public List<PostEntity> plist(){
        return mainService.plist();
    }
    @GetMapping("/plist2")
    @ResponseBody
    public List<PostEntity> plist2(@RequestParam("postId") int postId){
        return mainService.plist2(postId);
    }

    @GetMapping("/getPMember")
    @ResponseBody
    public List<UserInfoEntity> getPMember(@RequestParam("userEmail") String userEmail){
        return mainService.getPMember(userEmail); 
    }
    
    @GetMapping("/listPReply")
    @ResponseBody
    public List<CommentEntity> listPReply(@RequestParam("postId") int postId){
        System.out.println("게시물번호:"+postId);
        return mainService.listPReply(postId);
    }

    @GetMapping("/postLike")
    @ResponseBody
    public boolean postLike(@RequestParam("userEmail") String userEmail, @RequestParam("postId") int postId){
        return mainService.postLike(userEmail, postId);
    }

    

     // 댓글입력하기
     @PostMapping("/commentAdd")
     @ResponseBody 
     public List<CommentEntity> commentAdd(@RequestParam("userEmail") String userEmail, @RequestParam("commentDetail") String commentDetail,@RequestParam("postId") int postId) {
        mainService.upcomment(postId);       
        return mainService.commentAdd(userEmail,commentDetail,postId);
     }
     //답글달기
     @PostMapping("/insertReply")
     @ResponseBody 
     public List<CommentEntity> insertReply(@RequestParam("postId") int postId,@RequestParam("userEmail") String userEmail, @RequestParam("commentDetail") String commentDetail, @RequestParam("commentParrent") int commentParrent) {
        mainService.upcomment(postId);    
        return mainService.insertReply(postId,userEmail,commentDetail, commentParrent);
     }
     
     //댓글삭제
     @PostMapping("/commentdel")
     @ResponseBody
     public void commentdel(@RequestParam("postId") int postId, @RequestParam("commentId") int commentId){
        mainService.minuscomment(postId);
        mainService.commentdel(commentId);
     }

     //댓글수정하기
     @PostMapping("/commentupdate")
     @ResponseBody
     public void commentupdate(@RequestParam("commentId") int commentId, @RequestParam("commentDetail") String commentDetail){
        mainService.commentupdate(commentId, commentDetail);
     }

     //하트하기
     @PostMapping("/heartup")
     @ResponseBody
     public void heartup(@RequestParam("postId") int postId, @RequestParam("userEmail") String userEmail){
        mainService.heartup(postId, userEmail);
     }

     //하트삭제하기
     @PostMapping("/heartdel")
     @ResponseBody
     public void heartdel(@RequestParam("postId") int postId, @RequestParam("userEmail") String userEmail){
        mainService.heartdel(postId, userEmail);
     }
     
     //친구하기
     @PostMapping("/followfirend")
     @ResponseBody
     public void followfirend(@RequestParam("userEmail") String userEmail, @RequestParam("friendEmail") String friendEmail){

        mainService.followfirend(userEmail, friendEmail);
     }
   //친구삭제
   @PostMapping("/delFriend")
   @ResponseBody
   public void delFriend(@RequestParam("userEmail") String userEmail, @RequestParam("friendEmail") String friendEmail){

      mainService.delFriend(userEmail, friendEmail);
   }
     
   @PostMapping("/insertPost")
   @ResponseBody
   public void insertPost(@RequestParam("userEmail") String userEmail, @RequestParam("imageName") MultipartFile imageFile){
      mainService.insertPost(userEmail,imageFile);
   }
   @PostMapping("/insertVideo")
   @ResponseBody
   public void insertVideo(@RequestParam("userEmail") String userEmail, @RequestParam("videoFile") MultipartFile videoFile){
      System.out.println(userEmail);
      mainService.insertVideo(userEmail,videoFile);
   }
}