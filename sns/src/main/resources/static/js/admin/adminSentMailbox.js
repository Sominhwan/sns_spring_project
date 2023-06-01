var totalData; //총 데이터 수
var dataPerPage = 15; //한 페이지에 나타낼 글 수
var pageCount = 5; //페이징에 나타낼 페이지 수
var globalCurrentPage=1; //현재 페이지
var dataList; //표시하려하는 데이터 리스트
var attachFile = [];

/* 주소록 검색 */
$(document).ready(function () { 		
    $.ajax({
        url : "/admin/getSentMailData",
        type : "post",
        dataType : "json",
        global: false,
        success : function(obj){
            totalData = obj.length;
            dataList = obj;
            for(let i = 0; i<totalData; i++){
                if(dataList[i].attachFile != '-'){
                  attachFile[i] = '<img src="/adminImages/attachmentIcon.svg" />';
                } else{
                  attachFile[i] = '';
                }
            }
            // 글목록 표시 호출 (테이블 생성)
            displayData(1, dataPerPage);
            // 페이징 표시호출
            paging(totalData, dataPerPage, pageCount, 1);
        }
    });
});

function displayData(currentPage, dataPerPage){
    var table = document.getElementById("ajaxTable4");
    table.innerHTML = "";

    currentPage = Number(currentPage);
    dataPerPage = Number(dataPerPage);
   
    for(var i = (currentPage-1) * dataPerPage; i< (currentPage-1) * dataPerPage + dataPerPage; i++){

        //var tr = $("#mail-tr").parent().parent().eq(i);
        //var td = tr.children();
        //alert(td.eq(0).text());
        table.innerHTML += '<tr id="mail-tr" style="cursor: pointer;">' +
                            '<td scope="row" id="num-row">'+ (i+1) +'</td>' +
                            '<td scope="row" id="attachFile-row">'+ attachFile[i] +'</td>' +
                            '<td scope="row" id="email-row">'+ dataList[i].email +'</td>' +
                            '<td scope="row" id="title-row">'+ dataList[i].title +'</td>' +
                            '<td scope="row" id="time-row">'+ dataList[i].sendTime +'</td>' +
                            '</tr>';         
    };		
}

function paging(totalData, dataPerPage, pageCount, currentPage) {
    totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수
    if(totalPage<pageCount){
      pageCount=totalPage;
    }
    
    let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
    let last = pageGroup * pageCount; //화면에 보여질 마지막 페이지 번호
    
    if (last > totalPage) {
      last = totalPage;
    }
    
    let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
    let next = last + 1;
    let prev = first - 1;
    
    console.log(first);
    console.log(last);
    console.log(next);
    console.log(prev);

    let pageHtml = "";
  
    if (prev > 0) {
      pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
    }
  
   //페이징 번호 표시 
    for (var i = first; i <= last; i++) {
      if (currentPage == i) {
        pageHtml +=
          "<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
      } else {
        pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
      }
    }
  
    if (last < totalPage) {
      pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
    }
  
    $("#paging-ul").html(pageHtml);
    let displayCount = "";
    displayCount = "/ 총 " + totalData + "건";
    $("#totalMessage").text(displayCount);
  
  //페이징 번호 클릭 이벤트 
  $("#paging-ul li a").click(function () {
    let $id = $(this).attr("id");
    selectedPage = $(this).text();

    if ($id == "next") selectedPage = next;
    if ($id == "prev") selectedPage = prev;
    
    //전역변수에 선택한 페이지 번호를 담는다...
    globalCurrentPage = selectedPage;
    //페이징 표시 재호출
    paging(totalData, dataPerPage, pageCount, selectedPage);
    //글 목록 표시 재호출
    displayData(selectedPage, dataPerPage);
  });  
}

$("#dataPerPage").change(function () {
  dataPerPage = $("#dataPerPage").val();
  //전역 변수에 담긴 globalCurrent 값을 이용하여 페이지 이동없이 글 표시개수 변경 
  paging(totalData, dataPerPage, pageCount, globalCurrentPage);
  displayData(globalCurrentPage, dataPerPage);
});

$('#sendSentMailBoxTable').on("click","tr", function(){
  location.href="/admin/adminSentMailbox/read?num="+dataList[this.children[0].innerText-1].sendmail_idx;
});