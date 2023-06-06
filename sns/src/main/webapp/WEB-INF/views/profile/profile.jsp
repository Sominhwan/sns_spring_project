
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/css/profile/profile.css" />
    <link rel="stylesheet" href="/css/profile/navbar.css" />
    <link rel="stylesheet" href="/css/profile/sidebar.css" />
    <link rel="stylesheet" href="/css/chat/message.css" />
    <link rel="shortcut icon" type="image/x-icon" href="images/mainLogo.png" />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>프로필 - PhoTalk</title>
    <script type="text/javascript">
    	function save(){
    		var frm = "";
        $.ajax({
            type: "get",
            url: "/loginProfile.action",
            data: {},
            success: function(obj) {
                frm = obj.userEmail;
                var frm2 = document.getElementById('contents').value;
                console.log(frm2);
                console.log(frm);
                $.ajax({
                    url: "/profile-guestBook",
                    type: "post",
                    data: {
                        gbComment : frm2,
                        userEmail : frm
                    },
                    success: function(response) {
                        $(".up_text3").text(frm2);
                    },
                    error: function(error) {
                        console.log("실패");
                    }
                });
            },
            error: function(error) {
                // 오류 처리를 위한 동작을 정의
                console.log("실패2")
            }
        });
            
    	}
    	function photo() {
        var frm = "";
        $.ajax({
            type: "get",
            url: "/loginProfile.action",
            data: {},
            success: function(obj) {
                frm = obj.userEmail;
                var frm2 = document.getElementById('img__preview').files[0];
                var formData = new FormData();
                formData.append('file', frm2);
                formData.append('userEmail', frm);
                console.log(frm2);
                console.log(frm);
                $.ajax({
                    url: "/profile-upload",
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    enctype:"multipart/form-data",
                    success: function(response) {
                        console.log(response);
                        $(".profile_mainprofile").attr("src", response);
                    },
                    error: function(error) {
                        console.log("실패");
                    }
                });
            },
            error: function(error) {
                // 오류 처리를 위한 동작을 정의
                console.log("실패2")
            }
        });
        }
    	function bgimage(){
    		var frm = "";
        $.ajax({
            type: "get",
            url: "/loginProfile.action",
            data: {},
            success: function(obj) {
                frm = obj.userEmail;
                var frm2 = document.getElementById('img__preview2').files[0];
                var formData = new FormData();
                formData.append('file', frm2);
                formData.append('userEmail', frm);
                console.log(frm2);
                console.log(frm);
                $.ajax({
                    url: "/profile-bgUpload",
                    type: "post",
                    data: formData,
                    processData: false,
                    contentType: false,
                    enctype:"multipart/form-data",
                    success: function(response) {
                        console.log(response);
                         $(".mainBanner").css("background-image", "url('" + response + "')");
                    },
                    error: function(error) {
                        console.log("실패");
                    }
                });
            },
            error: function(error) {
                // 오류 처리를 위한 동작을 정의
                console.log("실패2")
            }
        });
    	}
    </script>
</head>
<div class="modal-wrapper"></div>
<body style="overflow-x: hidden">
<!-------------------- 상단바 --------------------->
<nav>
    <div class = "navbar">
         <img src="images/mainLogo.png" alt="Image Button"/>
	     <a id = "PhoTalk" class = "navbar-brand" href="/main">PhoTalk</a>
	     <img src="images/mainSearch.svg" alt="mainSearch" style="position:relative; left:180px;"/>
	    <form method="post" id="navSearch" >
        	<span><input type="text" class = "InputBase"  placeholder="검색" name="searchWord" onkeyup="searchUser()" autocomplete="off"></span>
        	<input type="text" style="display:none;"/>
        </form>
        <!-- 모달창 -->
        <div class="absol">
        <img class="mainMessageButton" id ="mainMessageButtonfalse" src="images/mainMessageFalse.png" onclick="clickChatBtn()" alt="Image Button" style="cursor: pointer"/>
        <div id="alarm" class="alarm">
        <span class="alarmBalloon"></span>
        </div>
        </div>             
        <img class="mainMessageButton" id = "mainAlarmFalse" src="images/mainAlarmFalse.png" onclick="clickFollowBtn()" alt="Image Button" style="cursor: pointer"/>
    	<img id = "mainProfile2" src="./images/mainProfile2.png" alt="Image Button" onclick="profileModal()" style="cursor: pointer"/>
    </div>
