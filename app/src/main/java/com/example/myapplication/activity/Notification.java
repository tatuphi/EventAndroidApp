package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NotificationAdapter;
import com.example.myapplication.model.Notification.Example;
import com.example.myapplication.model.Notification.Result;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends AppCompatActivity {
    @BindView(R.id.rvListNotification) RecyclerView rvListNotification;
    Context mContext;
    BaseApiService mApiService;
//    NotificationAdapter notificationAdapter;
//    List<Result> notificationItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_notification);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();


//        notificationAdapter = new NotificationAdapter(this, notificationItemList);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvListNotification.setLayoutManager(new LinearLayoutManager(this));
        rvListNotification.setItemAnimator(new DefaultItemAnimator());
        getNotification();
    }
    private void getNotification(){
        mApiService.getListNotification().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    final List<Result> notificationItems = response.body().getResult();
                    rvListNotification.setAdapter(new NotificationAdapter(mContext, notificationItems));
//                    notificationAdapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(mContext, "Failed to load notifications", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
