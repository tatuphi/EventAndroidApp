package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.util.Constants;

public class DetailPayment extends AppCompatActivity {
    Context mContext;
    String idPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment);
        Intent myIntent = getIntent();
        idPayment = myIntent.getStringExtra(Constants.KEY_PAYMENTID);
    }
}
