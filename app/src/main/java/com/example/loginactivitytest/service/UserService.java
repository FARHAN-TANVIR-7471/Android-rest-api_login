package com.example.loginactivitytest.service;

import com.example.loginactivitytest.model.Login;
import com.example.loginactivitytest.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("token/generate-token")
    Call<User> login(@Body Login log);
}
