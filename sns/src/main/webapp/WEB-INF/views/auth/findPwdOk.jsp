<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/loading.css" />
    <link rel="stylesheet" href="/css/auth/findPwdOkPage.css" />
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
    <!-- 비밀번호 변경 완료 텍스트 -->
    <div class="findPwdInfo-text">
      <img src="/images/pwdChangeLogo.svg" id="pwdChangeLogo" />
      <span id="findPwd-text">비밀번호 변경 완료</span>
    </div>
    <!-- 비밀번호 변경 완료 컨텐츠 -->
    <div class="findPwd-content">
      <div id="findPwdComment">
        비밀번호 변경이 완료되었습니다. 새로운 비밀번호로 로그인해주세요.
      </div>
      <button
        type="submit"
        class="loginBtn"
        id="loginBtn"
        onclick="location.replace('/index')"
      >
        로그인 화면으로
      </button>
    </div>
    <!-- 푸터 시작 -->
    <footer class="signUp_footer">
      <div class="footer_inner">
        <span class="footer_info">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
  <script src="/js/auth/spin.js"></script>
</html>
