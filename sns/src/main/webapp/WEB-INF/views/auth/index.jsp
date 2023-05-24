<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="shortcut icon"
      type="image/x-icon"
      href="/images/loginLogo.png"
    />
    <link rel="stylesheet" href="/css/auth/loginPage.css" />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script
      type="text/javascript"
      src="https://developers.kakao.com/sdk/js/kakao.js"
    ></script>
    <title>PhoTalk</title>
    <script type="text/javascript"> 
      var emailCheck;
      var socialEmail = '${userEmail}';
      if(socialEmail != ""){
        sessionStorage.setItem('userEmailHash', socialEmail);
        document.getElementById('loginErrorMsg').style.display = 'none';
        emailCheck = '${emailCertification}';
        document.getElementById('login_container').style.display = 'none';
        document.getElementById('loginOK_container').style.display = 'block';
        document.getElementById("loginOKBtn").value = userNickName+ " 님으로 계속";      
        document.getElementById("profile").src = userImage ;
        document.getElementById("login").innerHTML = userNickName + ` 님이 아닌가요?  <a href="#" style="text-decoration: none;color: #1877f2;"onclick="loginContainerChange()">계정 변경</a>` ;                          
      }

      /* 로그인 확인 폼 제출 */
      function loginFrm() {
        const userEmail = document.getElementById('userEmail').value;
        const password = document.getElementById('password').value;
        const rememberCheck = document.getElementById("remember");
        const remember = rememberCheck.checked;
        $.ajax({
          url : "/login-process",
          type : "post",
          data: {
            userEmail: userEmail,
            password: password,
            remember: remember,
          },
          success : function(obj){
            if(obj.userEmail == null){ // 로그인 실패시
              document.getElementById('loginErrorMsg').style.display = 'block';
              return false;
             }     
             sessionStorage.setItem('userEmailHash', obj.userEmailHash);
             document.getElementById('loginErrorMsg').style.display = 'none';
             emailCheck = obj.emailcertification;
             document.getElementById('login_container').style.display = 'none';
             document.getElementById('loginOK_container').style.display = 'block';
             document.getElementById("loginOKBtn").value = obj.userNickName+ " 님으로 계속";      
             document.getElementById("profile").src = obj.userImage ;
             document.getElementById("login").innerHTML = obj.userNickName + ` 님이 아닌가요?  <a href="#" style="text-decoration: none;color: #1877f2;"onclick="loginContainerChange()">계정 변경</a>` ;                  
          }
        })
      }
      /* 로그인 폼 엔터키로 이벤트 발생 */
      function onEnterLogin() {
        const userEmail = document.getElementById('userEmail').value;
        const password = document.getElementById('password').value;
        var keyCode = window.event.keyCode;
        if (keyCode == 13) {
          //엔테키 이면
          if(userEmail != "" && password != ""){
            loginFrm();
          }
        }
      }
      /* 메인화면 이동 검증 */
      function checkEmail() {
        if(emailCheck == 1){
          document.getElementById("loginOk_frm").submit();
        } else{
          alert("이메일 인증을 하지 않은 계정입니다.");
        }
      }
      /* 로그아웃 시 로그인 컨테이너 변경 */
      function loginContainerChange() {       
        $.ajax({
          url : "/logout",
          type : "post",
          data: {},
          success : function(obj){             
            document.getElementById('loginOK_container').style.display = 'none';
            document.getElementById('login_container').style.display = 'block';
            sessionStorage.removeItem('userEmailHash');        
          },
          error : function(obj){
            alert("실패");
          }
        })
      }
    </script>
  </head>
  <body>
    <div class="content">
      <div class="left-content">
        <div id="mockUp">
          <div id="imac">
            <img
              src="${pageContext.request.contextPath}/images/imacMockUP.png"
            />
          </div>
          <div id="iphone">
            <img src="/images/iphoneMocUp.png" />
          </div>
          <div class="fade_container">
            <img class="active" src="/images/iphoneScreen1.png" alt="image1" />
            <img src="/images/iphoneScreen2.png" alt="image2" />
            <img src="/images/iphoneScreen3.png" alt="image3" />
          </div>
        </div>
      </div>
      <!-- 로그인 컨테이너 -->
      <div class="login_container" id="login_container">
        <img src="/images/loginLogo.png" />
        <span id="logo_text">PhoTalk</span>
        <form
          method="POST"
          name="login_frm"
          id="login_frm"
          action="/login-process"
          onsubmit="return false;"
        >
          <div class="input-box">
            <input
              -webkit-autofill
              id="userEmail"
              type="text"
              name="userEmail"
              placeholder="이메일을 입력해 주세요"
              maxlength="60"
              style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
              onkeydown="javascript:onEnterLogin();"
            />
            <label for="userEmail">이메일을 입력해 주세요</label>
            <span id="userEmailClear">
              <img src="/images/loginEmailCancelBtn.svg" alt="emailCancel" />
            </span>
          </div>
          <div class="input-box">
            <input
              class="password"
              id="password"
              type="password"
              name="password"
              placeholder="비밀번호를 입력해 주세요"
              maxlength="50"
              autocomplete="false"
              onkeydown="javascript:onEnterLogin();"
            />
            <label for="password">비밀번호를 입력해 주세요</label>
            <span id="keyShow">
              <img
                src="/images/pwdEyeBtnFalse.svg"
                alt="eye"
              />
            </span>
          </div>
          <input type="checkbox" id="remember" name="remember" />
          <label for="remember"></label>
          <span id="auto_login_text">자동 로그인</span> 
          <div id="popup">
            <a href="#" class="btn-open" onClick="javascript:popOpen();">
              <img
                src="/images/exclamation-circle-line.svg"
                style="cursor: help"
                alt="eye"
              />
            </a>
          </div>
          <input
            class="loginBtn"
            id="loginBtn"
            name="loginBtn"
            type="button"
            disabled
            value="로그인"
            onclick="loginFrm()"
          />
        </form>
        <!-- modal 영역 -->
        <div class="modal-bg" onClick="javascript:popClose();"></div>
        <div class="modal-wrap">
          <span id="modal_text"
            >개인정보 보호를 위해 개인 기기에서만 사용해 주세요.</span
          >
          <span id="cancelBtn" onclick="javascript:popClose();">
            <img src="/images/makePostCancelBtn.svg" />
          </span>
        </div>
        <span class="id_pwd" id="id_find"><a href="/findId">ID</a></span>
        <span class="id_pwd" id="idPwd">/</span>
        <span class="id_pwd" id="pass_find"
          ><a href="/findPwd">PASS 찾기</a></span
        >
        <span id="kakaoLogin">
          <a href="/oauth2/authorization/kakao"><img src="/images/kakaoLoginBtn2.svg" /></a>
        </span>
        <span id="naverLogin">
          <a href="/oauth2/authorization/naver"><img src="/images/naverLoginBtn.svg" /></a>
        </span>
        <!-- 로그인 실패시 뜨는 문구 -->
        <span id="loginErrorMsg" style="position: absolute; left: 71px; top: 530px; color:#ed4956; font-size:14px; display: none;">
          * 로그인에 실패하였습니다.
        </span>
        <span id="signUp">아직도 회원이 아닌가요?</span>
        <span id="signUpTag"><a href="/signUp">회원가입</a></span>
      </div>    
      <!-- 로그인 완료 컨테이너  -->
      <div class="loginOK_container" id="loginOK_container" style="display: none;">
        <img src="/images/loginLogo.png" />
        <span id="logo2_text">PhoTalk</span>
        <img class="profile" id="profile" src="" />
        <!-- form action 에 메인 페이지 주소 넣기 -->
        <form action="/main" method="POST" name="loginOk_frm" id = "loginOk_frm">
          <input
            class="loginOKBtn"
            id="loginOKBtn"
            name="loginOKBtn"
            type="button"
            value=""
            onclick="checkEmail()"
            style="cursor: pointer;"
          />
           <span id="login" style="position: absolute;height: 18px;width: 519.5px;font-size: 18px;
           color: #868e96;top: 450px;left:25%;transform: translate(0%, 0%);">
           </span>
        </form>      
      </div>
    </div>
    <!-- 푸터 -->
    <footer class="login_footer">
      <div class="footer_inner">
        <span class="footer_info"><a href="#">소개</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">채용안내</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">이용약관</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">도움말</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">운영정책</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">위치</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="#">인기 계정</a></span>
        <span class="footer_info">|</span>
        <span class="footer_info"><a href="login.jsp">사이트맵</a></span>
        <br />
        <span class="footer_info2">(주) 자바A_Project </span>
        <span class="footer_info2"
          >부산광역시 부산진구 엄광로 176(가야동)
        </span>
        <span class="footer_info2">전화: 010-1111-1111</span>
        <br />
        <span class="footer_info3">E-mail: thalsghks@naver.com</span>
        <span class="footer_info3">사업자등록번호: 111-11-11111호</span>
        <br />
        <span class="footer_info4">&copy;2023 Social Net Work Project</span>
      </div>
    </footer>
  </body>
  <script>
    // 페이지 새로고침시 로그인 유지
      if(sessionStorage.getItem('userEmailHash')!=null){
        $.ajax({
          url : "/loginOk.action",
          type : "get",
          data: {
          },
          success : function(obj){   
            if(obj.userNickName == undefined){ // 다른 url에서 로그인 후 로그아웃 할시
              sessionStorage.removeItem('userEmailHash');        
              location.href("/index");        
             }
             document.getElementById('loginErrorMsg').style.display = 'none';
             emailCheck = obj.emailcertification;
             document.getElementById("loginOKBtn").value = obj.userNickName+ " 님으로 계속";      
             document.getElementById("profile").src = obj.userImage ;
             document.getElementById("login").innerHTML = obj.userNickName + ` 님이 아닌가요?  <a href="#" style="text-decoration: none;color: #1877f2;"onclick="loginContainerChange()">계정 변경</a>` ;                  
             document.getElementById('login_container').style.display = 'none';
             document.getElementById('loginOK_container').style.display = 'block';
          }
        })
      }
  </script>
  <script src="/js/auth/login.js"></script>
</html>
