<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <link rel="stylesheet" href="/css/admin/readPage.css" />
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
            $('#sentMailbox').css('display', 'none');
          } else {
            $('#sentMailbox').css('display', 'block');          	    
          }
        }
        /* 첨부파일 버튼 이벤트 */
        function arrowBtn(){
          if ($('#arrow-down').css('display') == 'block') {
            $('#arrow-down').css('display', 'none');
            $('#arrow-up').css('display', 'block');
            $('#attachment-inner').css('display', 'block');     
          } else {
            $('#arrow-up').css('display', 'none');          	    
            $('#arrow-down').css('display', 'block');  
            $('#attachment-inner').css('display', 'none');           	    
          }          
        }
        /* 메일 프린터하기 */
        function divPrint(){
          var initBody = document.body.innerHTML;
          window.onbeforeprint = function(){
            document.body.innerHTML = document.getElementById('mailTable').innerHTML;
          }
          window.onafterprint = function(){
            document.body.innerHTML = initBody;
          }
          window.print();
        }
    </script>
  </head>
  <body>
    <div class="left-side">
    <aside>
      <div id="side-logo">
        <img src="/adminImages/adminLogo.png" alt="logo" /><a href="/admin/adminPage" id="adminLogo">PhoTalk</a>
      </div>
      <ul id="read-ul">
        <li id="read-li">
          <a href="/admin/adminPage" class="icon"
            ><img src="/adminImages/adminProfile.svg" alt="userImg" /><span
              class="sideText"
              >회원</span
            >
            관리</a
          >
        </li>
        <li id="read-li">
          <a href="/admin/adminPost"
            ><img
              src="/adminImages/adminPost.svg"
              alt="postImg"
              class="icon"
            /><span class="sideText">게시물 관리</span></a
          >
        </li>
        <li id="read-li">
          <a href="/admin/adminMail"
            ><img
              src="/adminImages/adminMail.svg"
              alt="messageImg"
              class="icon"
            /><span class="sideText">메일 보내기</span></a
          >
          <img src="/adminImages/mailSendCategory.svg" style="position: fixed; top: 395px; left: 300px; z-index: 100; cursor: pointer;" onclick="sentMailbox()"/>
          <a href="/admin/adminSentMailbox" id="sentMailbox" style="display: none;">
            <img
              src="/adminImages/sendMailLogo.svg"
              alt="messageImg"
              class="category-icon"
              style="position:fixed; left: 80px;"
            /><span class="sideText2" style="position: fixed; left: 130px; top: 448px; font-size: 18px;">보낸 메일함</span></a
          >          
        </li>
        <li id="read-li">
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
        <img src="/adminImages/adminLogout.svg" alt="logoutImg" class="icon" style="width: 25px;"/>
        <span class="sideText"><a href="#" id="logout" onclick="logout()">나가기</span></a>
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
    <!-- 상세 메일정보 네비게이션 바 -->
    <nav id="navbar">
        <span id = "material-message-logo">
        <img src="/adminImages/material-message.svg" />
        </span>
        <span id="adminProfile-text">상세 메일함</span>    
    </nav>
    <!-- 상세 메일정보 컨텐츠 -->
    <div class="mailTable" id="mailTable">
        <input type="hidden" id="num" value="${num}"/>
        <div id="mailTitle">   
        </div>
        <div id="printPage" onclick="divPrint()">
          <img id="printIcon" src="/adminImages/feather-printer.svg">
            인쇄
          </a>
        </div>
        <div style="position: fixed; left: 1790px; top: 142px; color: #eee;">|</div>
        <div id="translate" onclick="openPopupWindow()">번역</div>
        <div id="sender">   
          보낸사람
        </div>
        <div id="senderWrap">   
          <span id="senderEmail">photalk2@gmail.com</span>
        </div>
        <div id="receiver">   
          받는사람
        </div>  
        <div id="receiverWrap">   
        </div> 
        <div id="senderTime">
        </div>    
        <hr id="hr"> 
        <!-- 첨부파일 리스트 -->
        <div id="mail-content">
          <div id="attachment-inner" style="display: none;">
          </div> 
          <div id="mail-content-text">
          </div>     
        </div>   
    </div>
  </body>
  <script src="/js/admin/adminRead.js"></script>
</html>
