package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListApplyUser extends AppCompatActivity {
    @BindView(R.id.rvListApplyUser)
    RecyclerView rvListApplyUser;
    Context mContext;
    BaseApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_apply_user);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

    }
}
