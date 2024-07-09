package com.example.beautyclaws.models;

import com.google.gson.annotations.SerializedName;

public class LoginReq {


    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;


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

}