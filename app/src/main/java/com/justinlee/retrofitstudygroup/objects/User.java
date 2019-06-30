package com.justinlee.retrofitstudygroup.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("photo")
    private String photo;
    @SerializedName("hobby")
    private String hobby;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public static String mockJsonUser() {
        return "{" +
                "  \"name\":\"Retrofit Kenny\"," +
                "  \"photo\":\"https://avatars2.githubusercontent.com/u/40210777?s=460&v=4\"," +
                "  \"hobby\":\" love Coding because I user Retrofit\"" +
                "}";
    }

    public static String mockJsonUser2() {
        return "{" +
                "  \"name\":\"Okhttp Kenny\"," +
                "  \"photo\":\"https://avatars2.githubusercontent.com/u/40210777?s=460&v=4\"," +
                "  \"hobby\":\"Okhttp sucks\"" +
                "}";
    }
}
