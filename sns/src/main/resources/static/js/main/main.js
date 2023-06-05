function hamberger(userEmail){//햄버거 모달 스크립트 완료
	const modal = document.querySelector('.modal');
	const hams = document.querySelectorAll('.ham');
	const cancelBtn = document.querySelector('.modal_close');
	for(var i=0; i<hams.length; i++){
	hams[i].addEventListener('click', () => {
		modal.style.display = 'block';
		});
	}
	cancelBtn.addEventListener('click', () => {
		modal.style.display = 'none';
	});
}
//팔로우리스너
$(document).on('click', '.folbox a.follow-btn', function() {
	var userEmail = $(this).data('useremail');
	var friendEmail = $(this).data('friendemail');
	var followBtn = $(this).closest('.folbox');
	dofriend(userEmail, friendEmail, followBtn);
  });
// 언팔로우 리스너
$(document).on('click', '.folbox a.unfollow-btn', function() {
	var userEmail = $(this).data('useremail');
	var friendEmail = $(this).data('friendemail');
	var followBtn = $(this).closest('.folbox');
	deletefriend(userEmail, friendEmail, followBtn);
  });
//친구하기
function dofriend(userEmail, friendEmail, followBtn) {
	$.ajax({
	  url: "/followfirend",
	  type: "POST",
	  data: {
		userEmail: userEmail,
		friendEmail: friendEmail
	  },
	  success: function(result) {
		followBtn.find('a').replaceWith('<a href="javascript:void(0)" class="unfollow-btn" style="color:#1877F2;font-size: 14px;" data-useremail="' + userEmail + '" data-friendemail="' + friendEmail + '">팔로워</a>');
	  },
	  error: function(xhr, status, error) {
		console.log(error);
	  }
	});
}
//친구삭제하기
function deletefriend(userEmail, friendEmail, followBtn) {
	$.ajax({
	  url: "/delFriend",
	  type: "POST",
	  data: {
		userEmail: userEmail,
		friendEmail: friendEmail
	  },
	  success: function(result) {
		console.log(userEmail);
		followBtn.find('a').replaceWith('<a href="javascript:void(0)" class="follow-btn" style="color:#1877F2;font-size: 14px;" data-useremail="' + userEmail + '" data-friendemail="' + friendEmail + '">팔로우</a>');
	  },
	  error: function(xhr, status, error) {
		console.log(error);
	  }
	});
}
function share(postId){//공유하기모달
	 const modal = document.querySelector('.modal');
	 const sharebtns=document.querySelectorAll('.sharebtn');
	 const sharecancel=document.querySelector('.sharecancel');
	 const sharemodal=document.querySelector('.sharemodal');
	 for (var i = 0; i <sharebtns.length ; i++) {
	 sharebtns[i].addEventListener('click', () => {
		 sharemodal.style.display='block';
		  modal.style.display = 'none';
	 
		 const currentURL = encodeURIComponent(window.location.href);
		 const naverShareLink = document.querySelector('.naver-share-link');
		 naverShareLink.href = 'http://share.naver.com/web/shareView.nhn?url=' + currentURL + '&title=' + encodeURIComponent(document.title);
	 });
	}
	 sharecancel.addEventListener('click', () => {
		 sharemodal.style.display='none';
	 });
}
 //카카오공유
 function postShareKakao() {

	 Kakao.init("22e95823c2f2830c0d276cb7b53d5dad");
	 Kakao.Link.sendCustom({
		 templateId: 93282
	 });
 }

 function friendtext(email){
	 var formData = new FormData();
	 $.ajax({
		 type : "get",
		 url : "reply?boardIdx=${boardInfo.boardIdx}&writer=${boardInfo.userIdx}",
			 dataType : "text",
			 data : formData, 
			 contentType: false, 
			 processData: false, 
			 cache : false,
			 success : function(data) {
					 // C에서 받아온 데이터로 새로 뿌려주기
				 var html = jQuery('<div>').html(data);
				 var contents1 = html.find("div#replyList").html();
				 var contents2 = html.find("div#replyCount").html();
				 $("#replyList").html(contents1);
				 $("#replyCount").html(contents2);
			 },
			 error : function(){
				 alert("통신실패");
			 }
		 });
 }


 
 var nowUrl = window.location.href;//링크 url따오기 완료
 function copyUrl(){
	 const modal = document.querySelector('.modal');
	 navigator.clipboard.writeText(nowUrl).then(res=>{
		   alert("주소가 복사되었습니다!");
		   modal.style.display='none';
		 })
 }
 function report(postId){//신고하기 버튼 누르면 report 숫자올라가기 완료
	 const modal = document.querySelector('.modal');
	   $.ajax({
			 url: "postReport", 
			 type: "POST",
			 data: { postId: postId },
			 success: function(result) {
				 modal.style.display = 'none';
			 },
			 error: function(xhr, status, error) {
			 }
		   });
 }



	
