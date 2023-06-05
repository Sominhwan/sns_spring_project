package com.project.my.module.sns.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.my.module.userRole.entity.CommentEntity;
import com.project.my.module.userRole.entity.FriendmanagerEntity;
import com.project.my.module.userRole.entity.PostEntity;
import com.project.my.module.userRole.entity.PostlikeEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.CommentRepository;
import com.project.my.module.userRole.repository.FriendRepository;
import com.project.my.module.userRole.repository.PostRepository;
import com.project.my.module.userRole.repository.PostlikeRepository;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostlikeRepository postlikeRepository;
    public List<UserInfoEntity> getListPMember(String userEmail) {
        return userRepository.getListPMember(userEmail);
    }
    @Transactional
    public boolean friendCheck(String userEmail, String friendEmail){
        boolean flag = false;

        FriendmanagerEntity friendCheck = friendRepository.friendCheck(userEmail, friendEmail);

        if(friendCheck != null && friendCheck.getFriendSign() == 1) {
            flag=true;
        }
        return flag;
    }

    public List<PostEntity> plist(){
        return postRepository.plist();
    }
    public List<PostEntity> plist2(int postId){
        return postRepository.plist2(postId);
    }

    public List<UserInfoEntity> getPMember(String userEmail){
        return userRepository.getPMember(userEmail);
    }

    public List<CommentEntity> listPReply(int postId){
        return commentRepository.listPReply(postId);
    }

    public boolean postLike(String userEmail, int postId){
        boolean flag=false;

        PostlikeEntity postLike = postlikeRepository.postLike(userEmail, postId);
        if (postLike !=null && postLike.getLikeNum()==1){
            flag=true;
        }
        return flag;


    }

    public boolean replycheck(int postId){
        boolean flag=false;

        CommentEntity replycheck = commentRepository.replycheck(postId);
        if (replycheck !=null){
            flag=true;
        }
        return flag;
    }

    //댓글달기
    @Transactional
    public List<CommentEntity> commentAdd(String userEmail,String commentDetail, int postId){
        
        return commentRepository.commentAdd(userEmail, commentDetail, postId);
    }
    //답글달기
    
    @Transactional
    public List<CommentEntity> insertReply(int postId,String userEmail,String commentDetail,int commentParrent){
        
        return commentRepository.insertReply(postId,userEmail, commentDetail, commentParrent);
    }
    //댓글달고 댓글숫자+1
    @Transactional
    public void upcomment(int postId){
        postRepository.upcomment(postId);
    }

    @Transactional
    public void commentdel(int commentId){
        commentRepository.commentdel(commentId);
    }
    @Transactional
    public void minuscomment(int postId){
        postRepository.minuscomment(postId);
    }

    @Transactional
    public void commentupdate(int commentId, String commentDetail){
        commentRepository.commentupdate(commentId, commentDetail);
    }

    //하트
    @Transactional
    public void heartup(int postId, String userEmail){
        postlikeRepository.heartup(postId, userEmail);
        postRepository.upheart(postId);
    }

    //하트삭제
    @Transactional
    public void heartdel(int postId, String userEmail){
        postlikeRepository.heartdel(postId, userEmail);
        postRepository.downheart(postId);
    }

    //팔로우하기
    @Transactional
    public void followfirend(String userEmail, String friendEmail){
        friendRepository.followfirend(userEmail, friendEmail);
        friendRepository.followfirend2(userEmail, friendEmail);
    }
    //친구삭제하기
    @Transactional
    public void delFriend(String userEmail, String friendEmail){
        friendRepository.delFriend(userEmail, friendEmail);
        friendRepository.delFriend2(userEmail, friendEmail);
    }
    
    //게시물 삽입
    @Transactional
    public void insertPost(String userEmail, MultipartFile imageFile) {
        try {
            String imageName = saveImage(imageFile); // 이미지 저장 처리 (아래에 메소드를 추가해야 함)
            postRepository.insertPost(userEmail, imageName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save image.");
        }
    }
    //저장하고 파일이름반환
    private String saveImage(MultipartFile imageFile) throws IOException {
        String imageName = generateUniqueImageName();
        String savePath = "C:\\programming\\sns\\sns\\src\\main\\resources\\static\\photo\\" + imageName+".jpg";
        imageFile.transferTo(new File(savePath));
        imageName+=".jpg";
        return imageName;
    }
    //랜덤이름주기
    private String generateUniqueImageName() {
        String imageName = UUID.randomUUID().toString();
        return imageName;
    }
    //비디오 올리기
    @Transactional
    public void insertVideo(String userEmail, MultipartFile videoFile) {
        try {
            String videoName = saveVideo(videoFile); // 이미지 저장 처리 (아래에 메소드를 추가해야 함)
            postRepository.insertVideo(userEmail, videoName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save image.");
        }
    }
    private String saveVideo(MultipartFile videoFile) throws IOException {
        String videoName = generateUniqueImageName();
        String savePath = "C:\\Users\\dita810\\Downloads\\sns_spring_project\\sns\\src\\main\\resources\\static\\photo\\" + videoName+".mp4";
        videoFile.transferTo(new File(savePath));
        videoName+=".mp4";
        return videoName;
    }
}
