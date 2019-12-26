package com.example.loginactivitytest.service;

import com.example.loginactivitytest.model.TransportScheduleUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TransportscheduleAPI {
    @GET("viewAllTransportSchedule")
    Call<List<TransportScheduleUser>> getUserData();

}
