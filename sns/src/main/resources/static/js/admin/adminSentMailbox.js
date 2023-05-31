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
            var result = obj.result; 
            //searchProcess(result);
        },
        error : function(xhr, status, error){
            //alert("통신 실패");
        }
    });
}

function searchProcess(result){
    var table = document.getElementById("ajaxTable");
    table.innerHTML = "";
       $.each(result , function(i){
           table.innerHTML += '<tr><td class="chk"><input type="checkbox" name= "myCheck" id="myCheck" class="myCheck" onclick="changeColor();"/></td>' 
           + '<td class="addr">' + result[i].userEmail + '</td></tr>';
    });		
}