</nav>
<!-------------------- 사이드바 --------------------->
    <ul class = "sideUl">
        <li class = "sideLi">
            <a class = "home" href="/main">
                <img src="images/mainHomeFalse.png"  alt="Image Button" width="25" >
                <span class = "sidebar">홈</span>
            </a>
        </li>
        <li class = "sideLi">
            <a class = "follow" href="#">
                <img src="images/mainFollowFalse.png" alt="Image Button" width="25">
                <span class = "sidebar">팔로우</span>
            </a>
        </li>
        <li class = "sideLi">
            <a  class = "search" href="#">
                <img src="images/mainExploreFalse.png" alt="Image Button" width="25" >
                <span class = "sidebar">탐색</span>
            </a>
        </li>
        <li class = "sideLi">
            <a  class = "make" href="#">
                <img src="images/mainMakePostFalse.png" alt="Image Button" width="25" >
                <span class = "sidebar">만들기</span>
            </a>
        </li>
        <li class = "sideLi"> 
            <a  class = "profile" href="/profile?userEmail">
                <img src="images/mainProfile2.png" alt="Image Button" width="25" >
                <span class = "sidebar" style = "font-weight: bold;">프로필</span>
            </a>
        </li>
    </ul>
<!-------------------- 메인페이지 --------------------->

<!------------- 상단부분 ----------------->

<div class = mainProfile>
    <div class = mainBanner style="background-size: cover; background-position: center;	">
    	<div class = mainBanner2>
    		<div class = area_up1>
            <!-- 프로필 사진 -->
            <div class = area_up2>
                <img class= "profile_mainprofile" src="" style = " width: 140px; height:140px; border-radius : 50%;" onerror="this.src ='images/profileProfileLogo.png'">
			</div>
            <div class = area_up3>
                <!-- 이름 -->
                <div class = area_up4>
					<span class = "up_text userName"></span>
                </div>
                <!-- 이메일 -->
                <div class = area_up5>
                	<span class = "up_text2 userEmail"></span>
                </div>
                <!-- 방명록 -->
                	<div class = area_up6>
                	<span class = up_text3></span>
                	</div>
                </div>
            </div>
        <div class = area_up7>
            <!-- <div>
                <button type="button" class = "profileBtn3" id="show">
                <img src="images/profileBtn3.png" class = "profilepng">
                </button>
  				<button type="button" class="profileBtn2" id="show2"><img src="images/profileBtn2.png" class="profilepng"></button>
                <button type="button" class = "profileBtn1">
                	<img src="images/profileBtn1.png" class = "profilepng"  id="show3">
                </button>
            </div> -->
        </div>
      </div>
   </div>
</div>
<!-- 방명록 팝업창 -->
<div class = "background">
	<div class = "window">
		<div class = "popup">
			<div class = "popup-header-div">
				<span id = "popup-header">방명록 작성</span>
				<a id = "popupclose4" ><img src = "images/profilePopupClose.svg"> </a>
			</div>
			<div class ="popup-body">
				<div class = "popup-content">
					<form name = "postFrm" method = "post" enctype="multipart/form-data">
					<input type="hidden" name="userEmail" value="">
					<textarea class = "popup-textarea" id = "contents" placeholder="방명록 작성 양식입니다.(공백 포함 50자까지 작성 가능)" maxlength = "50"></textarea>
					</form>
				</div>
			</div>
			<div class = "popup-bottom">
				<button id = "popupclose" type = "submit" onclick = "save()"><img src = "images/profileCoverImageSelectBtn.svg"></button>
			</div>
		</div>
	</div>
</div>

<!-- 프로필 사진 팝업 -->
<div class = "background2">
	<div class = "window2">	
		<div class = "popup2">
			<div class = "popup-header-div">
				<span id = "popup-header2">프로필 변경</span>
				<a id = "popupclose3" ><img src = "images/profilePopupClose.svg"> </a>
			</div>
			<div class ="popup-body">
				<form name = "postPhoto" method="post" enctype="multipart/form-data">
					<div class = "photopreview">
						<img src="images/profile.svg" id="img__wrap" onerror="this.src='images/profile.svg'" src="" width="280px" height="280px" />
					</div>
					<div class = "inputPhoto">
						<input type="file" name="userProfile" id="img__preview"/>
						<input type="hidden" name="photoId" value=""/>
					</div>
				</form>
			</div>
			<div class = "popup-bottom">
				<div>
					<button id="popupclose2" type="submit" onclick = "photo()"><img src = "images/profileCoverImageSelectBtn.svg"></button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 배경 사진 팝업 -->
