package com.justinlee.retrofitstudygroup;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justinlee.retrofitstudygroup.objects.User;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.photoImage) ImageView mPhotoImage;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.hobby) TextView mHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        User user = User.fromJson(getIntent().getStringExtra("USER_INFO"));
        Glide.with(this).load(user.getPhoto()).into(mPhotoImage);
        mName.setText(user.getName());
        mHobby.setText(user.getHobby());
    }

}
