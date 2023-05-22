package com.project.my.util;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GmailService {
    @Autowired
    private JavaMailSender sender;

    public boolean sendEmail(String userEmail, String userName){
        String host = "http://localhost:8082/emailCheck";
        String title = "Photalk 회원가입을 위한 이메일 확인 메일입니다.";
        String sha256 = new SHA256().getSHA256(userEmail);		
        EmailTemplete emailTemplete = new EmailTemplete();
        String content = emailTemplete.template(userName, host, sha256);

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
}
