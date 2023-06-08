const modal = document.querySelector(".modal-wrapper");
var lastID = 0;
let timerId = null;
let timerId2 = null;
let timerId3 = null;
let timerroomId = null;
var userEmail= "";
var Audience ="";
var sendMsgRoomId = "";
var roomdata;
var roomimgdata;
var userNickName = "";
var lastCheck = 0;
/* ----------------- ajax 함수 ----------------- */

function cahtSubmit(userEmail){	
	var chatName = userNickName
	var chatContent = $('#chatContent').val();
	var sendMsgRoomIdInt = parseInt(sendMsgRoomId);
	console.log(sendMsgRoomId);
	console.log(chatName);
	console.log(chatContent);
	$.ajax({
		type: "GET",
		url: "/chatSubmit",
		data: {
			roomId: sendMsgRoomIdInt,
			chatName: chatName,
			chatContent: chatContent,
		},
		success: function(obj) {
			console.log(obj);
		},
		error: function(){
			alert("실패");
		}
	});
		$('#chatContent').val('');
	}

	function getChatRoom(){
		$.ajax({					
			type: "get",
			url: "/getChatRoom",
			data: {
				userEmail: userEmail
			},
			success: function(obj) {
				chatroomList = JSON.stringify(obj)
				console.log("받아온값:"+chatroomList);
				console.log("길이:"+obj.length);
				roomdata = obj;
				for(var i = 0; obj.length > i; i++){
					addRoomId(obj[i].roomId, obj[i].userEmail, obj[i].userImage, obj[i].userNickName)
				}
				getRecentChat();
				getChatRoomAlarm();
				stratGetRecentChat();
				stratGetChatRoomAlarm();
			},
			error: function(){
				alert("실패");
			}
		})
	}

	function getChatList(){	
		var sendMsgRoomIdInt = parseInt(sendMsgRoomId);
		console.log("라스트:"+lastCheck);
		$.ajax({
			type: "GET",
			url: "/getChatList",
			data: {
				roomId: sendMsgRoomIdInt,
				lastCheck: lastCheck,
				userEmail: userEmail
			},
			success: function(obj) {
				chatList = JSON.stringify(obj)
				console.log("채팅리스트:"+chatList);
				for(var i = 0;obj.length > i;i++){
					if(obj[i].chatName == userNickName){
						addMyChat(obj[i].chatName, obj[i].chatContent);
						lastCheck = obj[i].chatID;
					}else{
						addChat(obj[i].chatName, obj[i].chatContent);
						lastCheck = obj[i].chatID;
					}
				}
			},
			error: function(){
				alert("실패");
			}
		});
	}

	function getRecentChat(){
		$.ajax({
			type: "get",
			url: "/getRecentChat",
			data: {
				userEmail: userEmail,
			},
			success: function(obj){
				chatList = JSON.stringify(obj)
				console.log("최근 채팅:"+chatList);
				if(obj=="") return;
				for(var i = 0; i<obj.length; i++){
					addRecentChat(obj[i].roomId, obj[i].chatContent);
				}
				
			},
			error: function(){
				alert("실패");
			}
		})
	}

	function getChatAllAlarm(){
		var allAlarm = 0;
		$.ajax({
			type: "get",
			url: "/getChatAlarm",
			data: {
				userEmail: userEmail,
			},
			success: function(obj){
				for(var i = 0;obj.length > i;i++){
					allAlarm = allAlarm + obj[i].lastCheck;
				}
				if(allAlarm == 0){
					addAlarmNumZero();
					return
				}
				addAlarmNum(allAlarm);
			}
		})
	}

	function getChatRoomAlarm(){
		$.ajax({
			type: "get",
			url: "/getChatAlarm",
			data: {
				userEmail: userEmail,
			},
			success: function(obj){
				for(var i = 0; i<obj.length; i++){
					if(obj[i].lastCheck == 0){
						zeroRoomAlarmNum(obj[i].roomId);
					}else{
						addRoomAlarmNum(obj[i].roomId,obj[i].lastCheck)
					}
				}
				
			}
		})
	}



function getFriendList(userEmail){
	$.ajax({
		type: "get",
		url: "/getFriendList",
		data: {
			userEmail:userEmail,
		},
		success: function(obj){
			chatroomList = JSON.stringify(obj)
			console.log("받아온값:"+chatroomList);

			for(var i=0; i<obj.length; i++){
				addInviteList(obj[i].friendName, obj[i].userImage, obj[i].friendEmail)
			}
		}
	})
}

