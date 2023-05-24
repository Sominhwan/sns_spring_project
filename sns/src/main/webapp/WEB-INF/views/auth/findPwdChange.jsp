<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/findPwdChangePage.css" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <title>비밀번호 찾기 - PhoTalk</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
     var spinner;
      jQuery(function () {
        spinner = new Spinner().spin().el;
        jQuery(document.body).append(spinner);
        jQuery(spinner).css("display", "none");
      });
      window.onbeforeunload = function (e) {
        if (e != null && e != undefined) {
          jQuery(spinner).css("display", "");
        }
      }; 
      /* 비밀번호 유효성 검사 및 비밀번호 변경 */
      function pwdForm_check(){
    	  const pwd = document.getElementById("userNewPwd");
    	  const repwd = document.getElementById("userNewPwdCheck");
    	  if(pwd.value!=repwd.value){
    		  document.getElementById("pwdCheck2").style.display='none';
          document.getElementById("pwdCheck3").style.display='none';
    		  document.getElementById("pwdCheck1").style.display='block';
    		  pwd.value=null;
    		  repwd.value=null;
    		  pwd.focus();		  
    		  return false;
    	  }
    	  
    	  var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/;
    	  if(!pwdCheck.test(pwd.value) || !pwdCheck.test(repwd.value)){
    		  document.getElementById("pwdCheck1").style.display='none';
          document.getElementById("pwdCheck3").style.display='none';
    		  document.getElementById("pwdCheck2").style.display='block';
    		  pwd.value=null;
    		  repwd.value=null;
    		  pwd.focus();	  
    		  return false;
    	  } 

        const userEmail = document.getElementById('idText2').innerHTML;
        const userPassword = document.getElementById("userNewPwd").value;
        $.ajax({
          url : "/changeUserPwd",
          type : "post",
          data: {
            userEmail: userEmail,
            userPassword : userPassword
          },
          success : function(obj){
            if(obj.fail != null){ // 소설로그인 시
              document.getElementById("pwdCheck1").style.display='none';
    		      document.getElementById("pwdCheck2").style.display='none';
    		      document.getElementById("pwdCheck3").style.display='block';
              pwd.value=null;
    		      repwd.value=null;
    		      pwd.focus();	
            }
            if(obj.success != null){
              location.replace('/findPwdOk');
            }  
          },
          error : function(obj){
            document.getElementById("errorAlarmText").innerHTML = "존재하는 비밀번호가 없습니다.";
            $('.signUp-modal').css('display', 'block');
          }
        }) 
      }

      // 비밀번호 찾기 input 값 엔터키 이벤트
      function onEnterChangePwd() {
        const userNewPwd = document.getElementById('userNewPwd').value;
        const userNewPwdCheck = document.getElementById('userNewPwdCheck').value;
        var keyCode = window.event.keyCode;
        if (keyCode == 13) {
          //엔테키 이면
          if(userNewPwd != "" && userNewPwdCheck != "" ){
            pwdForm_check();
          }
        }
      }      
    </script>
  </head>
  <body>
    <nav id="navbar">
      <img src="/images/joinLogo.png" id="signUpOkLogo" />
      <a href="/index" id="logo">PhoTalk</a>
      <ul>
        <li><a href="/signUp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="/index" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <!-- 비밀번호 재설정 텍스트 -->
    <div class="findPwdInfo-text">
      <span id="findPwd-text">비밀번호 찾기</span>
    </div>
    <!-- 비밀번호 재설정 컨텐츠 -->
    <div class="findPwd-content">
      <div id="findPwdComment">새로운 비밀번호를 재설정해주세요.</div>
      <!-- 새 비밀번호 입력 폼 -->
      <div class="findPwdInput-container">
        <form method="POST" name="findPwd_frm" id = "findPwd_frm" onsubmit="return false;">
          <span class="idText">아이디</span>
          <span class="idText2" id="idText2">${param.userEmail}</span>
          <span class="pwdText">새 비밀번호</span>
          <input -webkit-autofill
            id="userNewPwd"
            type="text"
            name="userNewPwd"
            placeholder="영문 숫자 포함 8자 이상"
            maxlength="60"
            autocomplete="false"
            onkeydown="javascript:onEnterChangePwd();"
            style="-webkit-box-shadow: 0 0 0 1000px #f9f9f9 inset"
          />
          <span class="pwdTextCheck">새 비밀번호 확인</span>
          <input
            id="userNewPwdCheck"
            type="password"
            name="userNewPwdCheck"
            placeholder="새 비밀번호 확인"
            maxlength="60"
            autocomplete="false"
            onkeydown="javascript:onEnterChangePwd();"
          />
          <input name="userEmail" type="hidden" value="간다">
          	<span id="pwdCheck1"style="display:none; position:absolute;left: 200px;top: 173px;color:#ed4956;font-size: 12px">
          		* 비밀번호가 일치하지 않습니다.
          	</span>      
          	<span id="pwdCheck2" style="display:none; position:absolute;left: 200px;top: 173px;color:#ed4956;font-size: 12px">
          		* 비밀번호 양식이 틀립니다.
          	</span> 
           	<span id="pwdCheck3" style="display:none; position:absolute;left: 200px;top: 173px;color:#ed4956;font-size: 12px">
          		* 이전과 같은 비밀번호입니다.
          	</span>        
          <button
            type="button"
            class="findPwdCheckBtn"
            id="findPwdCheckBtn"
            disabled
            onclick="pwdForm_check()"
          >
            확인
          </button>
        </form>
      </div>
    </div>
    <!-- 푸터 시작 -->
    <footer class="signUp_footer">
      <div class="footer_inner">
        <span class="footer_info">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
  <script src="/js/auth/findPwdChange.js"></script>
  <script src="/js/auth/spin.js"></script>
</html>
