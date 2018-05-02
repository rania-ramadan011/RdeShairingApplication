package com.example.rania.itigraduationproject.Interfaces;

import com.example.rania.itigraduationproject.PureClasses.DriverCarInfo;
import com.example.rania.itigraduationproject.PureClasses.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Rania on 4/25/2018.
 */

public interface Service {
    public static final String BASE_URL = "http://10.0.2.2:8084/RideSharingProWS/rest/";

    @GET("getUser.json")
    Call<User>getUser();

    @POST("user")
    Call<User>sendUser(@Body User user );

    @POST("getUserByEmailAndPassword")
    Call<User>getUserByEmailAndPassword(@Body User user);

    @POST("driverSignUpWs")
    Call<DriverCarInfo>saveDriverObject(@Body DriverCarInfo driverCarInfo);





}
