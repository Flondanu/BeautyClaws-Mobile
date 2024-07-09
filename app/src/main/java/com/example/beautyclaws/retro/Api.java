package com.example.beautyclaws.retro;

import com.example.beautyclaws.models.APIResponse;
import com.example.beautyclaws.models.LoginReq;
import com.example.beautyclaws.models.UserReq;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    //url can change evrytime you restart ngrok, rem to update here
    String BASE_URL = "https://127.0.0.1:4040/beautyclaws/myapi/";

    @POST("users/create")
    Call<APIResponse> createUser(@Body UserReq userReq);

    @POST("users/login")
    Call<APIResponse> login(@Body LoginReq loginReq);


}
