window.onload = function(){
    getSentMailData();
}
    	
/* 주소록 검색 */
function getSentMailData(){	  		
    $.ajax({
        url : "/admin/getSentMailData",
        type : "post",
        dataType : "json",
        global: false,
        success : function(obj){
            //var result = obj.result; 
            //console.log(obj);
            searchProcess(obj);
        },
        error : function(xhr, status, error){
            //alert("통신 실패");
        }
    });
}

function searchProcess(result){
    var table = document.getElementById("ajaxTable4");
    table.innerHTML = "";
       $.each(result , function(i){
           table.innerHTML += '<tr onclick="changePage()" style="cursor: pointer;">' +
                                '<td scope="row" id="num-row">'+ (i+1) +'</td>' +
                                '<td scope="row" id="attachFile-row"><img src="/adminImages/attachmentIcon.svg" /></td>' +
                                '<td scope="row" id="email-row">'+ result[i].email +'</td>' +
                                '<td scope="row" id="title-row">'+ result[i].title +'</td>' +
                                '<td scope="row" id="time-row">'+ result[i].sendTime +'</td>' +
                              '</tr>';
           
    });		
}