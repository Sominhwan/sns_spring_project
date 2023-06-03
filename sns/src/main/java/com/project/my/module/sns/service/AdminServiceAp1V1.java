package com.project.my.module.sns.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.sns.dto.MailDTO;
import com.project.my.module.sns.dto.SMSDTO;
import com.project.my.module.userRole.entity.PostEntity;
import com.project.my.module.userRole.entity.SMSEntity;
import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.MailRepository;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class AdminServiceAp1V1 {
    private final UserRepository userRepository; 
	private final MailRepository mailRepository;
    
    // 회원 이름으로 회원 정보 검색
    @Transactional
    public String getUserInfo(String userName){
		if(userName==null) 
			userName = "";
           
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":["); 
		List<UserInfoEntity> userList = userRepository.search(userName);

		for (int i = 0; i < userList.size(); i++) {
			result.append("[{\"value\": \"" + "" + "\"},");
			result.append("{\"value\": \"" + (i+1) + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserName() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserNickName() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserEmail() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserSocialId() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserPN() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserAddress() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getEmailcertification() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserInfoType() + "\"},");
			result.append("{\"value\": \"" + userList.get(i).getUserRegDate() + "\"},");
			result.append("{\"value\": \"" + "" + "\"}],");
		}
		result.append("]}");
		return result.toString();
    } 

    // 회원 이메일로 선택회원에 관한 모든 정보 삭제
    @Transactional
    public void deleteUserInfo(String userEmail){
		userRepository.deleteUserInfo(userEmail);			
		// 회원정보와 관련된 모든 데이터 삭제
		// TODO
    } 	

    // 회원 이메일로 회원 게시물 검색
    @Transactional
    public String getUserPost(String userEmail){
        System.out.println(userEmail); 
		if(userEmail==null) 
			userEmail = "";
           
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":["); 
		List<PostEntity> userPostList = userRepository.searchPost(userEmail);

		for (int i = 0; i < userPostList.size(); i++) {
			result.append("[{\"value\": \"" + "" + "\"},");
			result.append("{\"value\": \"" + (i+1) + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getPostId() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getUserEmail() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getImageName() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getVideoName() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getCommentNum() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getLikeNum() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getShareNum() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getPostReport() + "\"},");
			result.append("{\"value\": \"" + userPostList.get(i).getCreationDate() + "\"},");
			result.append("{\"value\": \"" + "" + "\"}],");
		}
		result.append("]}");
		return result.toString();
    } 

    // postId로 선택게시물에 관한 모든 정보 삭제
    @Transactional
    public void deleteUserPost(String postId){
		userRepository.deleteUserPost(postId);			
		// 회원게시물과 관련된 모든 데이터 삭제
		// TODO
    } 	

    // 회원 이메일 주소 검색
    @Transactional
    public String getUserEmail(String userEmail){
		if(userEmail==null) 
			userEmail = "";
           
		ArrayList<UserInfoEntity> userEmailList = userRepository.searchUserEmail(userEmail);
		net.minidev.json.JSONObject sendObject = new net.minidev.json.JSONObject();
		net.minidev.json.JSONArray sendArray = new net.minidev.json.JSONArray();

		for(int i = 0; i < userEmailList.size(); i++ ){
			net.minidev.json.JSONObject informationObject = new net.minidev.json.JSONObject();
			informationObject.put("userEmail",userEmailList.get(i).getUserEmail());
			sendArray.add(informationObject);
		}
		sendObject.put("result",sendArray);			

		return sendObject.toString();
    } 	
    // 모든 메일 리스트 가져오기
    @Transactional
    public List<MailDTO> getSentMailData(){         
		List<MailDTO> mailList = new ArrayList<>();
		mailList = mailRepository.selectAllMessageList();
		return mailList;
    } 
    // 특정 메일 데이터 가져오기
    @Transactional
    public List<MailDTO> getSelectMailData(int num){         
		List<MailDTO> mailList = new ArrayList<>();
		mailList = mailRepository.selectMessageList(num);
		return mailList;
    } 
    // 광고수신 유저 휴대폰번호 가져오기
    @Transactional
    public List<SMSDTO> getUserPhoneList(){ 
		List<SMSDTO> userPhoneList = new ArrayList<>();     
		userPhoneList = userRepository.selectUserPhoneList();
		return userPhoneList;
    } 

	// 카페24 SMS 전송내역 가져오기
	@Transactional
	public List<SMSEntity> getSmsData(){ 
		List<SMSEntity> smsDataList = new ArrayList<>();     
		smsDataList = userRepository.selectSmsData();
		return smsDataList;
	} 
}
