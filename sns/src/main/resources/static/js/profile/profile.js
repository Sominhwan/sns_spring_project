var userEmail = "";
// var friendEmail = "";
var profile = "";

function show(){
	document.querySelector(".background").className = "background show"
}

function popupclose () { 
    document.querySelector(".background").className = "background";
}

function popupclose2 () { 
    document.querySelector(".background2").className = "background2";
}

function popupclose4 () { 
  document.querySelector(".background").className = "background";
}



// -----------------------------------------------------

function show2(){
	document.querySelector(".background2").className = "background2 show"
}

function popupclose3 () { 
    document.querySelector(".background2").className = "background2";
}

function check2 () { 
  document.querySelector(".background2").className = "background2";
}



//-----------------------------------------------------------------

function show3(){
	document.querySelector(".background3").className = "background3 show"
}

function popupclose5 () { 
    document.querySelector(".background3").className = "background3";
}

function popupclose6 () { 
    document.querySelector(".background3").className = "background3";
}



// ----------------------------------------------------------------------

/*function show4(){
	document.querySelector(".background4").className = "background4 show"
}

function popupclose5 () { 
    document.querySelector(".background4").className = "background4";
}


document.querySelector("#show4").addEventListener('click', show4);
document.querySelector("#popupclose5").addEventListener('click', popupclose5);*/

// 프로필 사진 //
$("#img__preview").on("change", function(e){
		var f=e.target.files[0];
		if(!f.type.match("image*")){
			alert("이미지만 첨부할 수 있습니다..");
			$("#img__preview").val('');
			return;
		}

		// f.size = 1024*1024*2
		if(f.size>1024*1024*2){
			alert("2mb까지의 사진만 업데이트 할 수 있습니다.");
			$("#img__preview").val('');
			return;
		}
		var reader=new FileReader();

		reader.onload=function(e){
			$("#img__wrap").attr("src",e.target.result);
		}
		reader.readAsDataURL(f); //비동기적 진행(파일 읽기)
	});
	
// 배경 사진 //
$("#img__preview2").on("change", function(e){
		var f=e.target.files[0];
		if(!f.type.match("image*")){
			alert("이미지만 첨부할 수 있습니다..");
			$("#img__preview2").val('');
			return;
		}

		// f.size = 1024*1024*2
		if(f.size>1024*1024*2){
			alert("2mb까지의 사진만 업데이트 할 수 있습니다.");
			$("#img__preview2").val('');
			return;
		}
		var reader=new FileReader();

		reader.onload=function(e){
			$("#img__wrap2").attr("src",e.target.result);
		}
		reader.readAsDataURL(f); //비동기적 진행(파일 읽기)
	});


// 회원정보 받아오기
$.ajax({
	type: "get",
	url: "/loginProfile.action",
	data: {
	},
	success: function(obj) {
		userEmail = obj.userEmail;
		if(userEmail == FriendEmail || FriendEmail ==""){
			myPageButton();
			$(".userEmail").text(obj.userEmail);
			$(".userNickName").text(obj.userNickName);
			$(".userName").text(obj.userName);
			$(".userGender").text(obj.userGender);
			$(".userSchool").text(obj.userSchool);
			$(".userPN").text(obj.userPN);
			$(".userAddress").text(obj.userAddress);
			$(".userSocial").text(obj.userSocial);
			bgupdate(obj.userEmail);
			friendList(obj.userEmail);
			profile = obj.userImage;
			console.log(FriendEmail);
		}else{
				$.ajax({
				type: "post",
				url: "/profile-friendInfo",
				data: { userEmail : FriendEmail
				},
				success: function(obj2) {
					friendButton();
					$(".userEmail").text(obj2[0].userEmail);
					$(".userNickName").text(obj2[0].userNickName);
					$(".userName").text(obj2[0].userName);
					$(".userGender").text(obj2[0].userGender);
					$(".userSchool").text(obj2[0].userSchool);
					$(".userPN").text(obj2[0].userPN);
					$(".userAddress").text(obj2[0].userAddress);
					$(".userSocial").text(obj2[0].userSocial);
					bgupdate(FriendEmail);
					friendList(FriendEmail);
					console.log(obj2);
				},
				error: function(error) {
					console.log("실패");
				}		
			})
		}
	}
});

// 회원 사진, 배경사진 불러오기
function bgupdate(userEmail){
	$.ajax({
		type: "post",
		url: "/profile-bgImage",
		data: {
			userEmail : userEmail
		},
		success: function(obj2){
			$(".mainBanner").css("background-image", "url('" + obj2[0].gbBackGroundImage + "')");
			$(".profile_mainprofile").attr("src", obj2[0].userImage);
			$(".up_text3").text(obj2[0].gbComment);
		},
		error: function(error) {
			console.log("실패");
		}
	});
}

// 친구리스트
function friendList(userEmail){
	$.ajax({
		type: "post",
		url: "/profile-friendList",
		data: {
			userEmail : userEmail
		},
		success: function(obj2){
			console.log(obj2)
			$(".Text-State2").text(obj2.length);
			for(var i=0; i<obj2.length; i++){
				friendListProfile(obj2[i].friendEmail,obj2[i].friendName,obj2[i].userImage);
			}
		},
		error: function(error) {
			console.log("실패");
		}
	});
}

function friendListProfile(friendEmail,friendName,userImage){
	$('.follow_area3').append(
		'<div class="follow_area4">' +
		'<a href="#" onclick="sendTitle(\''+ friendEmail +'\')">' +
		'<img src="'+userImage+'" style="width: 130px; height: 130px; border-radius: 10px"onerror="this.src =\'/images/profile.svg\'">' +
		'</a>' + friendName +
		'</div>'
	);
}

function sendTitle(friendEmail) {
	window.location.href = "/profile?userEmail=" + friendEmail;
}

function myPageButton(){
	$('.area_up7').append(
		'<div>' +
		'<button type="button" class = "profileBtn3" id="show">' +
			'<img src="images/profileBtn3.png" class = "profilepng">' +
		'</button>' +
		'<button type="button" class="profileBtn2" id="show2"><img src="images/profileBtn2.png" class="profilepng"></button>' +
		'<button type="button" class = "profileBtn1">' +
			'<img src="images/profileBtn1.png" class = "profilepng"  id="show3">' +
		'</button>' +
		'</div>'
	)
	document.querySelector("#show").addEventListener('click', show);
	document.querySelector("#popupclose").addEventListener('click', popupclose);
	document.querySelector("#popupclose2").addEventListener('click', popupclose2);
	document.querySelector("#popupclose4").addEventListener('click', popupclose4);
	document.querySelector("#show2").addEventListener('click', show2);
	document.querySelector("#popupclose3").addEventListener('click', popupclose3);
	// document.querySelector("#check2").addEventListener('click', check2);
	document.querySelector("#show3").addEventListener('click', show3);
	document.querySelector("#popupclose5").addEventListener('click', popupclose5);
	document.querySelector("#popupclose6").addEventListener('click', popupclose6);
}

function friendButton(){
	$('.area_up7').append(
		'<div>' +
		'<button type="button" class = "profileBtn3" id="show44">' +
			'<img src="images/profileBtn3.png" class = "profilepng">' +
		'</button>' +
		'</div>'
	)
}

document.querySelector("#check2").addEventListener('click', check2);