package com.project.my.module.sns.service;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class GmailService {
    @Autowired
    private JavaMailSender sender;

    public String sendEmail(String userEmail){
        String host = "http://localhost:8082/index";
        String title = "Photalk 회원가입을 위한 이메일 확인 메일입니다.";		
        String content = "<html>\r\n"
        + "  <head></head>\r\n"
        + "  <body>\r\n"
        + "    <table style=\"width: 650px; height: 600px\">\r\n"
        + "      <thead style=\"width: 650px; height: 150px; background-color: #2e2e2e\">\r\n"
        + "        <tr>\r\n"
        + "          <th>\r\n"
        + "            <span\r\n"
        + "              style=\"\r\n"
        + "                width: 100px;\r\n"
        + "                height: 75px;\r\n"
        + "                font-family: Billabong;\r\n"
        + "                color: #fff;\r\n"
        + "                font-size: 60px;\r\n"
        + "                margin-left: -350px;\r\n"
        + "              \"\r\n"
        + "            >\r\n"
        + "              <img\r\n"
        + "                src=\"https://velog.velcdn.com/images/thalsghks/post/5cff43c3-1c63-4e23-b7f9-a3a8e5584a3f/image.png\"\r\n"
        + "                style=\"width: 40px\"\r\n"
        + "              />\r\n"
        + "              PhoTalk\r\n"
        + "            </span>\r\n"
        + "          </th>\r\n"
        + "        </tr>\r\n"
        + "      </thead>\r\n"
        + "      <tbody>\r\n"
        + "        <tr>\r\n"
        + "          <td><br /></td>\r\n"
        + "        </tr>\r\n"
        + "        <tr>\r\n"
        + "          <td>\r\n"
        + "            <!-- 본문의 제목, 내용, 안내글 -->\r\n"
        + "            <div\r\n"
        + "              style=\"\r\n"
        + "                background: linear-gradient(to top, yellow 0%, transparent 50%);\r\n"
        + "                font-size: 25px;\r\n"
        + "                font-weight: 500;\r\n"
        + "                margin-left: 40px;\r\n"
        + "                float: left;\r\n"
        + "              \"\r\n"
        + "            >\r\n"
        + "              PhoTalk 이메일 인증 안내\r\n"
        + "            </div>\r\n"
        + "          </td>\r\n"
        + "        </tr>\r\n"
        + "        <!-- div 태그를 이용한 구역 설정 -->\r\n"
        + "        <tr>\r\n"
        + "          <td>\r\n"
        + "            <div>\r\n"
        + "              <ul style=\"list-style: none\">\r\n"
        + "                <li style=\"line-height: 40px; float: left\">\r\n"
        + "                  <b>"+userEmail+"</b> 님 안녕하세요. PhoTalk 입니다.<br />항상 PhoTalk 서비스를\r\n"
        + "                  이용해주셔서 감사합니다.<br /><br />\r\n"
        + "                  다음 링크에 접속하여 이메일 확인을 진행하세요.\r\n"
        + "                  <a href='"+host+"emailCheckProc.jsp?code=" + "해쉬주소" + "'>이메일 인증하기</a>\r\n"						
        + "                </li>\r\n"
        + "              </ul>\r\n"
        + "            </div>\r\n"
        + "          </td>\r\n"
        + "        </tr>\r\n"
        + "      </tbody>\r\n"
        + "      <tfoot style=\"width: 650px; height: 100px; background-color: #eeeeee\">\r\n"
        + "        <tr>\r\n"
        + "          <td>\r\n"
        + "            <span\r\n"
        + "              style=\"list-style: none; line-height: 30px; margin-left: 30px\"\r\n"
        + "            >\r\n"
        + "              *본 메일은 PhoTalk 가입을 위해 회원가입한 회원에게 발송되는\r\n"
        + "              이메일입니다.\r\n"
        + "            </span>\r\n"
        + "            <span\r\n"
        + "              style=\"list-style: none; line-height: 30px; margin-left: 30px\"\r\n"
        + "            >\r\n"
        + "              &copy;2023 Social Net Work Project\r\n"
        + "            </span>\r\n"
        + "          </td>\r\n"
        + "        </tr>\r\n"
        + "      </tfoot>\r\n"
        + "    </table>\r\n"
        + "  </body>\r\n"
        + "</html>";

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        try {
            helper.setTo(userEmail);
            helper.setSubject(title);
            helper.setText(content, true);
            sender.send(message);
            return "전송완료";
        } catch (Exception e) {
            return "전송실패";
        }
        
    }

}
