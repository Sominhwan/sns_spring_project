package com.project.my.util.SMS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.project.my.module.userRole.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsSendService {
	private final UserRepository userRepository;

    public Map sendSMS(String msg_1, String rphone_1, String action_1, String sphone1_1, String sphone2_1, String sphone3_1){
        String action;
        String charsetType = "utf-8"; //EUC-KR 또는 UTF-8
		try {
			action = nullcheck(action_1, "");
		    if(action.equals("go")) {
		        String sms_url = "";

		        sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // SMS 전송요청 URL
		        String setPhone =  rphone_1;
		        String setContent = msg_1;
		        String user_id = base64Encode("thalsghks"); // SMS아이디
		        String secure = base64Encode("a7df2ef01970f20ed87a0051f81592ff");//인증키
		        String msg = base64Encode(nullcheck(msg_1, ""));
		        String rphone = base64Encode(nullcheck(rphone_1, ""));
		        String sphone1 = base64Encode(nullcheck(sphone1_1, ""));
		        String sphone2 = base64Encode(nullcheck(sphone2_1, ""));
		        String sphone3 = base64Encode(nullcheck(sphone3_1, ""));
		        String rdate = base64Encode(nullcheck("", ""));
		        String rtime = base64Encode(nullcheck("", ""));
		        String mode = base64Encode("1");
		        String subject = "";
		        if(nullcheck("", "").equals("L")) {
		            subject = base64Encode(nullcheck("", ""));
		        }
		        String testflag = base64Encode(nullcheck("", ""));
		        String destination = base64Encode(nullcheck("", ""));
		        String repeatFlag = base64Encode(nullcheck("", ""));
		        String repeatNum = base64Encode(nullcheck("", ""));
		        String repeatTime = base64Encode(nullcheck("", ""));
		        String returnurl = nullcheck("", "");
		        String nointeractive = nullcheck("", "");
		        String smsType = base64Encode(nullcheck("", ""));

		        String[] host_info = sms_url.split("/");
		        String host = host_info[2];
		        String path = "/" + host_info[3];
		        int port = 80;

		        // 데이터 맵핑 변수 정의
		        String arrKey[]
		            = new String[] {"user_id","secure","msg", "rphone","sphone1","sphone2","sphone3","rdate","rtime"
		                        ,"mode","testflag","destination","repeatFlag","repeatNum", "repeatTime", "smsType", "subject"};
		        String valKey[]= new String[arrKey.length] ;
		        valKey[0] = user_id;
		        valKey[1] = secure;
		        valKey[2] = msg;
		        valKey[3] = rphone;
		        valKey[4] = sphone1;
		        valKey[5] = sphone2;
		        valKey[6] = sphone3;
		        valKey[7] = rdate;
		        valKey[8] = rtime;
		        valKey[9] = mode;
		        valKey[10] = testflag;
		        valKey[11] = destination;
		        valKey[12] = repeatFlag;
		        valKey[13] = repeatNum;
		        valKey[14] = repeatTime;
		        valKey[15] = smsType;
		        valKey[16] = subject;

		        String boundary = "";
		        Random rnd = new Random();
		        String rndKey = Integer.toString(rnd.nextInt(32000));
		        MessageDigest md = MessageDigest.getInstance("MD5");
		        byte[] bytData = rndKey.getBytes();
		        md.update(bytData);
		        byte[] digest = md.digest();
		        for(int i =0;i<digest.length;i++)
		        {
		            boundary = boundary + Integer.toHexString(digest[i] & 0xFF);
		        }
		        boundary = "---------------------"+boundary.substring(0,11);

		        // 본문 생성
		        String data = "";
		        String index = "";
		        String value = "";
		        for (int i=0;i<arrKey.length; i++)
		        {
		            index =  arrKey[i];
		            value = valKey[i];
		            data +="--"+boundary+"\r\n";
		            data += "Content-Disposition: form-data; name=\""+index+"\"\r\n";
		            data += "\r\n"+value+"\r\n";
		            data +="--"+boundary+"\r\n";
		        }
		        if(rphone_1.replaceAll("-","").length() < 8 ||
                    rphone_1.replaceAll("-", "").length() > 11 ||
                    rphone_1.charAt(0) !='0') {
                    Map result = new HashMap<String, Object>();
                    result.put("result", "올바른 휴대폰 번호를 입력하세요");
                    return result;
		        } else {
			        InetAddress addr = InetAddress.getByName(host);
			        Socket socket = new Socket(host, port);
			        // 헤더 전송
			        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charsetType));
			        wr.write("POST "+path+" HTTP/1.0\r\n");
			        wr.write("Content-Length: "+data.length()+"\r\n");
			        wr.write("Content-type: multipart/form-data, boundary="+boundary+"\r\n");
			        wr.write("\r\n");

			        // 데이터 전송
			        wr.write(data);
			        wr.flush();

			        // 결과값 얻기
			        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(),charsetType));
			        String line;
			        String alert = "";
			        ArrayList tmpArr = new ArrayList();
			        while ((line = rd.readLine()) != null) {
			            tmpArr.add(line);
			        }
			        wr.close();
			        rd.close();

			        String tmpMsg = (String)tmpArr.get(tmpArr.size()-1);
			        String[] rMsg = tmpMsg.split(",");
			        String Result= rMsg[0]; //발송결과
			        String Count= ""; //잔여건수
			        if(rMsg.length>1) {Count= rMsg[1]; }

			                        //발송결과 알림
			        if(Result.equals("success")) {
			            alert = "성공적으로 발송하였습니다.";
			            alert += " 잔여건수는 "+ Count+"건 입니다.";
			        }
			        else if(Result.equals("reserved")) {
			            alert = "성공적으로 예약되었습니다";
			            alert += " 잔여건수는 "+ Count+"건 입니다.";
			        }
			        else if(Result.equals("3205")) {
			            alert = "잘못된 번호형식입니다.";
			        }
			        else {
			            alert = "[Error]"+Result;
			        }	    	
			        if(nointeractive.equals("1") && !(Result.equals("Test Success!")) && !(Result.equals("success")) && !(Result.equals("reserved")) ) {
			            //out.println("<script>alert('" + alert + "')</script>");
			        }
			        else if(!(nointeractive.equals("1"))) { // 메일 전송 성공시
						userRepository.insertSMSData(setPhone, setContent);
                        Map result = new HashMap<String, Object>();
			        	result.put("result", alert);
			        				        	
                        return result;
			        }
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

    public static String nullcheck(String str,  String Defaultvalue ) throws Exception
    {
		String ReturnDefault = "" ;

		if (str == null){
		ReturnDefault =  Defaultvalue ;
		} else if (str == "" ) {
		ReturnDefault =  Defaultvalue ;
		} else{
		ReturnDefault = str ;
		}
        return ReturnDefault ;
    }
    
    public static String base64Encode(String str)  throws java.io.IOException {
        byte[] strByte = str.getBytes();
        String result = Base64Utils.encodeToString(strByte);
        return result ;
    }
    
    public static String base64Decode(String str)  throws java.io.IOException {
        byte[] strByte = Base64Utils.decodeFromString(str);
        String result = new String(strByte);
        return result ;
    }    
}
