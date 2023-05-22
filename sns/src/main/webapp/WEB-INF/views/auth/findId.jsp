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
        alert(userName);
        $.ajax({
          url : "/findUserId",
          type : "post",
          data: {
            userName: userName,
            userNickName: userNickName
          },
          success : function(obj){
            if(obj.message != null){ // 로그인 실패시
              alert(obj.message);
              return false;
            }
            if(obj.userEmail != null){
              alert(obj.userEmail);
              alert(obj.userRegDate);
              return false;
            }     
          }
        })
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
