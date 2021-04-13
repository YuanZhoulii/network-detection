package com.blaze.network.common.exception;

/**
 * @author yuanzhouli
 * @date 2021/4/8 - 0:05
 */

public class ErrorMsg {
    private String nickname;
    private String email;
    private String mobile;
    private String oldPassword;
    private String newPassword;
    private String gender;

    public ErrorMsg() {
        this.nickname="";
        this.email="";
        this.mobile="";
        this.oldPassword="";
        this.newPassword="";
        this.gender="";
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
