<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/signUpOkPage.css" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <title>가입완료 - PhoTalk</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  </head>
  <body>
    <nav id="navbar">
      <img src="/images/joinLogo.png" id="signUpOkLogo" />
      <a href="login.jsp" id="logo">PhoTalk</a>
      <ul>
        <li><a href="/signUp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="/index" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <!-- 가입완료 텍스트 -->
    <div class="signUpOkInfo-text">
      <img src="/images/joinCompleteLogo.svg" id="joinCompleteLogo" />
      <span id="signUp-text">회원가입이 완료 되었습니다.</span>
    </div>
    <!-- 가입완료 버튼 -->
    <div class="signUpOk-content" id="signUpOk-content">
      <div id="emailComment">
      </div>
      <div id="emailAlert">
        * 일주일이내에 인증이 완료되지 않을경우 자동 회원탈퇴 처리가 됩니다.
      </div>
      <form action="" method="POST">
        <input
          class="serviceBtn"
          id="serviceBtn"
          name="serviceBtn"
          type="button"
          value="서비스 이용하기"
          onclick="location.replace('/index')"
        />
      </form>
    </div>
    <!-- 푸터 시작 -->
    <footer class="signUp_footer">
      <div class="footer_inner">
        <span class="footer_info">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
  <script>
      let url = window.location.search;
      let param = new URLSearchParams(url);
      let userEmail = param.get('userEmail');
      document.getElementById("emailComment").innerHTML = `<b>`+userEmail+`</b>`+` 로 보낸 메일을 확인하여 이메일 인증을 완료해주세요.
         인증이 완료될 경우에만 서비스를 이용하실 수 있습니다.`;
  </script>
  <script src="/js/auth/loading.js"></script>
</html>
