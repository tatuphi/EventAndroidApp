package com.example.myapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.BaseUser;
import com.example.myapplication.model.ListEvent.User;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUser extends AppCompatActivity {

    @BindView(R.id.txt_fullName)
    TextView fullname;
    @BindView(R.id.txt_email) TextView email;
    @BindView(R.id.txt_birthday) TextView birthday;
    @BindView(R.id.txt_numberPhone) TextView numberphone;
    @BindView(R.id.txt_gender) TextView gender;
    @BindView(R.id.txt_job) TextView job;
    @BindView(R.id.txt_description) TextView description;
    @BindView(R.id.profile_image)
    CircleImageView image;

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

    private void getProfile(){
        mApiService.getuser_profile().enqueue(new Callback<BaseUser>() {
            @Override
            public void onResponse(Call<BaseUser> call, Response<BaseUser> response) {
                if (response.code()==200)
                {
                    User userInfo = response.body().getResult();
                    urlImage = userInfo.getAvatar();
                    fullname.setText(userInfo.getFullName());
                    email.setText(userInfo.getEmail());
                    if (userInfo.getBirthday()==null)
                    {
                        birthday.setText("");
                    }
                    else
                    {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date birthDay = userInfo.getBirthday();
                        birthday.setText(dateFormat.format(birthDay).toString());
                    }
                    numberphone.setText(userInfo.getPhone());
                    description.setText(userInfo.getDiscription());
                    gender.setText(userInfo.getGender());
                    job.setText(userInfo.getJob());

                    Picasso.get().load(urlImage).into(image);
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
