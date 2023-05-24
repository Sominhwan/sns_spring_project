<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/findPwdPage.css" />
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

      // 비밀번호 찾기
      function findPwdProcess(){
        const userEmail = document.getElementById('userEmail').value;
        $.ajax({
          url : "/findUserPwd",
          type : "post",
          data: {
            userEmail: userEmail
          },
          success : function(obj){
            if(obj.message != null){ // 소설로그인 시
              document.getElementById("errorAlarmText").innerHTML = obj.message;
              $('.signUp-modal').css('display', 'block');
            }
            if(obj.success != null){ // 비밀번호 찾기 성공시
              document.getElementById('pwdForm').submit();             
            }
            if(obj.fail != null){ // 비밀번호 찾기 실패시
              document.getElementById("errorAlarmText").innerHTML = "존재하는 비밀번호가 없습니다.";
              $('.signUp-modal').css('display', 'block');
            }       
          },
          error : function(obj){
            document.getElementById("errorAlarmText").innerHTML = "존재하는 비밀번호가 없습니다.";
            $('.signUp-modal').css('display', 'block');
          }
        })
      }
      // 비밀번호 찾기 input 값 엔터키 이벤트
      function onEnterFindPwd() {
        const userEmail = document.getElementById('userEmail').value;
        var keyCode = window.event.keyCode;
        if (keyCode == 13) {
          //엔테키 이면
          if(userEmail != ""){
            findPwdProcess();
          }
        }
      }
    </script>
  </head>
  <body>
    <!-- 비밀번호 찾기 모달 -->
    <div class="signUp-modal" style="display:none;" >
      <div class="bg" >
        <div class="signUp-Box">
          <div class="errorMsg"><span id="errorText">오류 메시지</span></div>
          <div class="errorAlarm"><span id="errorAlarmText"></span></div>
          <div class="errorCheck" style="cursor: pointer" onclick="closeSignUpModal()"><span id="errorCheckText">확인</span></div>
        </div>
      </div>    
    </div>
    <nav id="navbar">
      <img src="/images/joinLogo.png" id="signUpOkLogo" />
      <a href="/index" id="logo">PhoTalk</a>
      <ul>
        <li><a href="/signUp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="/index" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <!-- 비밀번호 찾기 텍스트 -->
    <div class="findPwdInfo-text">
      <span id="findPwd-text">비밀번호 찾기</span>
    </div>
    <!-- 비밀번호 찾기 컨텐츠 -->
    <div class="findPwd-content">
      <div id="findPwdComment">비밀번호 찾기를 위한 ID를 입력해주세요.</div>
      <!-- 비밀번호 폼 -->
      <form id="pwdForm" action="/findPwdChange" method="POST" onsubmit="return false;">
        <div class="input-box">
          <input -webkit-autofill
            id="userEmail"
            type="text"
            name="userEmail"
            placeholder="이메일을 입력해주세요"
            maxlength="60"
            autocomplete="false"
            onkeydown="javascript:onEnterFindPwd();"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
          />
          <label for="userEmail">이메일을 입력해주세요</label>
        </div>
        <button
          type="button"
          class="findPwdBtn"
          id="findPwdBtn"
          disabled
          onclick="findPwdProcess()"
        >
          다음
        </button>
      </form>
    </div>
    <!-- 푸터 시작 -->
    <footer class="signUp_footer">
      <div class="footer_inner">
        <span class="footer_info">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
  <script src="/js/auth/findPwd.js"></script>
  <script src="/js/auth/spin.js"></script>
</html>
