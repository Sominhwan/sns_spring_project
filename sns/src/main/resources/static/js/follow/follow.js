$.ajax({					
	type: "get",
	url: "/follow.action",
	data: {
	},
	success: function(obj) {
		var userEmail = obj.userEmail;
        var userNickName = obj.userNickName;
        var userName = obj.userName;
        var userGender = obj.userGender;
        var userSchool = obj.userSchool;
        var userPN = obj.userPN;
        var userAddress = obj.userAddress;
        var userSocial = obj.userSocial;

        $(".userEmail").text(userEmail);
        $(".userNickName").text(userNickName);
        $(".userName").text(userName);
        $(".userGender").text(userGender);
        $(".userSchool").text(userSchool);
        $(".userPN").text(userPN);
        $(".userAddress").text(userAddress);
        $(".userSocial").text(userSocial);
        $(".profile_mainprofile").attr("src", obj.userImage);
		$.ajax({
			type: "get",
			url: "/followconsent",
			data: {
				userEmail: userEmail
			},
			success: function(followList) {
				followList.forEach(function(followInfo) {
					$.ajax({
                        type: "get",
                        url: "/followuser",
                        data: {
                            userEmail: followInfo.friendEmail
                        },
                        success: function(followuser) {
                            var html = `<table><tr>`;
                            followuser.forEach(function(followuserInfo) {
                                    html=`
                                                    <td style="padding-left: 200px;">
                                                        <div class="followimage">
                                                            <img src=${followuserInfo.userImage} width="220" height="200" style="border-top-left-radius: 5px; border-top-right-radius: 5px;">
                                                        </div>
                                                        <div class="followidtext">
                                                            <span style="position: relative; left: 20px; top: 15px; color: #303030; font-size: 15px">${followuserInfo.userNickName}</span>
                                                
                                                            <a href="javascript:follow('#')"><img src="./img/followBtn.svg" class="followBtn"></a>
                                                            <a href="javascript:followCancel('#')"><img src="./img/followCancelBtn.svg" class="followCancelBtn"></a>
                                                            <!-- 팔로우 모달 -->
                                                            <div class="followmodal">
                                                                <div>
                                                                    <br>
                                                                    <span style="position: absolute; left:40px; top: 42px;"><strong id="followNickName"></strong>님을 <b style="color: #1877f2;">팔로우</b> 하였습니다.</span>
                                                                </div>
                                                                <div style="position: absolute; left:65px; top:101px">
                                                                    <img src="./img/followModalCheckBtn.svg" class="followCheck">
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                `;
                            });
                            html += `</tr></table>`;
                            $('#followrequest').append(html);
                        }
                    });
				});
                
                
			}
		});
        $.ajax({
            type: "get",
            url: "/getListPMember2",
            data: {
              userEmail: userEmail,
            },
            success: function(recomendfollow) {
              var html = `<table style="position: absolute; top:470px left:400px"><tr>`;
              recomendfollow.forEach(function(recomend) {
                console.log(recomend.friendEmail + ":" + recomend.userNickName);
                html += `
                  <td style="padding-left: 200px;">
                    <div class="followimage">
                      <img src=${recomend.userImage} width="220" height="200" style="border-top-left-radius: 5px; border-top-right-radius: 5px;">
                    </div>
                    <div class="followidtext">
                      <span style="position: relative; left: 20px; top: 15px; color: #303030; font-size: 15px">${recomend.userNickName}</span>
                  
                      <a href="javascript:follow('#')"><img src="./img/followBtn.svg" class="followBtn"></a>
                      <a href="javascript:followCancel('#')"><img src="./img/followCancelBtn.svg" class="followCancelBtn"></a>
                      <!-- 팔로우 모달 -->
                      <div class="followmodal">
                        <div>
                          <br>
                          <span style="position: absolute; left:40px; top: 42px;"><strong id="followNickName"></strong>님을 <b style="color: #1877f2;">팔로우</b> 하였습니다.</span>
                        </div>
                        <div style="position: absolute; left:65px; top:101px">
                          <img src="./img/followModalCheckBtn.svg" class="followCheck">
                        </div>
                      </div>
                    </div>
                  </td>`;
              });
              html += `</tr></table>`;
              $('#recommend').append(html);
            },
            error: function(error) {
              console.log(error);
              console.log(error.responseText);
            }
          });

	}
});
