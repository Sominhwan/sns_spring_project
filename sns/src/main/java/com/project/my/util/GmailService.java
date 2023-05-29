package com.project.my.util;


import java.util.List;
import java.util.Vector;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GmailService {
    @Autowired
    private JavaMailSender sender;
    private String host;
    private String title;
    private String sha256;
    private String content;
    // 단일 전송
    public boolean sendEmail(String userEmail, String userName){
        host = "http://localhost:8082/emailCheck";
        title = "Photalk 회원가입을 위한 이메일 확인 메일입니다.";
        sha256 = new SHA256().getSHA256(userEmail);		
        EmailTemplete emailTemplete = new EmailTemplete();
        content = emailTemplete.template(userName, host, sha256);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
            helper.setTo(userEmail);
            helper.setSubject(title);
            helper.setText(content, true);
            sender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }       
    }
    // 다중 전송
    public boolean sendMutlEmail(String []userAllEmail, String title, String content, Vector<String> getFilePath, List<MultipartFile> multipartFile){
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
            helper.setTo(userAllEmail);
            helper.setSubject(title);
            helper.setText(content, true);
            //String [] fileName = StringUtils.cleanPath(fileName);
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