//만들기버튼 기능들
const makePostButton = document.querySelector('#make-post');
const overlay = document.querySelector('.overlay');
const makemodal=document.querySelector('.makemodal');
const makecancel=document.querySelector('.makecancel');
const imageposition3=document.querySelector('.imageposition3');
const imageposition4=document.querySelector('.imageposition4');
const fixmodal=document.querySelector('.fixmodal');//사진모달
const videomodal = document.querySelector('.videomodal');//동영상모달
const makeBackBtn=document.querySelector('.makeBackBtn');//사진뒤로가기
const makevideoBackBtn=document.querySelector('.makevideoBackBtn');//동영상뒤로가기
const makepostInsert=document.querySelector('.makepostInsert');//사진완료
const videopostInsert=document.querySelector('.videopostInsert');//영상완료
const postcomplete=document.querySelector('.postcomplete');
const makepostCheck=document.querySelector('.makepostCheck');

// 크롭 기능
let result = document.querySelector('.result'),
img_result = document.querySelector('.choicepicture'),
img_w = document.querySelector('.img-w'),
img_h = document.querySelector('.img-h'),
options = document.querySelector('.options'),
save = document.querySelector('.save'),
cropped = document.querySelector('.cropped'),
dwn = document.querySelector('.download'),
upload = document.querySelector('.imageInput'),
cropper = '';
	
// 파일 업로드 버튼 클릭후 파일 넣을시 이벤트 발생
$(".imageInput").change(function(){        	
	 if ($(".makePostImage").css("display") == "none" && $(".filebox").css("display") == "none") { 
		$(".makePostImage").show(); // display 속성을 block 으로 바꾼다.
		$(".filebox").show(); // display 속성을 none 으로 바꾼다. 
		$(".makepostBefore").hide(); // display 속성을 none 으로 바꾼다. 
		$(".makepostAfter").hide(); // display 속성을 none 으로 바꾼다. 
		$(".choicepicture").hide(); // display 속성을 none 으로 바꾼다. 
		$(".choiceafterpicture").hide(); // display 속성을 none 으로 바꾼다. 
		$(".box").hide(); // display 속성을 none 으로 바꾼다. 
		$(".makepostInsert").hide(); // display 속성을 none 으로 바꾼다.				 				 			 				  
	} else{
		$(".makePostImage").hide(); // display 속성을 block 으로 바꾼다.
		$(".filebox").hide(); // display 속성을 none 으로 바꾼다. 
		$(".makepostBefore").show(); // display 속성을 none 으로 바꾼다. 
		$(".makepostAfter").show(); // display 속성을 none 으로 바꾼다. 
		$(".choicepicture").show(); // display 속성을 none 으로 바꾼다. 
		$(".choiceafterpicture").show(); // display 속성을 none 으로 바꾼다. 
		$(".box").show(); // display 속성을 none 으로 바꾼다. 
		$(".makepostInsert").show(); // display 속성을 none 으로 바꾼다.				
	}

 });	
	

upload.addEventListener('change', e => {
	if (e.target.files.length) {
		// start file reader
		const reader = new FileReader();
		reader.onload = e => {
			if (e.target.result) {
				// create new image
				let img = document.createElement('img');
				img.id = 'image';
				img.src = e.target.result;
				// clean result before
				result.innerHTML = '';
				// append new image
				result.appendChild(img);
				// show save btn and options
				save.classList.remove('hide');
				options.classList.remove('hide');
				// init cropper
				cropper = new Cropper(img);
			}
		};
		reader.readAsDataURL(e.target.files[0]);
	}
});	
save.addEventListener('click', e => {
	e.preventDefault();
	// get result to data uri
	let imgSrc = cropper.getCroppedCanvas().toDataURL(); // remove width parameter
	// remove hide class of img
	cropped.classList.remove('hide');
	img_result.classList.remove('hide');
	// show image cropped
	cropped.src = imgSrc;
});
								
 makePostButton.addEventListener('click', () => {
	 overlay.classList.toggle('active');
	  overlay.style.display='block';
	   makemodal.style.display='block';
	   $(".makeimage").attr("src", "./images/mainMakePostTrue.svg");
 });
 makecancel.addEventListener('click', ()=>{
	 overlay.classList.toggle('active');
	 overlay.style.display = 'none';
	 makemodal.style.display='none';
	 $(".makeimage").attr("src", "./images/mainMakePostFalse.png");
 });
 imageposition3.addEventListener('click', ()=>{
	 makemodal.style.display = 'none';
	 fixmodal.style.display = 'block';
 });
 imageposition4.addEventListener('click', ()=>{
	 makemodal.style.display='none';
	 videomodal.style.display='block';
 });
 makeBackBtn.addEventListener('click', ()=>{
	 fixmodal.style.display='none';
	 makemodal.style.display='block';
 });
 makevideoBackBtn.addEventListener('click',()=>{
	 videomodal.style.display='none';
	 makemodal.style.display='block';
 })
  
 function dataURLtoFile(dataURL, fileName) {
	   const arr = dataURL.split(',');
	   const mime = arr[0].match(/:(.*?);/)[1];
	   const bstr = atob(arr[1]);
	   let n = bstr.length;
	   const u8arr = new Uint8Array(n);
	   while (n--) {
		 u8arr[n] = bstr.charCodeAt(n);
	   }
	   return new File([u8arr], fileName, { type: mime });
}
makepostCheck.addEventListener('click',()=>{
	 overlay.classList.toggle('active');
	 overlay.style.display = 'none';
	 $(".makeimage").attr("src", "./images/mainMakePostFalse.png");
	 postcomplete.style.display='none';
	 location.reload();
 })




