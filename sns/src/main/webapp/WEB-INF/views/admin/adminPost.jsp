<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <link rel="stylesheet" href="/css/admin/adminPostPage.css" />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>관리자페이지 - Photalk</title>
    <script type="text/javascript">
    	var request = new XMLHttpRequest();
    	
    	function searchFunction(){
    		request.open("Post", "/admin/PostSearch?userEmail=" + encodeURIComponent(document.getElementById("userEmail").value),true);
    		request.onreadystatechange = searchProcess;
    		request.send(null);
    	}
    	function searchProcess(){
    		var table = document.getElementById("ajaxTable");
    		table.innerHTML = "";
    		// 통신 성공시
    		if(request.readyState == 4 && request.status == 200){
    			var object = eval('(' +request.responseText + ')');
    			var result = object.result;
    			var j = 0;
    			
    			for(var i = 0; i<result.length; i++){
    				var row = table.insertRow(0);
    				var cell = row.insertCell(0);
    				cell.innerHTML = `<input type="checkbox" name= "myCheck" id="myCheck" class="myCheck" onclick="changeColor();"/>`;		
    				for (j = 1; j < result[i].length; j++) {
						var cell = row.insertCell(j);
						cell.innerHTML = result[i][j].value;	
					}			
    				cell.innerHTML = `<button class="checkBtn">삭제</button>`;
    				// 신고 횟수 10회 이상일때 색상 변경
    				if(result[i][9].value > 9){
    					document.getElementsByTagName('td')[9].style.color = 'red';
    				}
    			}
    		}
    	}
    	  	
    /* 회원정보 삭제 */
    // 버튼 클릭시 Row 값 가져오기
		$(document).on('click', '.checkBtn', function(){
			var checkBtn = $(this);
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			var postId = td.eq(2).text(); // 이메일 선택
		    deleteUser(postId);
		});
	   	
    	function deleteUser(postId) {
    		var request = new XMLHttpRequest();
    		request.open("Post", "PostDelete?postId=" +postId,true);
    		request.onreadystatechange = deleteProcess;
    		request.send(null);
		};
	 	function deleteProcess(){
    		// 통신 성공시
    		if(request.readyState == 4 && request.status == 200){	
    			searchFunction();
    		} 
    	}
	 			
	 	/* 체크박스 체크시 색상 변경 */
		function changeColor(){ 		
			 $("input[name=myCheck]").each(function(i){
				 var tr = $("input[name=myCheck]").parent().parent().eq(i);
					var td = tr.children();
				    if( $(this).is(":checked") == true ){
				    	td.eq(0).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(1).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(2).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(3).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(4).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(5).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(6).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(7).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(8).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(9).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(10).css({"background-color":"#F5F5F5","color":"#000"});
              td.eq(11).css({"background-color":"#F5F5F5","color":"#000"});	
				    }else{
				    	td.eq(0).css({"background-color":"#fff","color":"#000"});
              td.eq(1).css({"background-color":"#fff","color":"#000"});
              td.eq(2).css({"background-color":"#fff","color":"#000"});
              td.eq(3).css({"background-color":"#fff","color":"#000"});
              td.eq(4).css({"background-color":"#fff","color":"#000"});
              td.eq(5).css({"background-color":"#fff","color":"#000"});
              td.eq(6).css({"background-color":"#fff","color":"#000"});
              td.eq(7).css({"background-color":"#fff","color":"#000"});
              td.eq(8).css({"background-color":"#fff","color":"#000"});
              td.eq(9).css({"background-color":"#fff","color":"#000"});
              td.eq(10).css({"background-color":"#fff","color":"#000"});
              td.eq(11).css({"background-color":"#fff","color":"#000"});	
				    }
				  });
		};
	 	/* 체크박스 회원정보 삭제 */
	 	$(function(){
			$("#deleteBtn").click(function(){
				if (confirm("선택한 데이터를 삭제하시겠습니까?") == true){ 
				}else{
			  	  return ;
		    	}
			
				var tdArr = new Array();
				var checkbox = $("input[name=myCheck]:checked");
				// 체크된 체크박스 값을 가져온다
				checkbox.each(function(i) {
					var tr = checkbox.parent().parent().eq(i);
					var td = tr.children();
					var postId = td.eq(2).text();
					var postIdAll = "";
					tdArr.push(postId);			
				});
				deleteCheckUser(tdArr);
		    });
		});
	 	
    	function deleteCheckUser(tdArr) {
    		var request = new XMLHttpRequest();
    		request.open("Post", "/admin/PostDelete?postIdAll=" +tdArr,true);
    		request.onreadystatechange = deletAllProcess;
    		request.send(null);
		};
	 	function deletAllProcess(){
    		// 통신 성공시
    		if(request.readyState == 4 && request.status == 200){	
    			searchFunction();
    		} 
    	}
	 	
	 	/* 로그아웃 */
	 	function logout(){
	 		if (confirm("나가겠습니까?") == true){ 
        sessionStorage.removeItem('userEmailHash');
		 		location.replace('/index');
			} else{
			    return ;
		  } 		
	 	}
	 	/* 페이지 로드시 searchFunction() 실행 */
    	window.onload = function(){
    		searchFunction();
    	}
    /* 보낸 메일함 버튼 열기 */
    function sentMailbox(){
      if ($('#sentMailbox').css('display') == 'block') {
        $('#mailSendCategory2').css('display', 'none'); 
        $('#mailSendCategory').css('display', 'block'); 
        $('#sentMailbox').css('display', 'none');
      } else {
        $('#mailSendCategory').css('display', 'none'); 
        $('#mailSendCategory2').css('display', 'block'); 
        $('#sentMailbox').css('display', 'block');          	    
      }
    } 
    </script>
  </head>
  <body>
    <div class="left-side">
    <aside>
      <div id="side-logo">
        <img src="/adminImages/adminLogo.png" alt="logo" /><a
          href="/admin/adminPage"
          id="adminLogo"
          >PhoTalk</a
        >
      </div>
      <ul>
        <li>
          <a href="/admin/adminPage" class="icon"
            ><img src="/adminImages/adminProfile.svg" alt="userImg" /><span
              class="sideText"
              >회원</span
            >
            관리</a
          >
        </li>
        <li>
          <a href="/admin/adminPost"
            ><img
              src="/adminImages/adminPost.svg"
              alt="postImg"
              class="icon"
            /><span class="sideText">게시물 관리</span></a
          >
        </li>
        <li>
          <a href="/admin/adminMail"
            ><img
              src="/adminImages/adminMail.svg"
              alt="messageImg"
              class="icon"
            /><span class="sideText">메일 보내기</span></a
          >
          <img id="mailSendCategory" src="/adminImages/mailSendCategory.svg" style="position: fixed; top: 395px; left: 300px; z-index: 100; cursor: pointer;" onclick="sentMailbox()"/>
          <img id="mailSendCategory2" src="/adminImages/mailSendCategory2.svg" style="position: fixed; top: 395px; left: 300px; z-index: 100; cursor: pointer; display: none;" onclick="sentMailbox()"/>
          <a href="/admin/adminSentMailbox" id="sentMailbox" style="display: none;">
            <img
              src="/adminImages/sendMailLogo.svg"
              alt="messageImg"
              class="category-icon"
              style="position:fixed; left: 80px;"
            /><span class="sideText2" style="position: fixed; left: 130px; top: 448px; font-size: 18px;">보낸 메일함</span></a
          >          
        </li>
        <li>
          <a href="/admin/adminStatistics"
            ><img
              src="/adminImages/chartIcon.svg"
              alt="charIcon"
              class="icon"
            /><span class="sideText">통계</span></a
          >
        </li>        
      </ul>
      <!-- 로그아웃 -->
      <div id="logout">
        <img
            src="/adminImages/adminLogout.svg"
            alt="logoutImg"
            class="icon"
            style="width: 25px;"
          /><span class="sideText"><a href="#" id="logout" onclick="logout()"
            >나가기</span></a
        >
      </div>
      <!-- 푸터 시작 -->
      <footer class="sidebar-footer">
        <div class="footer-inner">
            <span class="footer_info"><a href="#">소개</a></span>
            <span class="footer_info"><a href="#">채용안내</a></span>
            <span class="footer_info"><a href="#">이용약관</a></span>
            <span class="footer_info"><a href="#">도움말</a></span>
            <span class="footer_info"><a href="#">운영정책</a></span>
            <span class="footer_info"><a href="#">위치</a></span>
            <span class="footer_info"><a href="#">인기 계정</a></span>
            <span class="footer_info"><a href="/index">사이트맵</a></span>
            <span class="footer_info"><a>&copy;2023 Social Net Work Project</a></span>
      </div>
        </div>
      </footer>
    </aside>
