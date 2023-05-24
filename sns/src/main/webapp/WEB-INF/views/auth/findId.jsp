<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/loading.css" />
    <link rel="stylesheet" href="/css/auth/findIdPage.css" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <title>아이디 찾기 - PhoTalk</title>
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

      // 아이디 찾기 
      function findIdProcess(){
        const userName = document.getElementById('userName').value;
        const userNickName = document.getElementById('userNickName').value;
        $.ajax({
          url : "/findUserId",
          type : "post",
          data: {
            userName: userName,
            userNickName: userNickName
          },
          success : function(obj){
            if(obj.message != null){ // 로그인 실패시
              document.getElementById("errorAlarmText").innerHTML = obj.message;
              $('.signUp-modal').css('display', 'block');
            }
            if(obj.userEmail != null){
              var form = document.createElement("form");
              form.setAttribute("charset", "UTF-8");
              form.setAttribute("method", "Post");  //Post 방식
              form.setAttribute("action", "/findIdOk"); //요청 보낼 주소
              var hiddenField = document.createElement("input");
              hiddenField.setAttribute("type", "hidden");
              hiddenField.setAttribute("name", "userEmail");
              hiddenField.setAttribute("value", obj.userEmail);
              form.appendChild(hiddenField);  

              hiddenField = document.createElement("input");
              hiddenField.setAttribute("type", "hidden");
              hiddenField.setAttribute("name", "userRegDate");
              hiddenField.setAttribute("value", obj.userRegDate);
              form.appendChild(hiddenField);   
              
              document.body.appendChild(form);
              form.submit();
            }     
          },
          error : function(obj){
            document.getElementById("errorAlarmText").innerHTML = "존재하지 않은 아이디입니다.";
            $('.signUp-modal').css('display', 'block');
          }
        })
      }

      /* 아이디 찾기 폼 엔터키로 이벤트 발생 */
      function onEnterFindId() {
        const userName = document.getElementById('userName').value;
        const userNickName = document.getElementById('userNickName').value;
        var keyCode = window.event.keyCode;
        if (keyCode == 13) {
          //엔테키 이면
          if(userName != "" && userNickName!= ""){
            findIdProcess();
          }
        }
      }      
    </script>
  </head>
  <body>
    <!-- 아이디 찾기 모달 -->	   
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
    <!-- 아이디 찾기 텍스트 -->
    <div class="findIdInfo-text">
      <span id="findId-text">아이디 찾기</span>
    </div>
    <!-- 아이디 찾기 컨텐츠 -->
    <div class="findId-content">
      <div id="findIdComment">
        회원가입에 등록된 성명과 사용자이름을 확인하여 아이디를 찾을 수
        있습니다.
      </div>
      <!-- 아이디 폼 -->
      <form action="" method="POST">
        <div class="input-box">
          <input -webkit-autofill
            id="userName"
            type="text"
            name="userName"
            placeholder="성명"
            maxlength="60"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
            onkeydown="javascript:onEnterFindId();"
          />
          <label for="userName">성명</label>
        </div>
        <div class="input-box">
          <input -webkit-autofill
            id="userNickName"
            type="text"
            name="userNickName"
            placeholder="닉네임"
            maxlength="60"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
            onkeydown="javascript:onEnterFindId();"
          />
          <label for="userNickName">닉네임</label>
        </div>

        <button
          type="button"
          class="findIdBtn"
          id="findIdBtn"
          disabled
          onclick="findIdProcess()"
        >
          아이디 찾기
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
  <script src="/js/auth/findId.js"></script>
  <script src="/js/auth/spin.js"></script>
</html>
