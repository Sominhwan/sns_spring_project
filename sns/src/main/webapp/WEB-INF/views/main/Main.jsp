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
    <link type="text/css" rel="stylesheet" href="css/main/navbar.css"></link>
    <link type="text/css" rel="stylesheet" href="css/main/sidebar.css"></link>
    <link type="text/css" rel="stylesheet" href="css/main/style.css"></link>
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

<form method="post" name="frm1" action="Main.jsp">
	<input type="hidden" name="gid">
</form>
    <nav>
    <div class = "navbar">
        <img src="images/mainLogo.png" alt="Image Button"/>
	    <a id = "PhoTalk" class = "navbar-brand" href="Main.jsp">PhoTalk</a>
	    	    
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
            <a class = "home" href="Main.jsp">
                <img class = "homeTrue" src="./images/mainHomeTrue.png"  alt="Image Button" width="25" >
                <span class = "sidebar" style="font-weight: bold">홈</span>
            </a>
        </li>
        <li class = "sideLi">
            <a class = "follow" href="follow.jsp">
                <img src="images/mainFollowFalse.png" alt="Image Button" width="25">
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
            <a  class = "profile" href="profile.jsp">
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


<div data-role="page">

    <div class="aaa">
		<table style="height: 100px">
			<tr id="userListContainer">
			</tr>
		</table>
	</div>

	<div class="bbb">
    	<h3>회원님을 위한 추천</h3>
    	<hr>
    	<table>
			<tr id="bbbUserListContainer">			
			</tr>
		</table>
    </div>
    <div class="socialproject">
    	<h5>© 2023 Social Net Work Project</h5>
    </div>

	<div id="cccContainer">
	</div>


	<!-- 햄버거모달 -->
	<div class="modal">
    			<div>
        			<a href="javascript:report('#')"><span id="main-modal-text" style="color: #fd3c56; font-weight: bold;">신고하기</span></a><br>
        			<hr style="background: #d8d8d8; height: 1px; border: 0;">
        			<a href="javascript:share('#')" class="sharebtn"><span id="main-modal-text">공유하기</span></a><br>
        			<hr style="background: #d8d8d8; height: 1px; border: 0;">
        			<a href="javascript:copyUrl('#"><span id="main-modal-text">링크복사</span></a><br>
        			<hr style="background: #d8d8d8; height: 1px; border: 0;">
        			<span id="main-modal-text" class="modal_close">취소</span>
    			</div>
	</div>
</div>
<!-- 화면꺼지게 -->
<div class="overlay">
	<!-- 만들기모달 -->
	<div class="makemodal">
		<div class="maketexttitle">
				<b>게시물 만들기</b><img src="./img/makePostCancelBtn.svg" class="makecancel">	
		</div>
		<hr style="background: #d8d8d8;height: 1px;border:0;">
		<div class="makebody">
			<img src="./img/makePostImage.svg" class="imageposition">
			<img src="./img/makePostVideo.svg" class="imageposition2"><br>
			<h5 class="makebodytext">사진과 동영상을 선택하세요</h5>
			<img src="./img/makePostSelectImage.svg" class="imageposition3" style="cursor: pointer;">
			<img src="./img/makePostSelectVideo.svg" class="imageposition4" style="cursor: pointer;">
		</div> 				
  	</div>
  	<!-- 편집하기모달 -->
  	<form name="postFrm" method="post" enctype="multipart/form-data" >
  	
  	<div class="fixmodal">
		<div class="maketexttitle">
		&nbsp;&nbsp;<b>편집하기</b><img src="./img/makePostBackBtn.svg" class="makeBackBtn" style="cursor: pointer;">
		</div>
		<hr style="background: #d8d8d8;height: 1px;border:0;">
		<div class="makebody">
		    <img src="./img/makePostImage.svg" class="makePostImage">
			<b class="makepostBefore" style="display: none">Before</b>
			<b class="makepostAfter" style="display: none">After</b>
			<!-- 크롭될이미지 -->
			<div class="filebox">
				<label for="imageInput">사진 선택</label> 
		    	<input type="file" accept="image/*" class="imageInput" name="imageName" id="imageInput">		
		    </div>	
			<div class="choicepicture" style="display: none">
				<div class="result"></div>
			</div>
			<div class="choiceafterpicture" style="display: none">
				<img class="cropped" src="./img/binImage.svg" alt="" />	
			</div>
			<!-- input file -->
      		<div class="box" style="display: none">
        		<div class="options hide">
          		<input type="number" class="img-w" value="300" min="100" max="400" style="display: none"/>
        		</div>
        		<!-- save btn -->
        		<button class="btn save hide" id="saveBtn" style="border-radius: 5px;cursor: pointer;">저장하기</button>
      		</div>
			<img src="./img/makePostInsertBtn.svg" class="makepostInsert" style="display: none">
		</div>				
  	</div>
  	</form>
  	<!-- 동영상모달 -->
  	<form name="videoFrm" method="post" enctype="multipart/form-data" >
  	<div class="videomodal">
		<div class="maketexttitle">
			<b style="position:relative;  margin-left: 10px;">동영상모달</b><img src="./img/makePostBackBtn.svg" class="makevideoBackBtn" style="cursor: pointer;">
		</div>
		<hr style="background: #d8d8d8;height: 1px;border:0;">
		<div class="makebody">
			<h5 class="videotitle">동영상을 선택하세요</h5>
			<div class="choicevideo">
				<input type="file" accept="video/*" id="videoElement" name="videoElement">
			</div>
			<img src="./img/makePostInsertBtn.svg" class="videopostInsert">
		</div>				
  	</div>
  	</form>
  	<!-- 게시물완료모달 -->
  	<div class="postcomplete">
		<div class="maketexttitle">
			<b class="postcomtitle">게시물이 올라갔습니다.</b>
		</div>
		<hr style="background: #d8d8d8;height: 1px;border:0;">
		<div class="makebody">
			<img src="./img/makePostCheckIcon.svg" class="makepostComple">
			<br>
			<span class="bodycomple">게시물이 올라갔습니다.</span>
			<br>
			<img src="./img/makePostCheckBtn.svg" class="makepostCheck" style="cursor: pointer;">
		</div>				
  	</div>
</div>

<!-- 공유하기모달 -->
<div class="sharemodal">
  <div class="share-header">
    <span>공유하기</span>
    <div class="sharecancel">x</div>
  </div>
  <hr style="background: #d8d8d8;height: 1px;border:0;">
  	<a href="#" class="naver-share-link" target="_blank" alt="Share on Naver">
  		<img src="./img/postShareNaver.jpg" class="postShareNaver" />
	</a>
  
  	<a href="#" id="btnKakao" onclick="postShareKakao()">
  		<img src="./img/postShareKakao.jpg" class="postShareKakao"/>
  	</a>
</div>
<div class="updateComment">
	
</div>
<form method="post" name="frm">
		<input type="hidden" name="userNickName">
		<input type="hidden" name="postId">
		<input type="hidden" name="commentId">
		<input type="hidden" name="userEmail">
		<input type="hidden" name="friendEmail">
		<input type="hidden" name="comment">
		
</form>
    <!-- js 추가 -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/0.8.1/cropper.min.js"></script>    
	<script src="/js/main/main.js"></script>
</body>
</html>