package com.justinlee.retrofitstudygroup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.justinlee.retrofitstudygroup.mvvm.UserViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.button_login_okhttp) Button mLoginButtonOkhttp;
    @BindView(R.id.button_login_retrofit) Button mLoginButtonRetrofit;

    private UserViewModel mUserViewModel;
    private String USER_ID = "abc@gmail.com";
    private String USER_PW = "abcpassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.setContextToRepo(this);
        mUserViewModel.getUser().observe(this, user -> {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            intent.putExtra("USER_INFO", user.toJson());
            startActivity(intent);
        });
    }

    @OnClick(R.id.button_login_okhttp)
    public void onOkhttpButtonClicked() {
        mUserViewModel.loginWithOkhttp(USER_ID, USER_PW);
    }

    @OnClick(R.id.button_login_retrofit)
    public void onRetrofitButtonClicked() {
        mUserViewModel.loginWithRetrofit(USER_ID, USER_PW);
    }
}
