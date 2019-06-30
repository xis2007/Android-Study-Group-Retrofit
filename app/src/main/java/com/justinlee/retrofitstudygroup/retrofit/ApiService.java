package com.justinlee.retrofitstudygroup.retrofit;

import com.justinlee.retrofitstudygroup.objects.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({
            "Content-Type:application/json",
            "Accept:application/json",
            "Authorization:Basic abcdefghijklmnopq"
    })
    @POST("/auth")
    Call<User> login(@Body UserSignInRequest request);

}