<div class = "background3">
	<div class = "window3">	
		<div class = "popup3">
			<div class = "popup-header-div">
				<span id = "popup-header3">배경사진 변경</span>
				<a id = "popupclose6" ><img src = "images/profilePopupClose.svg"> </a>
			</div>
			<div class ="popup-body">
				<form name = "postBgPhoto" method="post" enctype="multipart/form-data">
					<div class = "photopreview">
						<img src="images/profile.svg" id="img__wrap2" onerror="this.src='images/profile.svg'" src="" width="680px" height="280px" />
					</div>
					<div class = "inputPhoto">
						<input type="file" name="backimage" id="img__preview2"/>
						<input type="hidden" name="backId" value=""/>
					</div>
				</form>
			</div>
			<div class = "popup-bottom">
				<div>
					<button id="popupclose5" type="submit" onclick = "bgimage()"><img src = "images/profileCoverImageSelectBtn.svg"></button>
				</div>
			</div>
		</div>
	</div>
</div>


<!------------- 하단부분 ----------------->



<div class = profile_under>
    <div class = "area1">
        <div class = "area2">
            <!-- 프로필 -->
            <div class = "profile_content">
                <div class = "area3" style="font-size: 22px;">
                    <span class="Text-State">정보</span>
                </div>
                <div class= "area4">
                	<div class = "area4_0">
                		<div class = "profile_img">
                            <img class = "profile_icon" src="images/profileNameIcon.svg" alt="img">
                            <img class = "profile_icon" src="images/profileGenderMan.svg" alt="img" >
                            <!-- <img class = "profile_icon" src="images/profileGenderNull.png" alt="img" >
                            <img class = "profile_icon" src="images/profileGenderNull.png" alt="img" > -->
                            <img class = "profile_icon" src="images/profileNickName.svg" alt="img">
                            <img class = "profile_icon" src="images/profileEmailIcon.svg" alt="img" style = "margin-top: 6px;">
                            <img class = "profile_icon" src="images/profilePhoneIcon.svg" alt="img">
                            <img class = "profile_icon" src="images/profileSchoolIcon.svg" alt="img">
                            <img class = "profile_icon" src="images/profileHomeIcon.svg" alt="img">
                            <img class = "profile_icon" src="images/profileSocialInfoIcon.svg" alt="img">
                        </div>
                	</div>
                    <div class = "area4_1">
                        <div class = "profile_name">
                            <ul class = "profile_ul">
                                <li>
                                	<span class = "profile_text" >성명</span>
                                	<span class = "profile_info userName" style = "margin-left: 130px;"></span>
                                </li>
                                <li style="padding-top: 2px;">
                                	<span class = "profile_text">성별</span>
                                	<span class = "profile_info userGender" style = "margin-left: 130px;"></span>
                                </li>
                                <li style="padding-top: 5px;">
                                	<span class = "profile_text" >닉네임</span>
                                	<span class = "profile_info userNickName" style = "margin-left: 113px;"></span>
                                </li>
                                <li style="padding-top: 3px;">
                                	<span class = "profile_text">이메일 주소</span>
                                	<span class = "profile_info userEmail" style = "margin-left: 75px;"></span>
                                </li>
                                <li>
                                	<span class = "profile_text">휴대폰 번호</span>
                                	<span class = "profile_info userPN" style = "margin-left: 75px;"></span>
                                </li>
                                <li>
                                	<span class = "profile_text">학교</span>
                                	<span class = "profile_info userSchool" style = "margin-left: 125px;"></span>
                                </li>
                                <li>
                                	<span class = "profile_text">거주지역</span>
                                	<span class = "profile_info userAddress" style = "margin-left: 93px;"></span>
                                </li>
                                <li>
                                	<span class = "profile_text">소셜정보</span>
                                	<span class = "profile_info userSocial" style = "margin-left: 93px;"></span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class= "area5" >
                    <button type="button" class = "update_btn" >
                        <img class = "update_btn" src="images/profileUpdateIcon.svg" alt="Image Button" >
                        <span class = "update">수정하기</span>
                    </button>
                </div>
            </div>
            <!--팔로우 부분-->
            <div class = "profile_follow">
				<div class = "follow_area1" style="font-size: 22px;">
                    <span class="Text-State">팔로우</span>
                </div>
                <div class = "follow_area2">
                    팔로우<span class="Text-State2"></span>명
                </div>
                <div class = "follow_area3">
                </div>
            </div>
        </div>
        <!--게시글 자리-->
        <div class = "area_right">
            <div class = "area_right_1">
                <div class = "area3" style="font-size: 22px;">
                    <span class="Text-State">게시물</span>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/profile/profile.js"></script>
<script src="/js/chat/message.js"></script>
<script>
    var FriendEmail = "${userEmail}";
    // console.log(userEmail);
    // setFriendInfo(FriendEmail);
    window.onload = function() {
    	ready();
    };
</script>
</body>
</html>