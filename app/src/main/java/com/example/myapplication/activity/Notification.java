package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.NotificationAdapter;
import com.example.myapplication.util.api.BaseApiService;

import butterknife.BindView;

public class Notification extends AppCompatActivity {
    @BindView(R.id.rvListNotification) RecyclerView rvListNotification;
    BaseApiService mApiService;
    NotificationAdapter notificationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_notification);
    }
}
