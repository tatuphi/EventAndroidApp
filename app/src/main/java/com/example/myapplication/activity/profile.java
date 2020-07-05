package com.example.myapplication.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.myapplication.R;
import com.example.myapplication.model.BaseUser;
import com.example.myapplication.model.ListEvent.User;
import com.example.myapplication.util.SharedPrefManager;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        getProfileFromSharedPreferences();
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

    private void getProfileFromSharedPreferences(){
        JSONObject objUser = sharedPrefManager.getSPObjectUser();
        try
        {
//            urlImage = objUser.getString("avatar");

            fullname.setText(objUser.getString("fullName"));
            email.setText(objUser.getString("email"));

            String strBirthday = objUser.getString("birthday");
            Log.e("test","birthday" + strBirthday);

            if (strBirthday.equals(""))
            {
                birthday.setText("");
            }
            else
            {
                String dateInString = strBirthday.substring(0,10);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(dateInString);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                birthday.setText(dateFormat.format(date).toString());
            }
            numberphone.setText(objUser.getString("phone"));
            description.setText(objUser.getString("discription"));
            gender.setText(objUser.getString("gender"));
            job.setText(objUser.getString("job"));
            urlImage = objUser.getString("avatar");
            if (urlImage.equals(""))
            {
                Picasso.get().load("https://miro.medium.com/max/720/1*W35QUSvGpcLuxPo3SRTH4w.png").into(image);
            }
            else {
                Picasso.get().load(urlImage).into(image);
            }

//                    Picasso.get().load(urlImage)
//                            .placeholder(R.mipmap.avatar).error(R.drawable.ic_launcher_background)
//                            .into(image);

        }
        catch (JSONException | ParseException e) {
            e.printStackTrace();
        }

    }
}
