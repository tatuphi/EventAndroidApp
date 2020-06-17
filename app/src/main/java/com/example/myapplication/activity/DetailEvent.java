package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.DetailEvent.Event;
import com.example.myapplication.model.DetailEvent.Example;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEvent extends AppCompatActivity {
    @BindView(R.id.img_banner) ImageView img_banner;
    @BindView(R.id.txt_name) TextView txt_name;
    @BindView(R.id.txt_status) TextView txt_status;
    @BindView(R.id.txt_comment) TextView txt_comment;
    String evenId;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

//        get Intent from tabs
        Intent intent = getIntent();
        evenId = intent.getStringExtra(Constants.KEY_ID);
        getDetailEvent();
    }
    private void getDetailEvent(){
        mApiService.get_event_inf(evenId).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    Event event = response.body().getResult().getEvent();
                    Picasso.get().load(event.getBannerUrl()).into(img_banner);
                    txt_name.setText(event.getName());
                    txt_status.setText(event.getStatus());
                    txt_comment.setText(response.body().getResult().getCountComment().toString());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