function creatChatRoom(userEmail,Email,name){
	console.log("유저:"+userEmail);
	console.log("초대할 사람:"+Email+"-"+name);
	$.ajax({
		type: "get",
		url: "/creatChatRoom",
		data: {
			userEmail:userEmail,
			freindEmail:Email,
		},
		success: function(data){
			console.log("만들방번호:"+data);
			chat(name);
			startChat(data);
		},
		error: function(){
			alert("실패");
		}
	})
}

	







/* ----------------- append 함수 ----------------- */

function addAudienceID(chatName){
		if($('#audienceId').length == 0){
			$('audience').append(
			'<p id="audienceId">'+
			chatName+
			'<p>'
			)
		} 
}

// 상대 채팅 내용 불러오기/추가
function addChat(chatName, chatContent) {
		$('#chatList').append(
			'<div id=chatListContent class="chatListContent" style = " font-weight: bold;">'+
			'<p>'+
			chatName+
			'</p>'+
			'<span class="balloon">'+
			chatContent+
			'</span>'+
			'</div>'
		);
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	}
	
// 내 채팅 내용 불러오기/추가
function addMyChat(chatName, chatContent) {
		$('#chatList').append(
			'<div id=MychatListContent class="MychatListContent">'+
			'<p>'+
			'</p>'+
			'<span class="Myballoon">'+
			chatContent+
			'</span>'+
			'</div>'
		);
		$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
	} 

function addInviteList(name,image,Email){
	$('#inviteFollow').append(
		'<div id="inviteList" style="height:55px;">'+
		'<div id= "imgarea" style="float:left";width: 55px; height:55px;>'+
		'<img src = "'+image+'" style="width: 55px; height:55px; border-radius:50%; object-fit: cover;">'+
		'</div>'+
		'<div id="email" style="margin-left:63px">'+
		name+
		'<img src="images/messageInviteBtn.svg" id="'+Email+'" class="'+name+'" onclick="handleCreatChatRoom(event)" style="float: right;">'+
		'</div>'+
		'</div>'+
		'<hr style="background-color: rgba(0,0,0,0.1); border:none; height : 3px;">'
		
	)
}

// 채팅방 리스트 불러오기
function addRoomId(roomId,Email,image,name){
  console.log(roomId);
  $('#chatRoomList').append(
	'<div id ="'+Email+'profileImg" style = "float:left; width: 58px; height:58px;">' +
	'<img src = "'+image+'" style = "border-radius : 50%; width: 58px; height:58px; border: solid 2px #dedede; object-fit: cover;">'+
	'</div>' +
    '<div id="'+roomId+'" class="'+name+'" onclick="handleChatClick(event)" style = "margin-left:80px; cursor: pointer">'+
    '<div id="roomName">'+name+ 
    	'<div id="'+roomId+'roomAlarm" style="float:right;"></div>'+
    '</div>'+
    '<div id="'+roomId+'recentContent" style="height: 21px; margin-top: 16px;margin-bottom: 16px;color: #a4a4a4;"></div>'+
    '</div>'+
    '<hr style="background-color: rgba(0,0,0,0.1); border:none; height : 3px;">'
  );
}	

/*function addRoomImage(Email, userImage){
	setTimeout(function(){
		console.log(Email);
		console.log(userImage);
		$('#'+Email+'profileImg').append(
			'<img src = "'+userImage+'" style = "border-radius : 50%; width: 58px; height:58px; border: solid 2px #dedede;">'
		);
	},100);

}*/

// 채팅방 리스트에서 각 방에서의 제일 최근 메세지 표시
function addRecentChat(roomId, recentChat){
	$('#'+roomId+'recentContent *').remove();
	$('#'+roomId+'recentContent').append(
	'<p id="recentChat">'+
	recentChat+
	'</p>'
	);
}

// 각 방의 새로운 메세지 알림
function addRoomAlarmNum(roomId, roomAlarmNum){
	console.log("알람 있음");
	$('#'+roomId+'roomAlarm *').remove();
	$('#'+roomId+'roomAlarm').append(
		'<span class="roomAlarmBalloon">'+
		roomAlarmNum+
		'</span>'
	)
}