</div>
    <!-- 게시물 네비게이션 바 -->
    <nav id="navbar">
        <span id = "adminPostLogo">
        <img src="/adminImages/adminPostLogo.svg" />
        </span>
        <span id="adminPostLogo-text">게시물 목록</span>    
    </nav>
    <!-- 게시물정보 컨텐츠 -->
    <div class="userTable">
        <div id="postList">
            게시물 목록
        </div>
        <div id="searchBox">
            <input class="search" name="search" id="userEmail" onkeyup="searchFunction()" type="text" placeholder="작성자를 입력하세요" maxlength="30"/>
            <button type="button" class="searchBtn" onclick="searchFunction();"></button>
        </div>
        <!-- 게시물정보 테이블 -->
        <div class="postTable-scroll">
        <div class="postTable-content">
        <table class="postTable">
            <thead id="head">
                <tr> 
                    <th scope="cols">선택</th>
                    <th scope="cols">번호</th>
                    <th scope="cols">게시물 ID</th>
                    <th scope="cols">작성자</th>
                    <th scope="cols">이미지 파일</th>
                    <th scope="cols">동영상 파일</th>
                    <th scope="cols">댓글수</th>
                    <th scope="cols">좋아요 수</th>
                    <th scope="cols">공유하기 수</th>
                    <th scope="cols">신고횟수</th>
                    <th scope="cols">날짜</th>
                    <th scope="cols" style="color: #fd3c56;">삭제</th>          
                </tr>
            </thead>
            <tbody id="ajaxTable">
            </tbody>
        </table>
        </div>
        
        </div>
        <img src="/adminImages/delete.svg" alt="deleteBtn" id="deleteBtn">
    </div>
  </body>
</html>
