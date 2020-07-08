package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ApplyUserAdapter;
import com.example.myapplication.model.UserJoinEvent.Example;
import com.example.myapplication.model.UserJoinEvent.Result;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListApplyUser extends AppCompatActivity {
    @BindView(R.id.rvListApplyUser) RecyclerView rvListApplyUser;
    String eventId, sessionId;
    Context mContext;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apply_user);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent myIntent = getIntent();
        eventId = myIntent.getStringExtra(Constants.KEY_EVENTID);
        sessionId = myIntent.getStringExtra(Constants.KEY_SESSIONID);

        rvListApplyUser.setLayoutManager(new LinearLayoutManager(this));
        rvListApplyUser.setItemAnimator(new DefaultItemAnimator());

        getListApplyUser();
    }
    private void getListApplyUser(){
        mApiService.get_user_join_event(eventId,sessionId, 1).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.isSuccessful()){
                    List<Result> listApplyUser = response.body().getResult();
                    rvListApplyUser.setAdapter(new ApplyUserAdapter(mContext, listApplyUser));
                }
                else
                {

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
