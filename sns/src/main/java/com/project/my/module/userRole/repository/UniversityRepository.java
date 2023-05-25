package com.project.my.module.userRole.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.project.my.module.userRole.entity.UniversityEntity;

@Mapper
@Repository
public interface UniversityRepository {
    //대학교 들고오기
    List<UniversityEntity> universityData(@Param("university") String university);
}
