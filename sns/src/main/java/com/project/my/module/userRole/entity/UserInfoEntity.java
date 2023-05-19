package com.project.my.module.userRole.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoEntity {
    private String userName;
    private String userGender;
    private String userNickName;
    private String userEmail;
    private String userPwd;
    private String userPN;
    private String userSchool;
    private String userAddress;
    private String userSocial;
    private String userSocialId;
    private String emailHash;
    private int emailcertification;
    private String userImage;
    private String userRegDate;
    private int userAd;
    private String userRegTime;
    private String userInfoType;
    private String role;

    public UserInfoEntity(String userName, String userGender, String userNickName, String userEmail, String userPwd, String userPN, String userSchool, String userAddress,
        String userSocial, String userSocialId, String emailHash, int emailcertification, String userImage, int userAd, String userInfoType, String role) {      
        this.userName = userName;
        this.userGender = userGender;
        this.userNickName = userNickName;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userPN = userPN;
        this.userSchool = userSchool;
        this.userAddress = userAddress;
        this.userSocial = userSocial;
        this.userSocialId = userSocialId;
        this.emailHash = emailHash;
        this.emailcertification = emailcertification;
        this.userImage = userImage;
        this.userAd = userAd;
        this.userInfoType = userInfoType;
        this.role = role;
    }

    // public UserInfoEntity createUser(String userName, String userGender, String userNickName, String userEmail, String userPwd, String userPN, String emailHash, int userAd) // 일반회원 가입
    //     return new UserInfoEntity(userName, userGender, userNickName, userEmail, userPwd, userPN, "-", "-", "-", "-", emailHash, 0, "images/profile.svg", userAd , "일반", "USER" );
    // }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserInfoType(String userInfoType) {
        this.userInfoType = userInfoType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setUserPN(String userPN) {
        this.userPN = userPN;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserSocial(String userSocial) {
        this.userSocial = userSocial;
    }

    public void setUserSocialId(String userSocialId) {
        this.userSocialId = userSocialId;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public void setEmailcertification(int emailcertification) {
        this.emailcertification = emailcertification;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setUserRegDate(String userRegDate) {
        this.userRegDate = userRegDate;
    }

    public void setUserAd(int userAd) {
        this.userAd = userAd;
    }

    public void setUserRegTime(String userRegTime) {
        this.userRegTime = userRegTime;
    }
}
