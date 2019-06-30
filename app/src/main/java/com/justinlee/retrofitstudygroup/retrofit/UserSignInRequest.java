package com.justinlee.retrofitstudygroup.retrofit;

import com.google.gson.annotations.SerializedName;

public class UserSignInRequest {
    @SerializedName("param1")
    String param1;
    @SerializedName("param2")
    String param2;

    public UserSignInRequest(String id, String pw) {
        param1 = id;
        param2 = pw;
    }
}

