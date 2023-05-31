package com.project.my.module.userRole.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.my.module.sns.dto.MailDTO;
import com.project.my.module.userRole.entity.MailFileEntity;
import com.project.my.module.userRole.entity.MailFileLogEntity;
import com.project.my.module.userRole.entity.SendMailEntity;

@Mapper
@Repository
public interface MailRepository {
    // 발송내역 INSERT
    void insertSendMail(SendMailEntity sendMailEntity);
    // 첨부파일 내역 INSERT
    void insertMailFile(MailFileEntity mailFileEntity);
    // 모든 첨부파일 내역 INSERT
    void insertMailFileLog(MailFileLogEntity mailFileLogEntity);
    // 모든 메일내역 SELECT
    List<MailDTO> selectAllMessageList();
    
}
