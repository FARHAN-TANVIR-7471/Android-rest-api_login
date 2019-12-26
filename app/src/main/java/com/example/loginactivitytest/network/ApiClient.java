package com.example.loginactivitytest.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Farhan on 27.02.2019.
 */

public class ApiClient {
    public static final String BASE_URL = "http://192.168.0.142:8088/";
    private static Retrofit retrofit =null ;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }return  retrofit;
    }
}
