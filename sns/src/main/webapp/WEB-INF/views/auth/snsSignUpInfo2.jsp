<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/snsSignUpInfoPage2.css" />
    <link rel="shortcut icon" type="image/x-icon" href="images/loginLogo.png" />
    <title>가입하기 - PhoTalk</title>
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
      <img src="images/joinLogo.png" id="joinLogo" />
      <a href="login.jsp" id="logo">PhoTalk</a>
      <ul>
        <li><a href="signUp.jsp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="login.jsp" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <!-- 카카오 가입정보 입력 컨텐츠 -->
    <div class="signUpInfo-text">
      <span id="signUp-text">가입정보를 입력해주세요.</span>
    </div>
    <!-- 카카오 가입정보 입력 폼 -->
    <div class="signUpInfo-content">
      <form action="snsSignUpInfoProc.jsp" method="POST">
        <div class="input-box">
          <input
            id="userEmail"
            type="text"
            name="userEmail"
            readonly
            value="<%=userEmail%>"
            placeholder="이메일을 입력해 주세요"
            maxlength="60"
            autocomplete="false"
          />
          <label for="userEmail">이메일을 입력해 주세요</label>
        </div>
        <div class="input-box">
          <input
            id="userName"
            type="text"
            name="userName"
            placeholder="성명"
            maxlength="60"
            autocomplete="false"
          />
          <label for="userName">성명</label>
        </div>
        <div class="input-box">
          <input
            id="userNickName"
            type="text"
            name="userNickName"
            value="<%=userNickName%>"
            placeholder="닉네임"
            maxlength="60"
            autocomplete="false"
          />
          <label for="userNickName">닉네임</label>
        </div>
        <div class="input-box">
          <input
            id="userPhoneNum"
            type="text"
            name="userPhoneNum"
            placeholder="휴대폰 번호"
            maxlength="11"
            autocomplete="false"
          />
          <label for="userPhoneNum">휴대폰 번호</label>
        </div>
        <input id="userGender" type="hidden" name="userGender" value="<%=userGender%>"/>
        <input id="userSocialId" type="hidden" name="userSocialId" value="<%=userSocialId%>"/>
        <input id="userInfoType" type="hidden" name="userInfoType" value="<%=userInfoType%>"/>
        <input id="userAd" type="hidden" name="userAd" value="<%=agreement%>"/>
        <button
          type="submit"
          class="next-button"
          id="next-button"
          disabled
          onclick="submit"
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
  <script src="js/snsSignUpInfo.js"></script>
  <script src="js/spin.js"></script>
</html>
