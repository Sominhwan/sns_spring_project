/* 로그인 휴대폰 이미지 페이드인 기능 */
var now_img, next_img;
      setInterval(function () {
        now_img = $(".fade_container img:eq(0)");
        next_img = $(".fade_container img:eq(1)");
        next_img
          .addClass("active")
          .css("opacity", 0)
          .animate({ opacity: 1 }, 2000, function () {
            $(".fade_container").append(now_img); //콜백
            now_img.removeClass("active");
          });
      }, 3000);

/* 로그인 이메일 지우는 아이콘 기능 */
var $ipt = $('#userEmail'), $clearIpt = $('#userEmailClear');
  
$ipt.keyup(function(){
  $("#userEmailClear").toggle(Boolean($(this).val()));
});
  
$clearIpt.toggle(Boolean($ipt.val()));
$clearIpt.click(function(){
  $("#userEmail").val('').focus();
  $(this).hide();
  validate();
});

/* 로그인 비밀번호 숨기기 보이기 기능 */
$("#password").on("keyup", function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    $("#checkKey").triggerHandler("click"); 
  } else {
    if (this.value) {
      $("#keyShow").css("display", "inline-block");
    } else {
      $("#keyShow").hide();
    }
  }
});


$("#keyShow").on("click", function() {
  if ($("#password").attr("type") == "password") {
    $("#password").attr("type", "text");
    $("#keyShow > img").attr({ src: "/images/pwdEyeBtnTrue.svg" });
  } else {
    $("#password").attr("type", "password");
    $("#keyShow > img").attr({ src: "/images/pwdEyeBtnFalse.svg" });
  }
});

/* 로그인 버튼 활성화 */
const button = document.getElementById("loginBtn");
const inputEmail = document.getElementById("userEmail");
const inputPw = document.getElementById("password");

inputEmail.addEventListener("keyup", validate);
inputPw.addEventListener("keyup", validate);

function validate() {
  if (!(inputEmail.value && inputPw.value)) {
    button.disabled = true;
    button.style.cursor = "default";
    button.classList.remove("loginBtnDisabled");
  } else {
    button.disabled = false;
    button.style.cursor = "pointer";
    button.classList.add("loginBtnDisabled");
  }
}

/* 자동로그인 팝업창 */
function popOpen() {
  var modalPop = $('.modal-wrap');
  var modalBg = $('.modal-bg'); 

  $(modalPop).show();
  $(modalBg).show();
}

function popClose() {
 var modalPop = $('.modal-wrap');
 var modalBg = $('.modal-bg');

 $(modalPop).hide();
 $(modalBg).hide();
}

// /* 로그인 성공시 */
// function loginOK() {
//   if(document.getElementById('login_container').style.display !== 'none') {
//     document.getElementById('login_container').style.display = 'none';
//     document.getElementById('loginOK_container').style.display = 'block';
//   } else{
//     document.getElementById('loginOK_container').style.display = 'none';
//     document.getElementById('login_container').style.display = 'block';
//   }  
// }

/* 로그인 실패시 팝업 이벤트 */
// function close () { 
//     document.querySelector(".background").className = "background";
// }

// function check () { 
//   document.querySelector(".background").className = "background";
// }

// document.querySelector("#close").addEventListener('click', close);
// document.querySelector("#check").addEventListener('click', check);