// 각 방의 새로운 메세지가 없을때 or 전부 확인 했을 경우
function zeroRoomAlarmNum(roomId, roomAlarmNum){
	console.log("제로 알람");
	$('#'+roomId+'roomAlarm *').remove();
}

// 새로운 메세지가 없을 경우 or 전부 확인 했을 경우
function addAlarmNumZero(){
	$('#alarm *').remove();
}

// 방 전부의 새로온 메세지 알람
function addAlarmNum(AlarmNum){
	$('#alarm *').remove();
	$('#alarm').append(
		'<span class="alarmBalloon">'+
		AlarmNum+
		'</span>'
	)
}

function deletInviteList(){
	$('#inviteFollow *').remove();
}

function deleteRoomId(){
  $('#chatRoomList *').remove();
}



/* ----------------- 함수 반복, 정지 ----------------- */

function stratGetRecentChat(){
 timerId2 = setInterval(function(){
	getRecentChat()
 },500);
}

function stratGetChatRoomAlarm(userEmail){
 timerId3 = setInterval(function(){
	 getChatRoomAlarm();
 },1000);
}

	// 채팅 리스트를 0.2초마다 새로고침 시작하는 함수	
	function startChat(roomId){
		console.log("확인용 :"+roomId);
		sendMsgRoomId = roomId;
		lastCheck = 0;
		console.log("라스트체크 초기화");
		getChatList();
	//	chatListFunction('ten',roomId,userEmail);
		setTimeout(	function getInfiniteChat() {
	timerId = setInterval(function() {
		getChatList();
		//	chatListFunction(lastID,roomId,userEmail);
	//		console.log("반복중");
		}, 200);
	}, 500)
	}
	
	function ready(id, mName){
		$.ajax({					
			type: "get",
			url: "/loginOk.action",
			data: {
			},
			success: function(obj) {
				userEmail = obj.userEmail
				userNickName = obj.userNickName
				console.log(userEmail + userNickName)
				getChatAllAlarm();
			//	console.log(lastID);
			}
		})
	//	chatRoomListFunction(userEmail);
	//	chatRoomListImage(userEmail);
	//	alarmFunction(userEmail);
		setInterval(function(){
		getChatAllAlarm(); // 1초마다 실행
  }, 1000);
	}
	

// 창이 켜지면 실행 - 알람 관련 함수

	
	
	
//	function getInfiniteChat() {
//	timerId = setInterval(function() {
//			chatListFunction(lastID);
//			console.log("반복중")
//		}, 200);
//	}
	
	// 각 방의 알람 함수 정지
	function stopGetChatRoomAlarm() {
 	 clearInterval(timerId3);
	}
	// 각 방의 최근의 메세지 함수 정지
	function stopGetRecentChat() {
 	 clearInterval(timerId2);
	}
	// 채팅방 통신 정지
	function stopInfiniteChat() {
 	 clearInterval(timerId);
	}
	
	// 엔터키 클릭 이벤트 - 채팅 보내기
    function mykeydown() {
        if(window.event.keyCode==13) //enter 일 경우
        {
        	submitFunction();
        }
    }



function semdMsg() {
//  var msgContent = document.getElementById("msgContent").value;
//  document.getElementById("msgContent").clear;
//  word.innerHTML = msgContent;
	cahtSubmit(userEmail);
}


/* ----------------- 각종 핸들러 함수 ----------------- */
function allChatsearchList(){
	var input = document.getElementById("search").value.trim();
	deleteRoomId();
	for(var i = 0; i<roomdata.length; i++){
				if(input != ""){
						console.log(input+" - 필터링");
						if(roomdata[i].userNickName.indexOf(input) !== -1){
						addRoomId(roomdata[i].roomId,roomdata[i].userEmail,roomdata[i].userImage,roomdata[i].userNickName);
					}
				}else{
				console.log("공백");
				addRoomId(roomdata[i].roomId,roomdata[i].userEmail,roomdata[i].userImage,roomdata[i].userNickName);	
			}
	}

}

function allInvitesearchList(){
	var input = document.getElementById("search").value.trim();
	deletInviteList()
		for(var j=0; j<roomimgdata.length; j++){
				if(input != ""){
						console.log(input+" - 필터링");
						if(roomimgdata[j].userName.indexOf(input) !== -1){
						addInviteList(roomimgdata[j].userName, roomimgdata[j].userImage, roomimgdata[j].userEmail);
					}
				}else{
				console.log("공백");
				addInviteList(roomimgdata[j].userName, roomimgdata[j].userImage, roomimgdata[j].userEmail);	
			}	
		}
}

