<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>



<!DOCTYPE html>
<html lang="en">
  <head>
    <script> 
      var userName
      $.ajax({
        url : "/getUserInfo",
        type : "get",
        data: {},
        success : function(obj){             
          sessionStorage.removeItem('userEmailHash');        
        },
        error : function(obj){
          alert("실패");
        }
      })
      function redirectToUpdatePage() {
          window.location.href = "/userUpdate";
      }
      function redirectToDeletePage() {
        window.location.href = "/userDelete";
      }
      function redirectToUpdate1Page() {
        window.location.href = "/userUpdate1";
      } 
      </script>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <link type="text/css" rel="stylesheet" href="css/privacy/navbar.css">
    <link rel="stylesheet" href="/css/privacy/profile.css"/>
    <link rel="stylesheet" href="/css/privacy/update.css" />
    <link rel="stylesheet" href="/css/privacy/modal.css" />
    <link rel="stylesheet" href="/css/privacy/message.css?after"/>
    <script src="js/navbar.js"></script>
 	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>    
    <style>
		body {
			overflow: hidden;
		}
	</style>
    <link
      rel="shortcut icon"
      type="image/x-icon"
      href="/images/loginLogo.png"
    />
    <title>회원수정 - PhoTalk</title>
  </head>
<div class="modal-wrapper"></div>
<body style="overflow-x: hidden">
    <hr />
    <hr class="line1" />
    <hr class="line2" />
    <hr class="line3" />
    <hr class="line4" />
    <hr class="line5" />
    <hr class="line6" />
    <hr class="line7" />

    <span class="Text-Name"> 성명 </span>
    <span class="Text-Nickname">닉네임</span>
    <span class="Text-Email">이메일 주소</span>
    <span class="Text-Phone">핸드폰 번호</span>
    <span class="Text-School">학교</span>
    <span class="Text-Address">집 주소</span>
    <span class="Text-Social">소셜 정보</span>
    <span class="Text-Info">개인 정보</span>
    <span class="Text-Update">탈퇴하기</span>
    <span class="gaininfo">개인정보</span>
    <div class="Text-Update2">
      <a href="/userUpdate1" id="update2">수정하기</a>
    </div>
	
    <nav>
      <div class="navbar">
        <img src="/images/mainLogo.png" alt="Image Button" />
        <a id="PhoTalk" class="navbar-brand" href="profile.html">PhoTalk</a>
        <span><input class="InputBase" placeholder="검색" /></span>
        <img
          id="mainMessageFalse"
          src="/images/mainMessageFalse.png"
          alt="Image Button"
        />
        <img
          id="mainAlarmFalse"
          src="/images/mainAlarmFalse.png"
          alt="Image Button"
        />
        <img
          id="mainProfile2"
          src="/images/mainProfile2.png"
          alt="Image Button"
        />
      </div>
    </nav>
        <!-- 모달창 -->
        <div id="modal" class="modal">
          <div class="modal-content">
            <span class="close">&times;</span>
            <div class="modal-body">
              <a href="profile.jsp" style="text-decoration: none; color: black"
                ><img
                  class="Profile"
                  src="/images/mainProfileModalProfile.svg"
                /><span class="Profile-T">프로필 보기</span></a
              >
              <a href="update.jsp" style="text-decoration: none; color: black"
                ><img class="N-Info" src="/images/mainProfileModalInfo.svg" /><span
                  class="Info-T"
                  >개인 정보</span
                ><br
              /></a>
              <a href="help.jsp" style="text-decoration: none; color: black"
                ><img class="Help" src="/images/mainProfileModalHelp.svg" /><span
                  class="Help-T"
                  >도움말</span
                ><br
              /></a>
              <a href="login.jsp" style="text-decoration: none; color: black"
                ><img
                  class="Logout"
                  src="/images/mainProfileModalLogout.svg" /><span class="Logout-T"
                  >로그아웃</span
                ><br
              /></a>
            </div>
          </div>
        </div>
        <script>
          // 모달창 보이기
          document
            .getElementById("mainProfile2")
            .addEventListener("click", function () {
              document.getElementById("modal").style.display = "block";
            });
          // 모달창 외부를 클릭하면 모달창 닫기
          window.onclick = function (event) {
            if (event.target == document.getElementById("modal")) {
              document.getElementById("modal").style.display = "none";
            }
          };
        </script>
    <!-- 검색 창 -->
    <!-- 네브바 추가할것 !!!! -->
	<table class="userTable" id="userTable">
		<tbody id="ajaxTable">
	          	         	         		          		          		          		          		          		          		          		          		          		          		          		          		          		          		          	
	    </tbody>
	</table>
    
    <span class="Text-Name1"> Name </span>
    <span class="Text-Nickname1">Nickname</span>
    <span class="Text-Email1">Email</span>
    <span class="Text-Phone1">PhoneNumber</span>
    <span class="Text-School1">School</span>
    <span class="Text-Address1">address</span>
    <span class="Text-Socail1" style= "font-family: MalgunGothic;
  position:absolute;
  left: 800px;
  top: 634px;
  font-size: 16px;
  line-height: 1.88;
  letter-spacing: normal;
  text-align: left;
  font-weight: norma;
  color: var(--black); " >UserSocial</span>
	<!-- 프로필 모달 -->
	<table class="profile-modal" id="profile-modal" style="display: none">
		<tbody id="innerProfile">
			<tr onclick="location.href='profile.jsp'">
				<td class="profile-td"><img class= "Profile"src="./images/mainProfileModalProfile.svg"></td>
				<td class="profile-td2">프로필 보기</td>		
    		</tr>   	   				
			<tr onclick="location.href='update.jsp'">
				<td class="profile-td"><img class= "N-Info"src="./images/mainProfileModalInfo.svg"></td>
				<td class="profile-td2">개인 정보</td>		
    		</tr> 		
			<tr onclick="location.href='help.jsp'">
				<td class="profile-td"><img class= "Help"src="./images/mainProfileModalHelp.svg"><span class="Help-T"></td>
				<td class="profile-td2">도움말</td>		
    		</tr> 	
			<tr onclick="showLogout()">			    
				<td class="profile-td"><img class= "Logout" src="./images/mainProfileModalLogout.svg" id="show"></td>				   	
				<td class="profile-td2">로그아웃</td>		
    		</tr> 	    					  	         	         		          		          		          		          		          		          		          		          		          		          		          		          		          		          		          	
	    </tbody>
	</table>
	<!-- 로그아웃 모달 -->	   
	<div class="logout-modal" style="display: none" >
	  <div class="bg" >
	    <div class="logoutBox">
	    	<div class="logoutBtn" style="cursor: pointer" onclick="logout()"><span id="logoutText">로그아웃</span></div>
	    	<div class="logoutCancel" style="cursor: pointer" onclick="showLogout()"><span id="logoutCancelText">취소</span></div>
	    </div>
	  </div>    
	</div>
	<div class="go-update">
    <a
      href="/userUpdate"
      id="go"
      style="
        z-index: 200;
        position: absolute;
        left: 0px;
        top: 150px;
        width: 360px;
        height: 60px;
      "
    ></a>
  </div>

  <div class="go-delete">
    <a
      href="/userdelete"
      id="go-delete"
      style="
        z-index: 200;
        position: absolute;
        left: 0px;
        top: 210px;
        width: 360px;
        height: 60px;
      "
    >
    </a>
  </div>
    
    <div class="side-bar" style="
	position:fixed;
	bottom:0px;
	top: 0px;
	left: 0px;
    width: 360px;
    height: 100%;
    background-color: #e0e0e0;"></div>
    
    

    <div class="Info">
      <img src="./images/InfoIcon.svg" alt="정보" />
    </div>

    <div class="Update">
      <img src="./images/Update.svg" alt="수정" />
    </div>

    <div class="Info-Update">
      <img src="./images/infoUpdate.svg" alt="수정" />
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
   <!--<script src="js/message.js"></script>   
 <script>
    window.onload = function() {
    	ready('email>','mbean.getUserName()'); <퍼센트 이 태그 있었음
    };
  </script> -->
  </body>
</html>
