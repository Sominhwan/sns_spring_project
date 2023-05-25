<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-color: #ececec">
  <head>
    <meta charset="UTF-8" />
    <title>Search University</title>
    <link
      rel="shortcut icon"
      type="image/x-icon"
      href="/images/loginLogo.png"
    />
  </head>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <body>
    <div
      id="side-bar"
      class="side-bar"
      style="
        bottom: 0px;
        left: 0px;
        padding-left: 10px;
        height: 380px;
        background-color: white;
        overflow-y: auto;
        margin: 70px 10px 10px 10px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
      "
    >
      <span
        class="a"
        style="position: absolute; top: 130px; left: 100px; font-weight: bold"
        >Tip</span
      >
      <span class="b" style="position: absolute; top: 160px; left: 100px"
        >대학교를 검색 한 후 클릭을 하면
      </span>
      <span class="x" style="position: absolute; top: 190px; left: 100px"
        >학교명으로 자동으로 넘어갑니다.
      </span>
      <span
        class="d"
        style="position: absolute; top: 400px; left: 170px; color: gray"
        >powered by PhoTalk</span
      >
      <ul id="universityList" style="list-style-type: none; padding-left: 0; margin-top: -10">

      </ul>
    </div>

    <form
      name="searchForm"
      id="searchForm"
      method="get"
      action=""
      onsubmit="return false"
    >
      <label>
        <input
          type="text"
          name="uniName"
          id="uniName"
          style="
            z-index: 1000;
            height: 45px;
            width: 100%;
            position: absolute;
            top: 0px;
            left: 0px;
            border: none;
            outline: none;
            font-size: 20px;
            padding-left: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
          "
          placeholder="대학 이름을 입력하세요"
        />
      </label>
      <img
        id="search"
        onclick="searchSchool()"
        src="/images/adminSearch.svg"
        style="
          position: fixed;
          top: 10px;
          right: 10px;
          cursor: pointer;
          z-index: 1000;
        "
      />
      <!-- <input type="submit" value="검색" style="display: none" /> -->
    </form>

    <input type="hidden" name="selectedUni" id="selectedUni" />
    <form id="inputForm" method="post" action="/update1">
      <input type="hidden" name="selectedUni" id="selectedUniInput" />
    </form>
  </body>
  <script>
    function searchSchool() {
      var uniName = document.getElementById("uniName").value;
      $.ajax({
        url: "/getUniversityData",
        type: "get",
        data: { university: uniName },
        success: function (obj) {
          console.log(obj.university);
          var table = document.getElementById("universityList");
          table.innerHTML = "";
          for (let i = 0; i < obj.university.length; i++) {
            var uniName = obj.university[i].uniName;
            var listItem = document.createElement("li");
            listItem.textContent = uniName;
            listItem.style.padding = "10px 0";
            listItem.style.cursor = "pointer";
            listItem.style.borderBottom = "1px solid #ccc";
            table.appendChild(listItem);
            listItem.addEventListener("click", createClickHandler(uniName));
          }
          document.querySelector(".a").style.display = "none";
          document.querySelector(".b").style.display = "none";
          document.querySelector(".x").style.display = "none";
          document.querySelector(".d").style.display = "none";
        },
        error: function (obj) {
          alert("오류 발생");
        },
      });
    }
    
    function createClickHandler(uniName) {
      return function () {
        opener.document.getElementById("school").value = uniName;
        window.close();
      };
    }
    // Enter 키 이벤트 처리
document.getElementById("uniName").addEventListener("keydown", function (event) {
  if (event.keyCode === 13) {
    searchSchool();
    event.preventDefault(); // 폼 제출 방지
  }
});
  

  

    document.addEventListener("DOMContentLoaded", function () {
      document.querySelectorAll("#selectedUni").forEach(function (element) {
        element.addEventListener("input", function () {
          document.getElementById("selectedUniInput").value = element.value;
        });
      });
    });


    function checkForm() {
      var uniName = document.getElementById("uniName").value.trim();
      if (uniName == "") {
        alert("대학 이름을 입력해주세요.");
        return false;
      }
      return true;
    }

    function setTextBoxValue(value) {
      document.getElementById("selectedUni").value = value;
    }
    function setInputValue() {
      var form = document.getElementById("inputForm");
      form.submit();
    }
  </script>
</html>
