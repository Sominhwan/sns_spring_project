/* 특정 메일 데이터 검색 후 넣기 */
$(document).ready(function () { 
    const num = document.getElementById("num").value;	
    var text = "";
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
            text = obj[0].content;
            $.ajax({
                url : "/admin/papagoDetection",
                type : "post",
                global: false,
                data: {
                    text: text
                },
                success : function(obj){
                    if(obj==='ko'){
                        return false;
                    }
                    if(obj === "en"){
                        document.getElementById("text").innerText = "영어";
                    }
                    if(obj === "ja"){
                        document.getElementById("text").innerText = "일본어";
                    }

                    translateText(obj, text, "ko");
                },
                error : function(xhr, status, error){
                    alert("통신 실패");
                }
            });
        }
    });	
});

function translateText(languageType, text, target){
    if(languageType === "한국어"){
        languageType = "ko";
    } 
    if(languageType === "영어"){
        languageType = "en";
    }
    if(languageType === "일본어"){
        languageType = "ja";
    }
    if(target === "한국어"){
        target = "ko";
    }
    if(target === "영어"){
        target = "en";
    }
    if(target === "일본어"){
        target = "ja";
    }
    if(languageType==="ko" && target==="ko"){
        return false;
    }
    if(languageType==="en" && target==="en"){
        return false;
    }
    if(languageType==="ja" && target==="ja"){
        return false;
    }

    $.ajax({
        url : "/admin/papagoTranslate",
        type : "post",
        global: false,
        data: {
            text: text,
            source: languageType,
            target: target
        },
        success : function(obj){
           console.log(obj);
           document.getElementById("mail-content-text").innerHTML = obj;    
        },
        error : function(xhr, status, error){
            alert("통신 실패");
        }
    });
}

// 언어 변경하기
function changeLanguage(){ 
    $('#upButton').css('display', 'none');          	    
    $('#downButton').css('display', 'block');  
    $('#layer_context').css('display', 'none');

    $('#upButton2').css('display', 'none');          	    
    $('#downButton2').css('display', 'block');  
    $('#layer_context2').css('display', 'none');      
    
    const language1 = document.getElementById("text").innerText;
    const language2 = document.getElementById("text2").innerText;
    document.getElementById("text").innerText = language2;
    document.getElementById("text2").innerText = language1;
    translateText(document.getElementById("text").innerText, document.getElementById("mail-content-text").innerHTML, document.getElementById("text2").innerText);
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

// $(document).mouseup(function(e) {
//     var container = $("#layer_context");
//     var container_1 = $(".button_translate_wrap");
//     var container2 = $("#layer_context2");
//     var container2_1 = $(".button_translate_wrap2");
//     // 클릭된 요소가 div 자체이거나 div의 하위 요소인 경우에는 숨기지 않음
//     if (!container.is(e.target) && !container_1.is(e.target) && container.has(e.target).length === 0) {
//       // 클릭된 요소가 div 바깥 영역인 경우 div 숨기기
//       $('#upButton').css('display', 'none');          	    
//       $('#downButton').css('display', 'block');  
//       container.hide();
//     }
//     if (!container2.is(e.target) && !container2_1.is(e.target) && container2.has(e.target).length === 0) {
//         $('#upButton2').css('display', 'none');          	    
//         $('#downButton2').css('display', 'block');  
//         container2.hide();
//       }    
//   });