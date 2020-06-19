package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FileAdapter;
import com.example.myapplication.adapter.SessionEvent;
import com.example.myapplication.adapter.TimeAdapter;
import com.example.myapplication.model.DetailEvent.Event;
import com.example.myapplication.model.DetailEvent.Example;
import com.example.myapplication.model.ListEvent.Detail;
import com.example.myapplication.model.ListEvent.Document;
import com.example.myapplication.model.ListEvent.Session;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.RecyclerItemClickListener;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

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
    @BindView(R.id.rvListDate) RecyclerView rvListDate;
//    bindview session detail
    @BindView(R.id.txt_joinNumber) TextView txt_joinNumber;
    @BindView(R.id.txt_address) TextView txt_address;
//    @BindView(R.id.googleMap) ImageView googleMap;
//    @BindView(R.id.roomMap) ImageView roomMap;
//    recyclerview
    @BindView(R.id.rvSchedules) RecyclerView rvSchedules;
    @BindView(R.id.rvFiles) RecyclerView rvFiles;
//    item in recyclerview

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
//        adapter
        rvListDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListDate.setItemAnimator(new DefaultItemAnimator());
//        adapter list time
        rvSchedules.setLayoutManager(new LinearLayoutManager(this));
        rvSchedules.setItemAnimator(new DefaultItemAnimator());
//        adapter list file
        rvFiles.setLayoutManager(new LinearLayoutManager(this));
        rvFiles.setItemAnimator(new DefaultItemAnimator());

        getDetailEvent();
    }
    private void getDetailEvent(){
        mApiService.get_event_inf(evenId).enqueue(new Callback<Example>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    Event event = response.body().getResult().getEvent();
                    Picasso.get().load(event.getBannerUrl()).into(img_banner);
                    txt_name.setText(event.getName());
                    txt_status.setText(event.getStatus());
                    txt_comment.setText(response.body().getResult().getCountComment().toString() + " comments");
//                    adapter session event
                    final List<Session> dateItems = event.getSession();
                    rvListDate.setAdapter(new SessionEvent(mContext, dateItems));
//                    default first session infor
                    txt_joinNumber.setText(dateItems.get(0).getJoinNumber().toString() + " people particated");
                    txt_address.setText(dateItems.get(0).getAddress().getLocation());
                    rvSchedules.setAdapter(new TimeAdapter(mContext,dateItems.get(0).getDetail()));
                    rvFiles.setAdapter(new FileAdapter(mContext, dateItems.get(0).getDocuments()));
//                    onlick item session date
                    rvListDate.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Session sessionItem = dateItems.get(position);
                            txt_joinNumber.setText(sessionItem.getJoinNumber().toString() + " people particated");
                            txt_address.setText(sessionItem.getAddress().getLocation());
                            List<Detail> detailItems = sessionItem.getDetail();
                            rvSchedules.setAdapter(new TimeAdapter(mContext,detailItems));
                            List<Document> documentItems = sessionItem.getDocuments();
                            rvFiles.setAdapter(new FileAdapter(mContext, documentItems));
                        }
                    }));
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });
    }
}
