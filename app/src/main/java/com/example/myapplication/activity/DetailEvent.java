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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
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
    @BindView(R.id.txt_address1) TextView txt_address;
    @BindView(R.id.roomMap) ImageView roomMap;
//    recyclerview
    @BindView(R.id.rvSchedules) RecyclerView rvSchedules;
    @BindView(R.id.rvFiles) RecyclerView rvFiles;
    @BindView(R.id.btn_applyEvent) Button btn_applyEvent;
    @BindView(R.id.btn_cancelEvent) Button btn_cancelEvent;
    @BindView(R.id.mapHere) TextView mapHere;
    @BindView(R.id.btn_returnListUser) TextView btn_returnListUser;

    @BindView(R.id.btn_back) TextView btn_back;
    @BindView(R.id.btn_scanQr) TextView btn_scanQr;

//    item in recyclerview

//    SupportMapFragment mapFrag;
//    private GoogleMap mMap;
    Session sessionItem;
    int joinNumber = 0;
    Event event;
    String eventId, typeTab,statusSession;
    Boolean isCancelSession = false;

//    List<String> arrSessionIds;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
//        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFrag.getMapAsync((OnMapReadyCallback) mContext);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService(mContext);


//        get Intent from tabs
        Intent intent = getIntent();
        eventId = intent.getStringExtra(Constants.KEY_ID);
        typeTab = intent.getStringExtra(Constants.KEY_STATUS);
//        adapter
        rvListDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListDate.setItemAnimator(new DefaultItemAnimator());
//        set up maps

//        adapter list time
        rvSchedules.setLayoutManager(new LinearLayoutManager(this));
        rvSchedules.setItemAnimator(new DefaultItemAnimator());
