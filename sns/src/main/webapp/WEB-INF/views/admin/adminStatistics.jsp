<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <link rel="stylesheet" href="/css/admin/adminStatisticsPage.css" />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>관리자페이지 - Photalk</title>
    <script type="text/javascript"> 	 	
	 	/* 로그아웃 */
	 	function logout(){
	 		if (confirm("나가겠습니까?") == true){ 
        sessionStorage.removeItem('userEmailHash');
		 		location.replace('/index');
			} else{
			    return ;
		  } 		
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
    <!-- 통계 네비게이션 바 -->
    <nav id="navbar">
        <span id = "adminStatisticsLogo">
        <img src="/adminImages/adminStatisticsLogo.svg" />
        </span>
        <span id="adminStatistics-text">통계</span>    
    </nav>
    <!-- 차트 컨텐츠 -->
    <div class="chartTable">
        <div id="statistics">
            통계
        </div>      
        <div class="tab_content">
        	<input type="radio" name="tabmenu" id="tab01" checked/>
        	<label for="tab01">회원&게시물 수</label>
        	<input type="radio" name="tabmenu" id="tab02"/>
        	<label for="tab02">좋아요 수</label>
        	<input type="radio" name="tabmenu" id="tab03"/>
        	<label for="tab03">게시물 수</label>        	        	
        <div class="conbox con1" id="container1"><div id="container"></div></div>
        <div class="conbox con2" id="container2"><div id="container2_1"></div></div>
        <div class="conbox con3" id="container3">
        	<figure class="highcharts-figure">
  			<div id="container3_1"></div>
			</figure>
		</div>
		</div>           
    </div>
    
  </body>
 <script src="https://code.highcharts.com/highcharts.js"></script>
 <script src="https://code.highcharts.com/modules/data.js"></script>
 <script src="https://code.highcharts.com/modules/drilldown.js"></script>
 <script src="https://code.highcharts.com/modules/exporting.js"></script>
 <script src="https://code.highcharts.com/modules/export-data.js"></script>
 <script src="https://code.highcharts.com/modules/accessibility.js"></script>
 <script src="/js/admin/adminStatistics.js"></script>
</html>
