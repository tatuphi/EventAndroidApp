package com.example.myapplication.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.model.BaseUser;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profile extends AppCompatActivity {

    @BindView(R.id.txt_fullName) TextView fullname;
    @BindView(R.id.txt_email) TextView email;
    @BindView(R.id.txt_birthday) TextView birthday;
    @BindView(R.id.txt_numberPhone) TextView numberphone;
    @BindView(R.id.txt_gender) TextView gender;
    @BindView(R.id.txt_job) TextView job;
    @BindView(R.id.txt_description) TextView description;
    @BindView(R.id.profile_image) CircleImageView image;

    String urlImage;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        getProfile();
    }
    public void edit_profile(View v)
    {
        Intent editProfile = new Intent(profile.this, EditProfile.class);
        startActivity(editProfile);
    }

    private void getProfile(){
        mApiService.getuser_profile().enqueue(new Callback<BaseUser>() {
            @Override
            public void onResponse(Call<BaseUser> call, Response<BaseUser> response) {
                if (response.code()==200)
                {
                    urlImage = response.body().getResult().getAvatar();
                    fullname.setText(response.body().getResult().getFullName());
                    email.setText(response.body().getResult().getEmail());
//                    birthday.setText(response.body().getResult().getBirthday().toString());
//                    numberphone.setText(response.body().getResult().getPhone());
//                    description.setText(response.body().getResult().getDescription());
//                    gender.setText(response.body().getResult().getGender());
//                    job.setText(response.body().getResult().getJob());

                    Picasso.get().load(urlImage).into(image);
//                    Picasso.get().load(urlImage)
//                            .placeholder(R.mipmap.avatar).error(R.drawable.ic_launcher_background)
//                            .into(image);
                }
                else
                {
                    try {
                        JSONObject jsonError = new JSONObject(response.errorBody().string());
                        Log.e("debug", "onFailure: ERROR 600 > " + jsonError.getJSONObject("error").getString("message") );
                        Toast.makeText(mContext, jsonError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(mContext, MainActivity.class));
                    } catch (Exception e) {
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseUser> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());

            }
        });
    }
}
