<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" type="image/x-icon" href="/images/loginLogo.png" />
    <link rel="stylesheet" href="/css/admin/translatePage.css" />
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <title>관리자페이지 - Photalk</title>
    <script type="text/javascript">
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
        /* 번역 언어 박스 오픈 이벤트 */
        function languageBtn(){
          if ($('#downButton').css('display') == 'block') {
            $('#downButton').css('display', 'none');
            $('#upButton').css('display', 'block');
            $('#layer_context').css('display', 'block');     
          } else {
            $('#upButton').css('display', 'none');          	    
            $('#downButton').css('display', 'block');  
            $('#layer_context').css('display', 'none');           	    
          }          
        }
        function languageBtn2(){
          if ($('#downButton2').css('display') == 'block') {
            $('#downButton2').css('display', 'none');
            $('#upButton2').css('display', 'block');
            //$('#attachment-inner').css('display', 'block');     
          } else {
            $('#upButton2').css('display', 'none');          	    
            $('#downButton2').css('display', 'block');  
            //$('#attachment-inner').css('display', 'none');           	    
          }          
        }
    </script>
  </head>
  <body>
    <!-- 번역 네브바 박스 -->
    <div id="translateBox">
      <div class="popup_title">메일 팝업창</div>
      <div class="button_translate_wrap">
        <button type="button" class="button_translate" onclick="languageBtn()">
          <span id="text" class="text">한국어</span>
          <img id="downButton" class="languageChangeButton" src="/adminImages/downButton.svg" onclick="languageBtn()"/>
          <img id="upButton" class="languageChangeButton" src="/adminImages/upButton.svg" onclick="languageBtn()" style="display: none;"/>
        </button>
      </div>
      <img id="exchangeLanguage" src="/adminImages/exchangeLanguage.svg" onclick="changeLanguage()"/>  
      <div class="button_translate_wrap2">
        <button type="button" class="button_translate" onclick="languageBtn2()">
          <span id="text2" class="text">일본어</span>
          <img id="downButton2"class="languageChangeButton" src="/adminImages/downButton.svg" onclick="languageBtn2()"/>
          <img id="upButton2" class="languageChangeButton" src="/adminImages/upButton.svg" onclick="languageBtn2()" style="display: none;"/>
        </button>
      </div>

    </div>
    <!-- 번역 언어 선택 박스 -->
    <div id="layer_context"class="layer_context" style="display: none;">
      <ul class="context_menu">
          <li class="context_item">
              <button type="button" class="button_context_item">한국어</button>
          </li>
          <li class="context_item">
              <button type="button" class="button_context_item">영어</button>
          </li>
          <li class="context_item">
              <button type="button" class="button_context_item">일본어</button>
          </li>
          <li class="context_item">
              <button type="button" class="button_context_item">중국어(간체)</button>
          </li>
          <li class="context_item">
              <button type="button" class="button_context_item">중국어(번체)</button>
          </li>
      </ul>  
  </div>   
    <!-- 상세 메일정보 컨텐츠 -->
    <div class="mailTable" id="mailTable">
        <input type="hidden" id="num" value="${num}"/>

        <div id="mailTitle">   
        </div>
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
        <!-- 첨부파일 리스트 -->
        <div id="mail-content">
          <div id="attachment-inner" style="display: none;">
          </div> 
          <div id="mail-content-text">
          </div>     
        </div>   
    </div>
  </body>
  <script src="/js/admin/adminTranslate.js"></script>
</html>
