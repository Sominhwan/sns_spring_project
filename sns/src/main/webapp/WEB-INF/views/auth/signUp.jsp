<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/loading.css" />
    <link rel="stylesheet" href="/css/auth/signUpPage.css" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <title>가입하기 - PhoTalk</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="/js/auth/spin.js"></script>
    <script src="/js/login.js"></script>
    <script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>
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
    </script>
  </head>
  <body>
    <nav id="navbar">
      <img src="/images/joinLogo.png" id="joinLogo" />
      <a href="/index" id="logo">PhoTalk</a>
      <ul>
        <li><a href="/signUp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="/index" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <div class="signUp-logo">
      <img src="/images/joinWelcomeLogo.png" id="logo2" />
      <span id="signUp-text">Welcome to PhoTalk</span>
      <span id="signUp-select">회원가입 방식을 선택하세요.</span>
    </div>
    <!-- 회원가입 버튼 -->
    <div class="signUp-Btn">
      <hr />
      <div id="signUpBtn_false">
        <a href="/termsService"
          ><img
            src="/images/emailBtn.svg"
            onmouseover="this.src='/images/emailBtnTrue.svg'"
            onmouseout="this.src='/images/emailBtn.svg'"
            id="emailBtn"
          />
        </a>
      </div>
      <div id="naverSignUpBtn_false">
        <a href="/oauth2/authorization/naver"
          ><img
            src="/images/naverBtn.svg"
            onmouseover="this.src='/images/naverBtnTrue.svg'"
            onmouseout="this.src='/images/naverBtn.svg'"
            id="naverBtn"
        /></a>
      </div>
      <div id="kakaoSignUpBtn_false">
        <a href="/oauth2/authorization/kakao"
          ><img
            src="/images/kakaoBtn.svg"
            onmouseover="this.src='/images/kakaoBtnTrue.svg'"
            onmouseout="this.src='/images/kakaoBtn.svg'"
            id="kakaoBtn"
        /></a>
      </div>
    </div>
    <!-- 카카오 개인정보 저장 폼 -->
    <form name="kakaologin" method="post" action="termsService.jsp">
  	<input type="hidden" name="kakaoid"/>
  	<input type="hidden" name="email"/>
	<input type="hidden" name="nickname"/>
	<input type="hidden" name="gender"/>
  </form>
    <!-- 푸터 시작 -->
    <footer class="signUp_footer">
      <div class="footer_inner">
        <span class="footer_info">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
</html>
