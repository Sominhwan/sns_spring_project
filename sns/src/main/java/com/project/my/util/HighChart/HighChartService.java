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
}
