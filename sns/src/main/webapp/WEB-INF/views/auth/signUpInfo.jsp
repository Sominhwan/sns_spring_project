<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/auth/signUpInfoPage.css" />
    <link rel="stylesheet" href="/css/auth/loading.css" />
    <link rel="shortcut icon" type="image/x-icon" href="images/loginLogo.png" />
    <title>가입하기 - PhoTalk</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>      
      var selectBox = function(value){
    	  document.getElementById('userGender').value = value;
    	}
      /* 페이지 전환 */
      
      function change(){
        const userEmail = $('input[name=userEmail]').val();
        const userName = $('input[name=userName]').val();
        const gender = $('input[name=userGender]').val();
        const userNickName = $('input[name=userNickName]').val();
        const userPhoneNum = $('input[name=userPhoneNum]').val();
        const password = $('input[name=password]').val();
        const agreement = $('input[name=agreement]').val();
        var regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        var regName = /^[가-힣a-zA-Z]{2,15}$/;
        var regPhoneNum = /^[0-9]+/g;
        var regPassword = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,20}$/;

        if(!regEmail.test(userEmail)){
          $('#userEmail').focus();
          document.getElementById("errorAlarmText").innerHTML = "올바른 이메일 형식을 입력하세요.";
          $('.signUp-modal').css('display', 'block');
          return false;
        }
        if(!regName.test(userName)){
           $('#userName').focus();
           document.getElementById("errorAlarmText").innerHTML = "올바른 성명을 입력하세요.";
           $('.signUp-modal').css('display', 'block');
           return false;
        }
        if(!regPhoneNum.test(userPhoneNum) ){
           $('#userPhoneNum').focus();
           document.getElementById("errorAlarmText").innerHTML = "올바른 휴대폰 번호를 입력하세요.";
           $('.signUp-modal').css('display', 'block');
           return false;
        }  
        if(!regPassword.test(password)){
          $('#password').focus();
          document.getElementById("errorAlarmText").innerHTML = "올바른 비밀번호 형식을 입력하세요.";
          $('.signUp-modal').css('display', 'block');
          return false;
        }
        $.ajax({
          url : "/signUpInfoCheck",
          type : "post",
          data: {
            userEmail: userEmail,
            userName: userName,
            gender: gender,
            userNickName: userNickName,
            userPhoneNum: userPhoneNum,
            password: password,
            agreement: agreement,            
          },
          success : function(obj){
            if(obj.error !=null){ // 유효성, 메일 보내기 실패
              document.getElementById("errorAlarmText").innerHTML = obj.error;
              $('.signUp-modal').css('display', 'block');
            }
            if(obj.success !=null){ // 회원가입, 메일 전송 성공
              location.replace("/signUpOk?userEmail="+obj.success); // 회원가입 완료 페이지 이동
            }
          },
          error : function(){
            alert("올바르지 않은 접근입니다.");
          }
        })  

    	  //document.signUp.submit(); 
      }

      $(document).ready(function() { // 광고 동의 체크 여부 확인
        var i = 0;
        <c:forEach items="${arr}" var="arr" varStatus="status">
           i++;
        </c:forEach>
        if(i==3){
          const agreement = $('input[name=agreement]').val('1');
        }else{
          const agreement = $('input[name=agreement]').val('0');
        }
      });
    </script>
  </head>
  <!-- 로딩바 -->
  <div id="loading" style="display: none">
    <div id="loading_bar">
      <!-- 로딩바의 경로를 img 태그안에 지정해준다. -->
      <img src="/images/Spin-loading.gif" style="width: 100px;"/>
    </div>
  </div>
  <body>
    <!-- 회원가입 모달 -->	   
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
      <img src="/images/joinLogo.png" id="joinLogo" />
      <a href="/index" id="logo">PhoTalk</a>
      <ul>
        <li><a href="/signUp" class="signUp">회원가입</a></li>
        <li>|</li>
        <li><a href="/index" class="signUp">로그인</a></li>
      </ul>
    </nav>
    <!-- 가입정보 입력 컨텐츠 -->
    <div class="signUpInfo-text">
      <span id="signUp-text">가입정보를 입력해주세요.</span>
    </div>
    <!-- 가입정보 입력 폼 -->
    <div class="signUpInfo-content">
      <form action="" method="POST" name="signUp">
        <div class="input-box">
          <input -webkit-autofill
            id="userEmail"
            type="text"
            name="userEmail"
            placeholder="이메일을 입력해 주세요"
            maxlength="60"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
          />
          <label for="userEmail">이메일을 입력해 주세요</label>
        </div>
        <div class="input-box">
          <input -webkit-autofill
            id="userName"
            class="userName"
            type="text"
            name="userName"
            placeholder="성명"
            maxlength="60"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
          />
          <label for="userName">성명</label>
        </div>
        <select name="gender" id="select-box" onchange="selectBox(this.value)" >
          <option selected disabled>=성별=</option>
          <option value="남성">남성</option>
          <option value="여성">여성</option>
        </select>

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
        <div class="input-box">
          <input -webkit-autofill
            id="userPhoneNum"
            type="text"
            name="userPhoneNum"
            placeholder="휴대폰 번호('-' 없이)"
            maxlength="11"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
          />
          <label for="userPhoneNum">휴대폰 번호('-' 없이)</label>
        </div>
        <div class="input-box">
          <input -webkit-autofill
            class="password"
            id="password"
            type="password"
            name="password"
            placeholder="비밀번호를 입력해 주세요 (8자 이상)"
            maxlength="50"
            autocomplete="false"
            style="-webkit-box-shadow: 0 0 0 1000px #fff inset"
          />
          <label for="password">비밀번호를 입력해 주세요(영문,숫자 포함 8자 이상)</label>
          <span id="keyShow">
            <img src="https://velog.velcdn.com/images/thalsghks/post/7910658e-94d5-4e16-b24a-a19ad98f6e70/image.svg" alt="eye" />
          </span>
        </div>
        <input type="hidden" id="userGender" name="userGender" value="없음">
        <input type="hidden" name="agreement" value="">
        <input
          class="next-button"
          id="next-button"
          name="next-button"
          type="button"
          disabled
          value="다음"
          onclick="change()"
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
  <script src="/js/auth/signUpInfo.js"></script>
  <script src="/js/auth/loading.js"></script>
</html>