//        adapter list file
        rvFiles.setLayoutManager(new LinearLayoutManager(this));
        rvFiles.setItemAnimator(new DefaultItemAnimator());
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getDetailEvent();
    }
    private void getDetailEvent(){
        mApiService.get_event_inf(eventId).enqueue(new Callback<Example>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    event = response.body().getResult().getEvent();
                    Picasso.get().load(event.getBannerUrl()).into(img_banner);
                    txt_name.setText(event.getName());
                    txt_status.setText(event.getStatus());
                    txt_comment.setText(response.body().getResult().getCountComment().toString() + " comments");
//                    adapter session event
                    final List<Session> dateItems = event.getSession();
//                    rvListDate.setAdapter(new SessionEvent(mContext, dateItems));
                    getDetailSession(dateItems.get(0));
                    rvListDate.setAdapter(new SessionEvent(mContext, dateItems, new SessionEvent.MyAdapterListener() {
                        @Override
                        public void itemListSessionDateOnClick(View v, int position) {
                            sessionItem = dateItems.get(position);
                            getDetailSession(sessionItem);
                        }
                    }));

                    txt_comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent cmtScreen = new Intent(mContext, Comments.class);
                            cmtScreen.putExtra(Constants.KEY_EVENTID, event.getId());
                            cmtScreen.putExtra(Constants.KEY_EVENTNAME, event.getName());
                            startActivity(cmtScreen);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {
            }
        });
    }
    private void getDetailSession(Session sessionItem){
//                            each session
            if (sessionItem.getJoinNumber()==null){
                txt_joinNumber.setVisibility(View.GONE);
            }
            else{
                joinNumber = sessionItem.getJoinNumber();
                txt_joinNumber.setText(sessionItem.getJoinNumber().toString() + " people particated");
            }
            if(sessionItem.getAddress()!=null){
                if (sessionItem.getAddress().getLocation()!=null)
                {
                    txt_address.setText(sessionItem.getAddress().getLocation());
                }
                else{
                    txt_address.setVisibility(View.GONE);
                }
                if (sessionItem.getAddress().getDetailImage()!=null){
                    roomMap.setVisibility(View.VISIBLE);
                    Picasso.get().load(sessionItem.getAddress().getDetailImage()).into(roomMap);
                }
                else{
                    roomMap.setVisibility(View.GONE);
                }
            }
            else {
                txt_address.setVisibility(View.GONE);
                roomMap.setVisibility(View.GONE);
            }

//                            if (sessionItem.getAddress().getMap().getLat()==null || sessionItem.getAddress().getMap().getLng()==null){
//                                mapHere.setVisibility(View.GONE);
//                            }
//                            else
//                            {
//                                Intent mapIntent = new Intent(mContext, MapsActivity.class);
//                                mapIntent.putExtra(Constants.KEY_LAT, sessionItem.getAddress().getMap().getLat());
//                                mapIntent.putExtra(Constants.KEY_LNG, sessionItem.getAddress().getMap().getLng());
//                                startActivity(mapIntent);
//                            }
            List<Detail> detailItems = sessionItem.getDetail();
            rvSchedules.setAdapter(new TimeAdapter(mContext,detailItems));
            List<Document> documentItems = sessionItem.getDocuments();
            rvFiles.setAdapter(new FileAdapter(mContext, documentItems));

            String statusEvent = event.getStatus();
//                            fields in a session
            String sessionId = sessionItem.getIdSession();

            if (sessionItem.getStatus()==null)
            {
                statusSession = "CANJOIN";
            }
            else
            {
                statusSession = sessionItem.getStatus();
            }
            if (sessionItem.getIsCancel()==null)
            {
                statusSession = "CANJOIN";
            }
            else
            {
                if (sessionItem.getIsCancel())
                {
                    isCancelSession = true;
                }
                else {
                    isCancelSession =false;
                }
            }
//                            Boolean isCancelSession = sessionItem.getIsCancel();
            String[] arrIdSessions = {sessionId};
//                  handle button apply event && cancel event
            btn_applyEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent choosePaymentScreen = new Intent(mContext, ChoosePayment.class);
                    choosePaymentScreen.putExtra(Constants.KEY_EVENTID, event.getId());
                    choosePaymentScreen.putExtra(Constants.KEY_SESSIONID, sessionId);
                    startActivity(choosePaymentScreen);
                }
            });

            btn_cancelEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                                    show verify notification to cancel session
                    mApiService.cancelEvent(event.getId(), arrIdSessions).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful())
                            {
                                Toast.makeText(mContext,"Canceled", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try {
                                    JSONObject jsonError = new JSONObject(response.errorBody().string());
                                    Toast.makeText(mContext, jsonError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();

                                } catch (Exception e) {
                                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });
//                            condition to set visible button
            if (typeTab.equals("RECENT"))
            {

                if( isCancelSession || !statusEvent.equals("PUBLIC"))
                {
                    btn_cancelEvent.setVisibility(View.GONE);
                    btn_applyEvent.setVisibility(View.GONE);
                }
                else if(statusSession.equals("JOINED")){
                    btn_cancelEvent.setVisibility(View.VISIBLE);
                    btn_applyEvent.setVisibility(View.GONE);

                }
                else {
                    btn_applyEvent.setVisibility(View.VISIBLE);
                    btn_cancelEvent.setVisibility(View.GONE);
                }
            }
            else {
                // not visible button
                btn_cancelEvent.setVisibility(View.GONE);
                btn_applyEvent.setVisibility(View.GONE);
                if(typeTab.equals("SELF") && joinNumber>0 )
                {
                    btn_returnListUser.setVisibility(View.VISIBLE);
                    btn_returnListUser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, ListApplyUser.class);
                            intent.putExtra(Constants.KEY_SESSIONID, sessionItem.getIdSession());
                            intent.putExtra(Constants.KEY_EVENTID, eventId);
                            startActivity(intent);
                        }
                    });
                }
            }

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(sessionItem.getAddress().getMap().getLat(), sessionItem.getAddress().getMap().getLng());
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in my event"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
}
