package com.justinlee.retrofitstudygroup.mvvm;

import android.util.Log;

import com.justinlee.retrofitstudygroup.objects.User;
import com.justinlee.retrofitstudygroup.retrofit.ApiService;
import com.justinlee.retrofitstudygroup.retrofit.UserSignInRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String TAG = "Repository";

    private AppCompatActivity mContext;

    private OkHttpClient mClient;
    private ApiService mApiService;

    public Repository() {
        Log.d(TAG, "Repository: created");
        // init Okhttp client
        mClient = new OkHttpClient();

        //init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.testing.login.com/")
                .build();
        mApiService = retrofit.create(ApiService.class);

    }

    public void loginWithOkhttp(UserViewModel userViewModel, String id, String pw) {
        Log.d(TAG, "loginWithOkhttp: called");

        String jsonStr = "{\"id\":" + id + ",\"password\":" + pw + "}";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonStr);

        Request request = new Request.Builder()
                .url("https://www.google.com")
                .header("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Basic abcdefghijklmnopqrstuvwxyz")
                .post(body)
                .build();

        mClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                Log.d(TAG, "onResponse: ");
//                String body = response.body().string();
                String body = User.mockJsonUser2();
                try {
                    JSONObject jsonObject = new JSONObject(body);

                    User user = new User();
                    user.setName(jsonObject.getString("name"));
                    user.setPhoto(jsonObject.getString("photo"));
                    user.setHobby(jsonObject.getString("hobby"));

                    mContext.runOnUiThread(() -> {
                        userViewModel.getUser().setValue(user);
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.d(TAG, "onFailure: ");
                String body = User.mockJsonUser2();
                try {
                    JSONObject jsonObject = new JSONObject(body);

                    User user = new User();
                    user.setName(jsonObject.getString("name"));
                    user.setPhoto(jsonObject.getString("photo"));
                    user.setHobby(jsonObject.getString("hobby"));

                    mContext.runOnUiThread(() -> {
                        userViewModel.getUser().setValue(user);
                    });
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
            }
        });
    }

    public void loginWithRetrofit(UserViewModel userViewModel, UserSignInRequest request) {
        Log.d(TAG, "loginWithRetrofit: called");
        mApiService.login(request).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                userViewModel.getUser().setValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                User user = User.fromJson(User.mockJsonUser());
                userViewModel.getUser().setValue(user);
            }
        });
    }

    public void setContext(AppCompatActivity context) {
        mContext = context;
    }
}
