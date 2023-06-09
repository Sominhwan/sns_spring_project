/* 특정 메일 데이터 검색 후 넣기 */
$(document).ready(function () { 
    const num = document.getElementById("num").value;	
    $.ajax({
        url : "/admin/getSentMailDetailData",
        type : "post",
        dataType : "json",
        global: false,
        data : {
            num: num
        },
        success : function(obj){
            console.log(obj);
            document.getElementById("mailTitle").innerText = obj[0].title;
            document.getElementById("receiverWrap").innerHTML = '<span id="receiverEmail">'+ obj[0].email +'</span>';
            document.getElementById("senderTime").innerText = obj[0].sendTime;

            const str = obj[0].fileName;
            const arr = str.split(", ");
            const fileNum = arr.length;

            const str2 = obj[0].attachFile;
            const arr2 = str2.split(", ");

            var attachFile = "";
            var fileCheck = "";
            for (let index = 0; index < fileNum; index++) {
                if(arr[index].includes('.txt')){
                    fileCheck = '<img id="fileImg" src="/adminImages/text.svg"/>';
                } else {
                    fileCheck = '<img id="fileImg" src="'+arr2[index]+'"/>';
                }
                attachFile+= '<li id="fileItem">' + 
                                fileCheck + 
                                '<span id="fileName">'+arr[index]+'</span>' +        
                                '<a id="img-preview" href="'+arr2[index]+'" target="_blank"><span id="preview">미리보기</span></a>' + 
                                '<a id="img-preview" href="/admin/downloadFile?num='+obj[0].sendmail_idx+'&file='+arr[index]+'"><img id="download" src="/adminImages/feather-download.svg"></a>' + 
                             '</li>';      
            }
            var table = document.getElementById("mail-content");
            table.innerHTML = "";

            if(obj[0].fileName != '-'){
                table.innerHTML += '<div id="file-attachment-summary">' + 
                                     '첨부 <strong>'+fileNum+'</strong>개' +
                                     '<img id="arrow-down" src="/adminImages/arrow-down.svg" onclick="arrowBtn()"/>' +
                                     '<img id="arrow-up" style="display: none;" src="/adminImages/arrow-up.svg" onclick="arrowBtn()" />' +
                                    '</div>' + 
                                    '<div id="attachment-inner" style="display: none;">' +
                                    '<ul class="fileList">' +
                                    attachFile +
                                    '</ul>' +
                                  '</div>' +
                                  '<div id="mail-content-text">' +
                                  '</div>';  
            } else{
                table.innerHTML += '<div id="mail-content-text"></div>';
            }
            document.getElementById("mail-content-text").innerHTML = obj[0].content;
        }
    });
});

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
  }

function openPopupWindow(){
    var num = getUrlParameter('num');
    var popupUrl = "/admin/adminSentMailbox/popup/read?num="+num;

    var popupWidth = 1000;
    var popupHeight = 700;
    var leftPosition = (window.screen.width);
    var topPosition = (window.screen.height);
    
    window.open(popupUrl, "_blank", "width=" + popupWidth + ", height=" + popupHeight + ", left=" + 0 + ", top=" + 0);
}

// 이메일 클럽보드 복사 + 애니매이션 효과
function clubBoard(){
    window.navigator.clipboard.writeText(document.getElementById("senderEmail").innerText).then(() => {
        $('#notification-container').fadeIn();
        setTimeout(() => {
            $('#notification-container').fadeOut();
        }, 2500)
    });
}
// 이메일 클럽보드 복사
function clubBoard2(){
    window.navigator.clipboard.writeText(document.getElementById("receiverEmail").innerText).then(() => {
        $('#notification-container').fadeIn();
        setTimeout(() => {
            $('#notification-container').fadeOut();
        }, 2500)
    });
}