// 상단바의 채팅 아이콘 클릭시 실행되는 함수
function clickChatBtn() {	// 말풍선 버튼 클릭시 채팅목록 모달창
  var image = document.getElementById('mainMessageButtonfalse');
  var followImage = document.getElementById('mainAlarmFalse');
 
  if (image.src.match("images/mainMessageFalse.png")) {
    image.src = "images/mainMessageTrue.svg";
    if(followImage.src.match("images/mainLikeTrue.svg")){
	followImage.src = "images/mainAlarmFalse.png";
	closeFollow();
	}
	// 현제 로그인한 사람의 이메일과 이름을 불러옴
    // modal.style.display = "flex";
	openModal();
	getChatRoom();
//	chatRoomListFunction(userEmail);
//	chatRoomListImage(userEmail);
//	recentChat();
//	allChatList(userEmail);
//	roomAlarmFunction(userEmail);
//	stratRecentChat();
//	stratAddRoomAlarmNum(userEmail)
   	} else {
    image.src = "images/mainMessageFalse.png";
    // modal.style.display = "none";
    closeModal();
  }
}

// 채팅 아이콘 한번더 클릭시 실행되는 함수 
function closeModal() {	
  stopInfiniteChat();
  stopGetChatRoomAlarm();
  stopGetRecentChat();
//  lastID = 0;
  modal.innerHTML = '';
}


function allChatList(){
		setTimeout(function(){
	console.log("allchat진입-roomip:"+ roomdata);
	console.log("allchat진입-roomimgdata:"+ roomimgdata);
	for(var i = 0; i<roomdata.length; i++){
		for(var j=0; j<roomimgdata.length; j++){
			if(roomdata[i].userEmail==roomimgdata[j].userEmail){
				addRoomId(roomdata[i].roomId,roomdata[i].userEmail,roomimgdata[j].userImage,roomimgdata[j].userName);
			}
		}
	}
	},150)	
}

//채팅초대 창 열떄 실행되는 함수
function handleChatInvite(){
	chatInvite();
	getFriendList(userEmail);
	stopGetChatRoomAlarm();
	stopGetRecentChat();
}


// 채팅방 초대창에서 뒤로가기 눌렀을때 실행되는 함수
function inviteBack(userEmail){
	openModal();
	getChatRoom();
}

// 채팅방에서 뒤로가기 버튼을 눌렀을때 실행되는 함수
function roomBackModal(userEmail) {
  stopInfiniteChat();
  openModal();
  getChatRoom();
 // lastID = 0;
}

// 채팅방 진입시 실행되는 함수
function handleChatClick(event) {
  const email = event.currentTarget.classList[0];
  const id = event.currentTarget.id;
  chat(email);
  startChat(id);
  stopGetChatRoomAlarm();
  stopGetRecentChat();
}

function handleCreatChatRoom(event){
	const name = event.currentTarget.classList[0];
	const email = event.currentTarget.id;
	console.log(name + "-" + email);
	creatChatRoom(userEmail, email, name);
}

function inviteChat(roomId, email){
  chat(email);
  startChat(roomId);
}

/* ----------------- 모달 창 열기 ----------------- */

function openModal() {
  modal.innerHTML = `
  <link href="css/profile.css" rel="stylesheet">
    <div class="msmodal">
      <div class="msmodal-content">
      <div class="msheader">
        <h2>채팅</h2>
         <button type="button" class="messageInvite" id ="messageInvite" onclick="handleChatInvite()"><img id="messageBackBtn" src="images/messageInvite.svg"></button>
        </div>
        <div class="search">
       		 <img id = "searchimg" src="images/search.svg">
       		 <input type="text" id="search" oninput="allChatsearchList()" placeholder="대화상대 검색" autocomplete="off" style="padding-left:35px;">
		</div>
		<div id="chatRoomList" style = "margin-top:20px;"> 
        </div>
      </div>
    </div>
  `;
}

