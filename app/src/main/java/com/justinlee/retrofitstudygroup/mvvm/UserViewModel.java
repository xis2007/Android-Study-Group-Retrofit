package com.justinlee.retrofitstudygroup.mvvm;

import android.util.Log;

import com.justinlee.retrofitstudygroup.objects.User;
import com.justinlee.retrofitstudygroup.retrofit.UserSignInRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private Repository mRepository;
    private MutableLiveData<User> mUser;

    public UserViewModel() {
        Log.d(TAG, "UserViewModel: repository created");
        mRepository = new Repository();
    }

    public MutableLiveData<User> getUser() {
        if (mUser == null) {
            mUser = new MutableLiveData<>();
        }
        return mUser;
    }

    public void loginWithOkhttp(String id, String pw) {
        mRepository.loginWithOkhttp(this, id, pw);
    }

    public void loginWithRetrofit(String id, String pw) {
        mRepository.loginWithRetrofit(this, new UserSignInRequest(id, pw));
    }

    public void setContextToRepo(AppCompatActivity context) {
        mRepository.setContext(context);
    }
}