//유저정보 받아오기
$.ajax({					
	type: "get",
	url: "/loginMain.action",
	data: {
	},
	success: function(obj) {
		var userEmail = obj.userEmail;
        var userNickName = obj.userNickName;
        var userName = obj.userName;
        var userGender = obj.userGender;
        var userSchool = obj.userSchool;
        var userPN = obj.userPN;
        var userAddress = obj.userAddress;
        var userSocial = obj.userSocial;

        $(".userEmail").text(userEmail);
        $(".userNickName").text(userNickName);
        $(".userName").text(userName);
        $(".userGender").text(userGender);
        $(".userSchool").text(userSchool);
        $(".userPN").text(userPN);
        $(".userAddress").text(userAddress);
        $(".userSocial").text(userSocial);
        $(".profile_mainprofile").attr("src", obj.userImage);
		//사진올리기 기능
		makepostInsert.addEventListener('click', e => {			 			 		 
			fixmodal.style.display='none';
			postcomplete.style.display='block';
			const userEmail = obj.userEmail;
			const croppedFile = dataURLtoFile(cropped.src, 'croppedImage.jpg');
			const formData = new FormData();
			formData.append('userEmail', userEmail);
			formData.append('imageName', croppedFile);
			$.ajax({
				   url: "/insertPost", 
				   type: "POST",
				   data:formData,
				   contentType: false,
				   processData: false,
				   success: function(result) {
					   location.reload();
				   },   
				   error: function(xhr, status, error) {
				   }
				 });
		});
		//영상
			
		videopostInsert.addEventListener('click', e => {
			const videomodal = document.querySelector('.videomodal');
			const postcomplete = document.querySelector('.postcomplete');
			const userEmail = obj.userEmail;
			const videoElement = document.getElementById('videoElement');
			const videoFile = videoElement.files[0];
			console.log(videoFile);
			console.log(userEmail);
			const formData = new FormData();
			formData.append('userEmail', userEmail);
			formData.append('videoFile', videoFile); 
			$.ajax({
			  url: "/insertVideo",
			  type: "POST",
			  data: formData,
			  contentType: false,
			  processData: false,
			  success: function(result) {
				location.reload();
			  },
			  error: function(xhr, status, error) {
			  }
			});
		});
		//aaa박스,bbb박스
		$.ajax({
			type: "get",
			url: "/getListPMember",
			data: {
				userEmail: userEmail
			},
			success: function(userList) {
				userList.forEach(function(userInfo) {//aaa박스
					var html =
						'<td width="100">' +
						'<div class="box1">' +
						'<a href="javascript:goURL(\'searched.jsp?userEmail=' + userInfo.userEmail + '\',\'' + userInfo.userEmail + '\')">' +
						'<img class="profileimage" src="' + userInfo.userImage + '">' +
						'</a>' +
						'</div>' +
						'<div>' +
						'<span style="position:relative; font-size: 14px; color: #303030; top:10px; left: 3px;">' +
						userInfo.userNickName +
						'</span>' +
						'</div>' +
						'</td>';
		
					$('#userListContainer').append(html);
				});
				userList.forEach(function(userInfo){//bbb박스
					$.ajax({
						type: "get",
						url: "/friendCheck",
						data: {
							userEmail: userEmail,
							friendEmail: userInfo.userEmail
						},
						success: function(result) { 
							var html = `
											<tr class="bbbb">
												<td width="50">
												<div class="boxnored">
													<img class="profileimage" src="${userInfo.userImage}">
												</div>
												</td>
												<td>
												<div class="box2">
													<h4 class="Nick-State">${userInfo.userNickName}</h4>
													<p class="Text-State">회원님을 팔로우합니다. </p>
												</div>
												</td>
												<td class="folbox">
													${result ? `<a href="javascript:void(0)" class="unfollow-btn" style="color:#1877F2;font-size: 14px;" data-useremail="${userEmail}" data-friendemail="${userInfo.userEmail}">팔로워</a>` : `<a href="javascript:void(0)" class="follow-btn" style="color:#1877F2;font-size: 14px;" data-useremail="${userEmail}" data-friendemail="${userInfo.userEmail}">팔로우</a>`}
												</td>
											</tr>
										`;
						$('#bbbUserListContainer').append(html);
						},
						error : function(error) {
							console.log(error);
							console.log(error.responseText);
						}
					});
				});
			}
		});
		
		//ccc박스 post가져오기
		$.ajax({//게시글가져오기
			type: "get",
			async: false,
			url: "/plist",
			data: {
			},
			success: function(plist) {
				var html =`
							<div class="ccc">
								<table>
									<tr style="width: 517px; display: inline-block;">
										<td style="padding-left: 10px; width: 50px;">
											<div class="box3" style="display: inline-block;">
										</td>
										<td style="width: 100px">
											<div class="box4"style="font-size: 15px; color: #303030; display: inline-block;">
											</div>
										</td>
										<td class="box5">
										</td>
									</tr>
									<tr>
										<td colspan="3" style="border-bottom: solid 1px #eee;" class="box6">
											
										</td>
									</tr>
									<tr style="float:left; height: 25px; padding-top: 15px;">
										<td style="padding-left: 10px;" class="box7">
										</td>
										<td>
											<a href="javascript:chat('#')" id="ddd">
												<img src="/img/postMessageFalse.svg" align="top">
											</a>
										</td>
										<td>
											<a href="javascript:share('#')" id="ddd" class="sharebtn">
												<img src="./img/postShare.svg" align="top">
											</a>
										</td>
									</tr>
									<tr class="box8">
										
									</tr>
									<tr class="commenter">
										<td colspan="3" width="500" style="padding-left:10px;" class="box9">
										</td>
									</tr>
									<tr>
										<td colspan="3" width="500" class="box10">
										</td>
									</tr>
									<tr>
										<td colspan="3" width="500" style="padding-top: 15px;" class="box11">
										</td>
									</tr>
								</table>
							</div>
				`;
				var index=0;
				plist.forEach(function(postInfo){
					
					var commentId;
					var userImage;
					var userNickName;
					var likeNum=postInfo.likeNum;
					var commentNum=postInfo.commentNum;
					var shareNum=postInfo.shareNum;
					
					
					$.ajax({//포스트의 유저정보가져오기
						type: "get",
						url: "/getPMember",
						data: {
							userEmail: postInfo.userEmail
						},
						success: function(postuser) {
							postuser.forEach(function(postuserInfo){
								console.log("게시물닉네임:"+postuserInfo.userNickName);
								console.log("유저프로필사진"+postuserInfo.userImage);
								var profileImage = postuserInfo.userImage;
            					var nickName = postuserInfo.userNickName;
								var imageName=postInfo.imageName;
								var videoName=postInfo.videoName;
								var postlikenum=postInfo.likeNum;
								console.log(postlikenum);
								console.log("길이:"+postInfo.length);
								console.log("사진:"+imageName+"   영상:"+videoName);
								$('.ccc:eq(' + index + ') .box3').append(`<img class="profile" src="${profileImage}">`);
            					$('.ccc:eq(' + index + ') .box4').append(`<b>${nickName}</b>`);
								$('.ccc:eq(' + index + ') .box5').append(`<a href="javascript:hamberger('#')" class="ham" style="margin-left: 320px;">
								<img src="./img/postCategory.svg"></a>`);
								if (imageName==null){
									$('.ccc:eq(' + index + ') .box6').append(`<video src="photo/${postInfo.videoName}" width="535" height="480"></video>`);
								}
								else {
									$('.ccc:eq(' + index + ') .box6').append(`<img src="photo/${postInfo.imageName}" width="535" height="480"></img>`);
								}
								$('.ccc:eq(' + index + ') .box8').append(`<td width="250" style="padding-left:10px;">${nickName}님 외 <b>${postlikenum}명</b>이 좋아합니다.</td>`);
								$('.ccc:eq(' + index + ') .box10').append(`<br>`);
								
								
								$('.ccc:eq(' + index + ') .box10').append(`<div class="asdf" style="padding-left: 10px">`);
								$('.ccc:eq(' + index + ') .box10').append(`&nbsp;&nbsp;<img src="/img/postLikeCount.svg">&nbsp;${postInfo.likeNum}&nbsp;&nbsp;&nbsp;&nbsp;`);
								$('.ccc:eq(' + index + ') .box10').append(`<img src="/img/postMessageCount.svg">&nbsp;댓글${postInfo.commentNum}개`);
								$('.ccc:eq(' + index + ') .box10').append(`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`);
								$('.ccc:eq(' + index + ') .box10').append(`공유하기 ${postInfo.shareNum} 회`);
								$('.ccc:eq(' + index + ') .box10').append(`</div>`);
								$('.ccc:eq(' + index + ') .box10').append(`<hr style="background-color: #d8d8d8">`);
								var currentIndex = index;
								$.ajax({//하트했는지 가져오기
									type: "get",
									url: "/postLike",
									data: {
										userEmail:userEmail,
										postId:postInfo.postId
									},
									success: function(result) {
										if (result) {
											$('.ccc:eq(' + currentIndex + ') .box7').append(`<a href="javascript:void(0)" class="heartdel" id="ddd" data-postid=${postInfo.postId} data-useremail=${userEmail}><img src="img/postLikeTrue.svg" align="top">`);
										}
										else {
											$('.ccc:eq(' + currentIndex + ') .box7').append(`<a href="javascript:void(0)" class="heartup" id="ddd" data-postid=${postInfo.postId} data-useremail=${userEmail}><img src="img/postLikeFalse.svg" align="top">`);
										}
									},
									error : function(error) {
											console.log(error);
											console.log(error.responseText);
									}
								});
								$('.ccc:eq(' + currentIndex + ') .box11').append(`<img src="/img/postMessageProfile.svg" class="postMessageProfile">&nbsp;`);
								$('.ccc:eq(' + currentIndex + ') .box11').append(`<input class="postTextbox" id="postTextbox" placeholder="댓글을 입력하세요." data-postid="${postInfo.postId}" data-userEmail="${userEmail}"/>`);
								//댓글입력시
								$(`.ccc:eq(${currentIndex}) .postTextbox`).on('keydown', function(event) {
									if (event.keyCode === 13) { 
									  event.preventDefault(); 
									  const postId = $(this).data('postid');
									  const userEmail = $(this).data('useremail');
									  const commentDetail = $(this).val();
									  $.ajax({
										type: "POST",
										url: "/commentAdd",
										data: {
										  userEmail: userEmail,
										  commentDetail: commentDetail,
										  postId: postId
										},
										success: function(commentList) {
										  refreshComments(postId);//댓글갱신
										  commentnumber(postId);//댓글개수갱신
										},
										error: function(error) {
										  console.log(error);
										  console.log(error.responseText);
										}
									  });
								  
									  $(this).val(''); 
									}
								});
								  //하트하기
								  $('.ccc:eq(' + currentIndex + ') .box7').on('click', '.heartup', function() {
									var postId = $(this).data('postid');
									var userEmail = $(this).data('useremail');
									heartup(postId, userEmail);
								  });
								  //하트삭제
								  $('.ccc:eq(' + currentIndex + ') .box7').on('click', '.heartdel', function() {
									var postId = $(this).data('postid');
									var userEmail = $(this).data('useremail');
									heartdel(postId, userEmail);
								  });
								  //삭제 리스너
								  $('.ccc:eq(' + currentIndex + ') .box9').on('click', 'a.delete-comment', function() {
									var commentId = $(this).data('commentid');
									var postId = $(this).data('postid');
									commentdel(commentId, postId);
								  });
								  //업데이트 리스너
								  $('.ccc:eq(' + currentIndex + ') .box9').on('click', 'a.update-comment', function() {
									var commentId = $(this).data('commentid');
									var postId = $(this).data('postid');
									commentupdate(commentId,postId);
								  });
								  //답글 리스너
								  $('.ccc:eq(' + currentIndex + ') .box9').on('click', 'a.reply-comment', function() {
									var commentId = $(this).data('commentid');
									var postId = $(this).data('postid');
									insertReply(commentId,postId);
								  });
								  //답글숨기기리스너
								  $('.ccc:eq(' + currentIndex + ') .box9').on('click', 'a.doDisplay', function() {
									var commentId = $(this).data('commentid');
									doDisplay(commentId);
								  });
								// 팔로우 리스너

								  
								  
								
								$.ajax({//댓글가져오기
									type: "get",
									url: "/listPReply",
									data: {
										postId:postInfo.postId
									},
									success: function(clist) {
										$('.ccc:eq(' + currentIndex + ') .commenter').append(`<tr class="commenter" style="height:${clist.length}*50%>px;">`);
										clist.forEach(function(commentInfo){
											if (commentInfo.commentParrent != null) {
												var myDiv = $(`<div id="myDIV${commentInfo.commentParrent}" style="display:none; padding-top: 10px;"></div>`);
												myDiv.append(`<c>${commentInfo.userEmail}</c>&nbsp;<c class="commentDetail">${commentInfo.commentDetail}</c>`);
												myDiv.append(`<br>`);
												myDiv.append(`<c>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c style="font-size: 90%; color: #8e8e8e;">${commentInfo.commentDate}</c>&nbsp;&nbsp;&nbsp;`);
											  
												if (commentInfo.userEmail == userEmail) {
												  myDiv.append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}" class="update-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">수정</a></b>&nbsp;`);
												}
												if (commentInfo.userEmail == userEmail) {
												  myDiv.append(`<a href="javascript:void(0)" class="delete-comment" data-commentid="${commentInfo.commentId}" data-postid="${postInfo.postId}" style="font-size: 90%; color: #8e8e8e;">삭제</a></b>&nbsp;`);
												}
											  
												myDiv.append(`<br>`);
												$('.ccc:eq(' + currentIndex + ') .box9').append(myDiv);
											}
											else {
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<b>${commentInfo.userEmail}</b>&nbsp;<c class="commentDetail" >${commentInfo.commentDetail}</c>`);
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<br>`);
												if (commentInfo.commentId/*의 값을 가진 commentParrent가있으면 */){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" style="font-size: 90%; color: #8e8e8e;" class="doDisplay" data-commentid="${commentInfo.commentId}" id="linkText${commentInfo.commentId}"><img src="img/postMessageReplyBtn.svg"/> 답글보기</a>&nbsp;<c style="font-size: 90%; color: #8e8e8e;">${commentInfo.commentDate}</c>&nbsp;&nbsp;`);
												}
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}"class="reply-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">답글</a> &nbsp;`);
												if (commentInfo.userEmail==userEmail){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}"class="update-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">수정</a></b>&nbsp;`);
													
												}
												if (commentInfo.userEmail==userEmail){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" class="delete-comment" data-commentid="${commentInfo.commentId}" data-postid="${postInfo.postId}" style="font-size: 90%; color: #8e8e8e;">삭제</a></b>&nbsp;`);
												}
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<br>`);
											}
											
										});
									},
									error : function(error) {
											console.log(error);
											console.log(error.responseText);
									}
									
								});
								
								 //댓글 보기숨기기
								 function doDisplay(commentId) {
									const elements = document.querySelectorAll("#myDIV" + commentId);
									elements.forEach(function(element) {
									  if (element.style.display === '' || element.style.display === 'none') {
										element.style.display = 'block';
										console.log("보이기" + commentId);
										$("#linkText" + commentId).html('<img src="img/postMessageReplyBtn.svg"/> 답글숨기기');
									  } else {
										element.style.display = 'none';
										console.log("숨기기:" + commentId);
										$("#linkText" + commentId).html('<img src="img/postMessageReplyBtn.svg"/> 답글보기');
									  }
									});
								  }
								//하트추가
								function heartup(postId, userEmail){
									$.ajax({
										url: "/heartup", 
										type: "POST",
										data: { postId: postId,
												userEmail:userEmail
										},
										success: function(result) {
											 $('.ccc:eq(' + currentIndex + ') .box7').empty();
											 $('.ccc:eq(' + currentIndex + ') .box8').empty(); 
											 $('.ccc:eq(' + currentIndex + ') .box10').empty();
											 $.ajax({
												type: "get",
												url: "/plist2",
												data: {
												  postId: postId
												},
												success: function (plist) {
												  $('.ccc:eq(' + currentIndex + ') .box10').append(`<br>`);
												  plist.forEach(function (postInfo) {
													$('.ccc:eq(' + currentIndex + ') .box8').append(`<td width="250" style="padding-left:10px;">${postuserInfo.userNickName}님 외 <b>${postInfo.likeNum}명</b>이 좋아합니다.</td>`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<div class="asdf" style="padding-left: 10px">`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`&nbsp;&nbsp;<img src="/img/postLikeCount.svg">&nbsp;${postInfo.likeNum}&nbsp;&nbsp;&nbsp;&nbsp;`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<img src="/img/postMessageCount.svg">&nbsp;댓글${postInfo.commentNum}개`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`공유하기 ${postInfo.shareNum} 회`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`</div>`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<hr style="background-color: #d8d8d8">`);
												  });
												},
												error: function (error) {
												  console.log(error);
												  console.log(error.responseText);
												}
											  });
											 $('.ccc:eq(' + currentIndex + ') .box7').append(`<a href="javascript:void(0)" class="heartdel" id="ddd" data-postid=${postInfo.postId} data-useremail=${userEmail}><img src="img/postLikeTrue.svg" align="top">`);
										  },
										error: function(xhr, status, error) {
											console.error("Error:", error);
										}
										
									  }
									 );
								 }
								//하트삭제
								function heartdel(postId, userEmail){
								   $.ajax({
									   url: "/heartdel", 
									   type: "POST",
									   data: { postId: postId,
											   userEmail:userEmail
									   },
									   success: function(result) {
											$('.ccc:eq(' + currentIndex + ') .box7').empty();
											$('.ccc:eq(' + currentIndex + ') .box8').empty();
											$('.ccc:eq(' + currentIndex + ') .box10').empty();
											$.ajax({
												type: "get",
												url: "/plist2",
												data: {
												  postId: postId
												},
												success: function (plist) {
												  $('.ccc:eq(' + currentIndex + ') .box10').append(`<br>`);
												  plist.forEach(function (postInfo) {
													$('.ccc:eq(' + currentIndex + ') .box8').append(`<td width="250" style="padding-left:10px;">${postuserInfo.userNickName}님 외 <b>${postInfo.likeNum}명</b>이 좋아합니다.</td>`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<div class="asdf" style="padding-left: 10px">`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`&nbsp;&nbsp;<img src="/img/postLikeCount.svg">&nbsp;${postInfo.likeNum}&nbsp;&nbsp;&nbsp;&nbsp;`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<img src="/img/postMessageCount.svg">&nbsp;댓글${postInfo.commentNum}개`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`공유하기 ${postInfo.shareNum} 회`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`</div>`);
													$('.ccc:eq(' + currentIndex + ') .box10').append(`<hr style="background-color: #d8d8d8">`);
												  });
												},
												error: function (error) {
												  console.log(error);
												  console.log(error.responseText);
												}
											  });
											$('.ccc:eq(' + currentIndex + ') .box7').append(`<a href="javascript:void(0)" class="heartup" id="ddd" data-postid=${postInfo.postId} data-useremail=${userEmail}><img src="img/postLikeFalse.svg" align="top">`);
										 },
									   error: function(xhr, status, error) {
										   console.error("Error:", error);
									   }
									   
									 }
									);
								}
								//댓글삭제
								function commentdel(commentId, postId){
									 $.ajax({
										type: "POST",
										url: "/commentdel", 
										data: { commentId: commentId,
												postId:postId
										},

										success: function(result) {
											refreshComments(postId);//댓글갱신
											  commentnumber(postId);//댓글개수갱신
										  },
										error: function(error) {
											console.log(error);
											console.log(error.responseText);
										}
									  });
									}
									//댓글 수정기능
									function commentupdate(commentId, postId) {
										const parentElement = document.getElementById("box" + commentId);
										const isInputBoxAdded = parentElement.querySelector('input[type="text"]');
									  
										if (!isInputBoxAdded) {
										  const inputBox = document.createElement('input');
										  inputBox.type = 'text';
										  inputBox.style.borderRadius = '30px';
										  inputBox.style.width = '120px';
									  
										  const deleteButton = document.createElement('input');
										  deleteButton.type = 'button';
										  deleteButton.value = '취소';
										  deleteButton.style.border = '1px solid skyblue';
										  deleteButton.style.backgroundColor = 'rgba(0,0,0,0)';
										  deleteButton.style.color = 'skyblue';
										  deleteButton.style.borderRadius = '5px';
										  deleteButton.addEventListener('mouseover', function () {
											deleteButton.style.color = 'white';
											deleteButton.style.backgroundColor = 'skyblue';
										  });
										  deleteButton.addEventListener('mouseout', function () {
											deleteButton.style.color = 'skyblue';
											deleteButton.style.backgroundColor = 'rgba(0,0,0,0)';
										  });
										  deleteButton.onclick = function () {
											parentElement.innerHTML = '<a href="javascript:void(0)" id="box' + commentId + '" class="update-comment" data-postid="' + postId + '" data-commentid="' + commentId + '" style="font-size: 90%; color: #8e8e8e;">수정</a></b>&nbsp;';
										  };
									  
										  const saveButton = document.createElement('input');
										  saveButton.type = 'button';
										  saveButton.value = '저장';
										  saveButton.style.border = '1px solid skyblue';
										  saveButton.style.backgroundColor = 'rgba(0,0,0,0)';
										  saveButton.style.color = 'skyblue';
										  saveButton.style.borderRadius = '5px';
										  saveButton.addEventListener('mouseover', function () {
											saveButton.style.color = 'white';
											saveButton.style.backgroundColor = 'skyblue';
										  });
										  saveButton.addEventListener('mouseout', function () {
											saveButton.style.color = 'skyblue';
											saveButton.style.backgroundColor = 'rgba(0,0,0,0)';
										  });
										  saveButton.onclick = function () {
											var updatedComment = inputBox.value;
											$.ajax({
											  url: '/commentupdate',
											  type: 'POST',
											  data: { commentId: commentId, commentDetail: updatedComment },
											  success: function (result) {
												refreshComments(postId); //댓글갱신
												commentnumber(postId); //댓글개수갱신
											  },
											  error: function (xhr, status, error) {
											  }
											});
										  };
									  
										  parentElement.innerHTML = '';
										  parentElement.appendChild(inputBox);
										  parentElement.appendChild(saveButton);
										  parentElement.appendChild(deleteButton);
										}
									  }
										//답글
										function insertReply(commentId, postId) {
											const parentElement = document.getElementById("box" + commentId);
											const isReplyBoxAdded  = parentElement.querySelector('input[type="text"]');
										  
											if (!isReplyBoxAdded ) {
											  const replyBox  = document.createElement('input');
											  replyBox .type = 'text';
											  replyBox .style.borderRadius = '30px';
											  replyBox .style.width = '120px';
										  
											  const replyDelete  = document.createElement('input');
											  replyDelete.type = 'button';
											  replyDelete.value = '취소';
											  replyDelete.style.border = '1px solid skyblue';
											  replyDelete.style.backgroundColor = 'rgba(0,0,0,0)';
											  replyDelete.style.color = 'skyblue';
											  replyDelete.style.borderRadius = '5px';
											  replyDelete.addEventListener('mouseover', function () {
												replyDelete.style.color = 'white';
												replyDelete.style.backgroundColor = 'skyblue';
											  });
											  replyDelete.addEventListener('mouseout', function () {
												replyDelete.style.color = 'skyblue';
												replyDelete.style.backgroundColor = 'rgba(0,0,0,0)';
											  });
											  replyDelete.onclick = function () {
												parentElement.innerHTML = '<a href="javascript:void(0)" id="box${commentInfo.commentId}"class="reply-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">답글</a>';
											  };
										  
											  const replySave = document.createElement('input');
											  replySave.type = 'button';
											  replySave.value = '저장';
											  replySave.style.border = '1px solid skyblue';
											  replySave.style.backgroundColor = 'rgba(0,0,0,0)';
											  replySave.style.color = 'skyblue';
											  replySave.style.borderRadius = '5px';
											  replySave.addEventListener('mouseover', function () {
												replySave.style.color = 'white';
												replySave.style.backgroundColor = 'skyblue';
											  });
											  replySave.addEventListener('mouseout', function () {
												replySave.style.color = 'skyblue';
												replySave.style.backgroundColor = 'rgba(0,0,0,0)';
											  });
											  replySave.onclick = function () {
												var replyDetail = replyBox.value;
												$.ajax({
												  url: '/insertReply',
												  type: 'POST',
												  data: { postId: postId, commentDetail: replyDetail,userEmail:userEmail, commentParrent:commentId },
												  success: function (result) {
													refreshComments(postId); //댓글갱신
													commentnumber(postId); //댓글개수갱신
												  },
												  error: function (xhr, status, error) {
												  }
												});
											  };
										  
											  parentElement.innerHTML = '';
											  parentElement.appendChild(replyBox);
											  parentElement.appendChild(replySave);
											  parentElement.appendChild(replyDelete);
											}
										  }
								//댓글다시가져오기
								function refreshComments(postId) {
									$.ajax({
									  type: "get",
									  url: "/listPReply",
									  data: {
										postId: postId
									  },
									  success: function(clist) {
										var currentIndex = $('.ccc').index($('#cccContainer').find(`[data-postid="${postId}"]`).closest('.ccc'));
										$('.ccc:eq(' + currentIndex + ') .box9').empty(); 
										
										clist.forEach(function(commentInfo) {
											if (commentInfo.commentParrent != null) {
												var myDiv = $(`<div id="myDIV${commentInfo.commentParrent}" style="display:none; padding-top: 10px;"></div>`);
												myDiv.append(`<c>${commentInfo.userEmail}</c>&nbsp;<c class="commentDetail">${commentInfo.commentDetail}</c>`);
												myDiv.append(`<br>`);
												myDiv.append(`<c>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c style="font-size: 90%; color: #8e8e8e;">${commentInfo.commentDate}</c>&nbsp;&nbsp;&nbsp;`);
											  
												if (commentInfo.userEmail == userEmail) {
												  myDiv.append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}" class="update-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">수정</a></b>&nbsp;`);
												}
												if (commentInfo.userEmail == userEmail) {
												  myDiv.append(`<a href="javascript:void(0)" class="delete-comment" data-commentid="${commentInfo.commentId}" data-postid="${postInfo.postId}" style="font-size: 90%; color: #8e8e8e;">삭제</a></b>&nbsp;`);
												}
											  
												myDiv.append(`<br>`);
												$('.ccc:eq(' + currentIndex + ') .box9').append(myDiv);
											}
											else {
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<b>${commentInfo.userEmail}</b>&nbsp;<c class="commentDetail" >${commentInfo.commentDetail}</c>`);
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<br>`);
												if (commentInfo.commentId/*의 값을 가진 commentParrent가있으면 */){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" style="font-size: 90%; color: #8e8e8e;" class="doDisplay" data-commentid="${commentInfo.commentId}" id="linkText${commentInfo.commentId}"><img src="img/postMessageReplyBtn.svg"/> 답글보기</a>&nbsp;<c style="font-size: 90%; color: #8e8e8e;">${commentInfo.commentDate}</c>&nbsp;&nbsp;`);
						
												}
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}"class="reply-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">답글</a> &nbsp;`);
												if (commentInfo.userEmail==userEmail){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" id="box${commentInfo.commentId}"class="update-comment" data-postid="${postInfo.postId}" data-commentid="${commentInfo.commentId}" style="font-size: 90%; color: #8e8e8e;">수정</a></b>&nbsp;`);
												}
												if (commentInfo.userEmail==userEmail){
													$('.ccc:eq(' + currentIndex + ') .box9').append(`<a href="javascript:void(0)" class="delete-comment" data-commentid="${commentInfo.commentId}" data-postid="${postInfo.postId}" style="font-size: 90%; color: #8e8e8e;">삭제</a></b>&nbsp;`);
						
												}
												$('.ccc:eq(' + currentIndex + ') .box9').append(`<br>`);
											}
										});
									  },
									  error: function(error) {
										console.log(error);
										console.log(error.responseText);
									  }
									});
								  }
								  //댓글개수갱신
								  function commentnumber(postId) {
									const currentIndex = $('.ccc').index($('#cccContainer').find(`[data-postid="${postId}"]`).closest('.ccc'));
									const currentBox10 = $('.ccc:eq(' + currentIndex + ') .box10');
									$.ajax({
									  type: "get",
									  url: "/plist2",
									  data: {
										postId: postId
									  },
									  success: function (plist) {
										currentBox10.each(function (index, element) {
										  $(element).empty();
										  plist.forEach(function (postInfo) {
											$(element).append(`<br>`);
											$(element).append(`<div class="asdf" style="padding-left: 10px">`);
											$(element).append(`&nbsp;&nbsp;<img src="/img/postLikeCount.svg">&nbsp;${postInfo.likeNum}&nbsp;&nbsp;&nbsp;&nbsp;`);
											$(element).append(`<img src="/img/postMessageCount.svg">&nbsp;댓글${postInfo.commentNum}개`);
											$(element).append(`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`);
											$(element).append(`공유하기 ${postInfo.shareNum} 회`);
											$(element).append(`</div>`);
											$(element).append(`<hr style="background-color: #d8d8d8">`);
										  });
										});
									  },
									  error: function (error) {
										console.log(error);
										console.log(error.responseText);
									  }
									});
								  }
								index++; 
							});
						},
						error : function(error) {
								console.log(error);
								console.log(error.responseText);
						}
						});
						
      					$('#cccContainer').append(html);
					});
				},
			error : function(error) {
					console.log(error);
					console.log(error.responseText);
			}
		});
		
		
			
	}
});

