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
			myPostUpload(obj.userEmail);
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
					$(".userEmail").text(obj2[0].userEmail);
					$(".userNickName").text(obj2[0].userNickName);
					$(".userName").text(obj2[0].userName);
					$(".userGender").text(obj2[0].userGender);
					$(".userSchool").text(obj2[0].userSchool);
					$(".userPN").text(obj2[0].userPN);
					$(".userAddress").text(obj2[0].userAddress);
					$(".userSocial").text(obj2[0].userSocial);
					myPostUpload(FriendEmail);
					bgupdate(FriendEmail);
					friendList(FriendEmail);
					$.ajax({
						type: "post",
						url: "/profile-followCheck",
						data: {
							userEmail : userEmail,
							friendEmail : FriendEmail
						},
						success: function(obj3){
							console.log(obj3)
							if(obj3 == 0){
								followButton();
							}else if(obj3 == 1){
								unFollowButton();
							}
						},
						error: function(error) {
							console.log("실패이자식아");
						}
					})
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
// document.querySelector("#check2").addEventListener('click', check2);

function followButton(){
	$('.area_up7').append(
		'<div>' +
		'<button type="button" class = "profileBtn3" id="show44" onclick ="deletfollowButton()">' +
			'팔로잉' +
		'</button>' +
		'</div>'
	)
}

function unFollowButton(){
	$('.area_up7').append(
		'<div>' +
		'<button type="button" class = "profileBtn3" id="show44" onclick ="deletunFollowButton()">' +
			'팔로우 취소' +
		'</button>' +
		'</div>'
	)
}

function deletfollowButton(){
	$('.area_up7 *').remove();
	unFollowButton();
	$.ajax({
		type: "post",
		url: "/profile-folloing",
		data: {
			userEmail : userEmail,
			friendEmail : FriendEmail
		},
		success: function(obj3){
			console.log("성공이자식아!");
		},
		error: function(error) {
			console.log("실패이자식아");
		}
	})
}

function deletunFollowButton(){
	$('.area_up7 *').remove();
	followButton();
	$.ajax({
		type: "post",
		url: "/profile-unfolloing",
		data: {
			userEmail : userEmail,
			friendEmail : FriendEmail
		},
		success: function(obj3){
			console.log("성공이자식아")
		},
		error: function(error) {
			console.log("실패이자식아!");
		}
	})
}



function myPostUpload(userEmail){
	$.ajax({//게시글가져오기
		type: "post",
		url: "/profile-pList3",
		data: {
			userEmail : userEmail
		},
		success: function(plist) {
			console.log("확인용:" + plist)
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
									// console.log("에러다1");
									// console.log("도넛될래1?");
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
					console.log("확인용 :" + plist);
					  $('.area_right_2').append(html);
				});
			},
		error : function(error) {
				console.log("에러다");
				console.log("도넛될래?");
		}
	});
}