function chatInvite() {
  modal.innerHTML = `
  <link href="css/profile.css" rel="stylesheet">
    <div class="msmodal">
      <div class="msmodal-content">
      <div class="msheader">
        <h2>대화상대 초대</h2>
         <button type="button" class="messageBack" id ="messageBack" onclick="inviteBack()"><img id="messageBackBtn" src="images/messageBackBtn.svg"></button>
        </div>
        <div class="search">
       		 <img id = "searchimg" src="images/search.svg">
 			 <input type="text" id="search" oninput="allInvitesearchList()" placeholder="대화상대 검색" autocomplete="off" style="padding-left:35px;">
		</div>
		<div id="inviteFollow" style = "margin-top:20px;">
        </div>
      </div>
    </div>
  `;
}

function chat(Email) {
  modal.innerHTML = `
  <link href="css/profile.css" rel="stylesheet">
    <div class="msmodal">
      <div class="msheader">
        <h4 id="audience">`+
        Email+
        `</h4>
        <button type="button" class="messageBack" id ="messageBack" onclick="roomBackModal(userEmail)"><img id="messageBackBtn" src="images/messageBackBtn.svg"></button>
        </div>
        <div>
        <hr style="background-color: rgba(0,0,0,0.1); border:none; height : 3px;">
        </div>
        <div id="chatList">
        </div>
        <div class="sendMsgBox">
             <hr style="background-color: rgba(0,0,0,0.1); border:none; height : 3px; ">
         	 <div class="msgBox">
 			 <input type="text" class="chatContent" id="chatContent" onkeyup="if(window.event.keyCode==13){semdMsg()}">
 			 <button type="button" class="semdMsgButton" id ="semdMsgButton" onclick="semdMsg()"><img id="messageSendInsertIcon"src="images/messageSendInsertIcon.svg" ></button>
 			 </div>
		</div>
    </div>
  `;
}

/* ----------------- 여기서 부터 팔로워 모달창 ----------------- */

function chatFollowAcceptListList(userEmail){
	$.ajax({
		type: "get",
		url: "/getAcceptList",
		data: {
			userEmail:userEmail,
		},
		success: function(obj){
			if(obj=="") return;
			chatroomList = JSON.stringify(obj)
			console.log("미수락:"+chatroomList);
			for(var i = 0; i<obj.length; i++){
				addFollowerList(obj[i].friendName, obj[i].userImage, obj[i].friendEmail);
			}
			
		}
	})
}

function updateFollowerFunction(userEmail, Email){
	$.ajax({
		type: "get",
		url: "/friendAccept",
		data: {
			userEmail:userEmail,
			freindEmail:Email,
		},
		success: function(data){
			console.log("친구수락:"+ data);
			succeceFollwer();
			chatFollowAcceptListList(userEmail);
		}
	})
}

function addFollowerList(name,image,Email){
	$('#followerList').append(
		'<div id="'+name+'invite" style="height:55px;">'+
		'<div id= "imgarea" style="float:left";width: 55px; height:55px;>'+
		'<img src = "'+image+'" style="width: 55px; height:55px; border-radius:50%; object-fit: cover;">'+
		'</div>'+
		'<div id="email" style="margin-left:63px">'+
		name+
		'<img src="images/alarmFollowBtn.svg" id="'+Email+'" onclick="updateFollowerFunction(userEmail, this.id)" style="float: right;">'+
		'</div>'+
		'</div>'+
		'<hr>'
		
	)
}

function succeceFollwer(){
	$('#followerList *').remove();
}



function clickFollowBtn(){
	var msgImage = document.getElementById('mainMessageButtonfalse');
	var followImage = document.getElementById('mainAlarmFalse');
	if(followImage.src.match("images/mainAlarmFalse.png")){
	if(msgImage.src.match("images/mainMessageTrue.svg")){
		msgImage.src = "images/mainMessageFalse.png";
		closeModal();
	}
	console.log("켜짐");
	followImage.src = "images/mainLikeTrue.svg";
	openFollower();
	chatFollowAcceptListList(userEmail);
	}else{
	console.log("꺼짐");
	followImage.src = "images/mainAlarmFalse.png";
	closeFollow();
	}
}

function closeFollow(){
	modal.innerHTML = '';
}





/* ----------------- 팔로워 모달 창 열기 ----------------- */
function openFollower(){
	modal.innerHTML = `
  <link href="css/profile.css" rel="stylesheet">
    <div class="msmodal">
      <div class="msmodal-content">
      <div class="msheader">
        <h2>알림</h2>
        </div>
		<div id="followerList"> 
        </div>
      </div>
    </div>
  `;
}
