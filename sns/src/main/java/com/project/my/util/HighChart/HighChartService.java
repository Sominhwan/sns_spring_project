package com.project.my.util.HighChart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.my.module.sns.dto.PostDTO;
import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HighChartService {
    private final UserRepository userRepository;

    // 2023년 월별 회원가입수 데이터 가져오기
    public List<?> getUserCount(){
        List<Integer> countList = new ArrayList<>();
		Integer [] count = new Integer[12];
        for(int i = 0; i<12; i++){
            count[i] = userRepository.selectUserCount(i+1);
            countList.add(count[i]);
        }
        return countList;        
    }

    // 2023년 월별 게시물 수 데이터 가져오기
    public List<?> getUserPostCount(){
        List<Integer> countList = new ArrayList<>();
		Integer [] count = new Integer[12];
        for(int i = 0; i<12; i++){
            count[i] = userRepository.selectUserPostCount(i+1);
            countList.add(count[i]);
        }
        return countList;        
    }    

    // HighChart 상위 5개 좋아요 수 가져오기
    public List<PostDTO> getPostInfoCount(){
        return userRepository.selectPostInfoCount();   
    }       

    // HighChart 특정 유저가 올린 상위 12개 게시물 가져오기
    public String getPostUpCount(){
        ArrayList<PostDTO> postList = new ArrayList<>();
        postList = userRepository.selectPostUpCount(); 
        Integer count = userRepository.selectPostAllCount();


        net.minidev.json.JSONObject sendObject = new net.minidev.json.JSONObject();
		net.minidev.json.JSONArray sendArray = new net.minidev.json.JSONArray();

		for(int i = 0; i<postList.size(); i++) {
			net.minidev.json.JSONObject informationObject = new net.minidev.json.JSONObject();
			informationObject.put("userEmail",postList.get(i).getUserEmail());
			informationObject.put("userPostCount",postList.get(i).getCount());
			sendArray.add(informationObject);
		}
		sendObject.put("result",sendArray);			
		sendObject.put("count",count);			

		return sendObject.toString();
    }       
}
