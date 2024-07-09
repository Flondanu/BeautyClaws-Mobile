package com.example.beautyclaws.models;

import com.google.gson.annotations.SerializedName;

public class UserReq {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

//    @SerializedName("confirmpassword")
//    private String confirmpassord;

    @SerializedName("email")
    private String email;


    @SerializedName("mobile")
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
