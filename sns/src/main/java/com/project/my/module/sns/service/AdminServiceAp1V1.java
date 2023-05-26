package com.project.my.module.sns.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.my.module.userRole.entity.UserInfoEntity;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false) // readOnly = true 경우 db 접근시 select 만 가능
public class AdminServiceAp1V1 {
    private final UserRepository userRepository; 
    
    // 회원 이름으로 회원 정보 검색
    @Transactional
    public String getUserInfo(String userName){
        System.out.println(userName); 
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
}
