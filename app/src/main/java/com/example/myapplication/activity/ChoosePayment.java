package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoosePayment extends AppCompatActivity {
//    @BindView(R.id.item_zalopay) TextView item_zalopay;
//    @BindView(R.id.item_masterCard) TextView item_masterCard;
    @BindView(R.id.txt_zalopay) TextView txt_zalopay;
    @BindView(R.id.txt_mastercard) TextView txt_mastercard;
    Context mContext;
    BaseApiService mApiService;
    String payType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent myIntent = getIntent();
        String eventId = myIntent.getStringExtra(Constants.KEY_EVENTID);
        String sessionId = myIntent.getStringExtra(Constants.KEY_SESSIONID);
        txt_mastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                verify notification;
                payType = "CREDIT_CARD";
                Intent intent = new Intent(mContext, ListCard.class);
                intent.putExtra(Constants.KEY_EVENTID, eventId);
                intent.putExtra(Constants.KEY_SESSIONID, sessionId);
                intent.putExtra(Constants.KEY_TYPEPAY, payType);
                startActivity(intent);
            }
        });
        txt_zalopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = "ZALO_PAY";
            }
        });
    }
}
