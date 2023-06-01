/* 특정 메일 데이터 검색 후 넣기 */
$(document).ready(function () { 
    const num = document.getElementById("num").value;	
    $.ajax({
        url : "/admin/getSentMailDetailData",
        type : "post",
        dataType : "json",
        global: false,
        data : {
            num: num,
            num2: "2"
        },
        success : function(obj){

        }
    });
});