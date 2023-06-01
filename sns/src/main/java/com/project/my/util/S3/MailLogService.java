package com.project.my.util.S3;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.my.module.userRole.entity.MailFileEntity;
import com.project.my.module.userRole.entity.MailFileLogEntity;
import com.project.my.module.userRole.entity.SendMailEntity;
import com.project.my.module.userRole.repository.MailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailLogService {
    private final MailRepository mailRepository;
    private final SendMailEntity sendMailEntity;
    private final MailFileEntity mailFileEntity;
    private final MailFileLogEntity mailFileLogEntity;


    public String saveSendMailLog(String []email, String title, String content, List<String> fileUrl, List<String> fileName) {
        int [] mailIdx = new int[email.length]; // 발송내역 idx
        int [] fileIdx = new int[fileUrl.size()]; // 첨부파일 내역 idx

        int j = 0;
        for(String mail : email) { // 발송 내역 저장
            sendMailEntity.setEmail(mail);
            sendMailEntity.setTitle(title);
            sendMailEntity.setContent(content);

            mailRepository.insertSendMail(sendMailEntity);  
            mailIdx[j++] = sendMailEntity.getSendmail_idx();  
        }
        for(int i = 0 ; i<fileUrl.size(); i++){ // 첨부파일 내역 저장
            mailFileEntity.setFile_url(fileUrl.get(i));
            mailFileEntity.setFile_name(fileName.get(i));
            mailRepository.insertMailFile(mailFileEntity); 
            fileIdx[i] = mailFileEntity.getMailfile_idx();             
        }
        for(int i = 0 ; i<mailIdx.length; i++){ // 모든 첨부파일 내역 저장
            for(int k = 0; k<fileIdx.length; k++){
                mailFileLogEntity.setSendmail_idx(mailIdx[i]);
                mailFileLogEntity.setMailfile_idx(fileIdx[k]);
                mailRepository.insertMailFileLog(mailFileLogEntity);
            }
        }          
      
        return null;
    }
}
