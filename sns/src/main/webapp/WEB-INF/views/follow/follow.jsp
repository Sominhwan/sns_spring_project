<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="com.project.my.module.userRole.entity.UserInfoEntity" %>
<%@ page import="com.project.my.module.userRole.repository.UserRepository" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Photalk</title>
    <link rel="shortcut icon" type="image/x-icon" href="./images/mainLogo.png" />
    <!-- 네브바 추가할것 !!!! -->    
    <link type="text/css" rel="stylesheet" href="css/follow/navbar.css"></link>
    <link type="text/css" rel="stylesheet" href="css/follow/sidebar.css"></link>
    <link type="text/css" rel="stylesheet" href="css/follow/follow.css"></link>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"
    />
    <!-- Cropper CSS -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/cropper/2.3.4/cropper.min.css"
    />
 	<script src="https://cdn.jsdelivr.net/npm/cropperjs@1.5.12/dist/cropper.min.js"></script>
 	<script type="text/JavaScript" src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
 	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
 	
</head>
<div class="modal-wrapper"></div>
<body style="overflow-x: hidden">

<form method="post" name="frm1" action="follow.jsp">
	<input type="hidden" name="gid">
</form>
    <nav>
    <div class = "navbar">
        <img src="images/mainLogo.png" alt="Image Button"/>
	    <a id = "PhoTalk" class = "navbar-brand" href="/main">PhoTalk</a>
	    	    
	    <form method="post" id="navSearch" >
        	<span><input type="text" class = "InputBase"  placeholder="검색" name="searchWord" onkeyup="searchUser()" autocomplete="off"></span>
        	<input type="text" style="display:none;"/>
        </form>
        <!-- 모달창 -->
        <div class="absol">
		<img class="mainMessageButton" id ="mainMessageFalse" src="images/mainMessageFalse.png" onclick="clickChatBtn('#')" alt="Image Button" style="cursor: pointer"/>
        <div id="alarm" class="alarm">
        <span class="alarmBalloon"></span>
        </div>
        </div> 
        <img class="mainMessageButton" id = "mainAlarmFalse" src="images/mainAlarmFalse.png" onclick="clickFollowBtn()" alt="Image Button" style="cursor: pointer"/>
    	<img id = "mainProfile2" src="./images/mainProfile2.png" alt="Image Button" onclick="profileModal()" style="cursor: pointer"/>
		
    </div>	   
</nav>
    <!-- 검색 창 -->
    <!-- 네브바 추가할것 !!!! -->
	<table class="userTable" id="userTable">
		<tbody id="ajaxTable">
	          	         	         		          		          		          		          		          		          		          		          		          		          		          		          		          		          		          	
	    </tbody>
	</table>
<!-------------------- 사이드바 --------------------->
    <ul class = "sideUl">
        <li class = "sideLi">
            <a class = "home" href="/main">
                <img class = "homeTrue" src="./images/mainHomeFalse.png"  alt="Image Button" width="25" >
                <span class = "sidebar" style="font-weight: bold">홈</span>
            </a>
        </li>
        <li class = "sideLi">
            <a class = "follow" href="/follow">
                <img src="images/mainFollowTrue.png" alt="Image Button" width="25">
                <span class = "sidebar">팔로우</span>
            </a>
        </li>
        <li class = "sideLi">
            <a  class = "search" href="quest.jsp">
                <img src="images/mainExploreFalse.png" alt="Image Button" width="25" >
                <span class = "sidebar">탐색</span>
            </a>
        </li>      
        <li class = "sideLi">
            <a  id="make-post" href="#">
                <img src="images/mainMakePostFalse.png" alt="Image Button" width="25" >
                <span class = "sidebar">만들기</span>
            </a>
        </li>    
        <li class = "sideLi"> 
            <a  class = "profile" href="/profile?userEmail">
                <img src="images/mainProfile2.png" alt="Image Button" width="25">
                <span class = "sidebar">프로필</span>
            </a>
        </li>                      
        <%
        	for(int i=0; i<27; i++){
        		%>
        		<br>
        		<%
        	}
        %>
        <dt>
        	&nbsp;
        	<a href="#소개"><span class="leftintroduce">소개</span></a>
        	<a href="#채용"><span class="leftintroduce">채용안내</span></a>
        	<a href="#이용"><span class="leftintroduce">이용약관</span></a>
        	<a href="#도움"><span class="leftintroduce">도움말</span></a>
        	<a href="#운영"><span class="leftintroduce">운영정책</span></a>
        	<a href="#위치"><span class="leftintroduce">위치</span></a>
        </dt>
        <dt>
        	&nbsp;
        	<span class="leftintroduce">사이트맵 © 2023 Social Net Work Project</span>
        </dt>        
    </ul>
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

<div class="followrequest" id="followrequest">
</div>
<hr style="position: fixed; top:550px; left: 345px; width: 1530px; background: #d8d8d8; height: 1px; border: 0">
<div class="followtext" style="position: absolute; top: 520px; left: -43px;">
	<h5>추천 팔로우</h5>
</div>
<div class="recommend" id="recommend">

</div>

    


<form method="post" name="frm">
		<input type="hidden" name="userEmail">
		<input type="hidden" name="friendEmail">
		<input type="hidden" name="nickName">
</form>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/0.8.1/cropper.min.js"></script> 
	<script src="js/navbar.js"></script>
	<script src="js/follow/follow.js"></script>   
    <script type="text/javascript">
    	function follow(emailnick){
    		const followmodal = document.querySelector('.followmodal');
            const followBtns=document.querySelectorAll('.followBtn');
            const followCheck=document.querySelector('.followCheck');
            const sentence=emailnick;
 			const [friendEmail,nickName,userEmail]=sentence.split(",");
 			document.frm.friendEmail.value=friendEmail;
 			document.frm.nickName.value=nickName;
 			document.frm.userEmail.value=userEmail;
            document.querySelector("#followNickName").textContent = nickName;
            for (var i = 0; i <followBtns.length ; i++) {
                followBtns[i].addEventListener('click', () => {
                	$.ajax({
                	    url: 'FollowAllow',
                	    type: 'POST',
                	    data: {
                	        userEmail: userEmail,
                	        friendEmail: friendEmail
                	    },
                	    success: function(response) {
                	        console.log(response);
                	        followmodal.style.display = 'block';
                	        
                	    },
                	    error: function(xhr, status, error) {
                	        console.log("Error: " + error);
                	    }
                	});
                });
            }
            followCheck.addEventListener('click', () => {
                followmodal.style.display = 'none';
                location.reload();
            });
    	}
    	function followCancel(emailnick){
    		const followCancelBtns=document.querySelectorAll('.followCancelBtn');
            const sentence=emailnick;
 			const [friendEmail,nickName,userEmail]=sentence.split(",");
 			document.frm.friendEmail.value=friendEmail;
 			document.frm.nickName.value=nickName;
 			document.frm.userEmail.value=userEmail;
            document.querySelector("#followNickName").textContent = nickName;
            for (var i = 0; i <followCancelBtns.length ; i++) {
            	followCancelBtns[i].addEventListener('click', () => {
                	$.ajax({
                	    url: 'FollowCancel',
                	    type: 'POST',
                	    data: {
                	        userEmail: userEmail,
                	        friendEmail: friendEmail
                	    },
                	    success: function(response) {
                	        console.log(response);
                	        location.reload()
                	    },
                	    error: function(xhr, status, error) {
                	        console.log("Error: " + error);
                	    }
                	});
                });
            }
    	}
    	
    </script>
</body>
</html>