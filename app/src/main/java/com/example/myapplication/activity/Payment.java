package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.PaymentAdapter;
import com.example.myapplication.model.PaymentHistory.Example;
import com.example.myapplication.model.PaymentHistory.Result;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.RecyclerItemClickListener;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity {
    @BindView(R.id.rvPayments) RecyclerView rvPayments;
    @BindView(R.id.txt_amountRevenue) TextView txt_amountRevenue;
    @BindView(R.id.txt_amountExpenditure) TextView txt_amountExpenditure;
    String total = "100,000 VND";
    int totalRevenue = 0;
    int totalExpenditure = 0;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvPayments.setLayoutManager(new LinearLayoutManager(this));
        rvPayments.setItemAnimator(new DefaultItemAnimator());
        getPayments();
    }

    private void getPayments() {
        mApiService.getPaymentHistory().enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if(response.isSuccessful())
                {
                    List<Result> paymentItems = response.body().getResult();
                    for (int i =0;i<paymentItems.size();i++)
                    {
                        if (paymentItems.get(i).getStatus().equals("PAID"))
                        {
                            if (paymentItems.get(i).getSessionRefunded().size() == 0){
                                totalRevenue += paymentItems.get(i).getAmount();
                            }
                            else
                            {
                                totalRevenue +=0;
                            }
                        }
                        else
                        {
                            if (paymentItems.get(i).getSessionRefunded().size() == 0){
                                totalExpenditure += paymentItems.get(i).getAmount();
                            }
                            else
                            {
                                totalExpenditure +=0;
                            }
                        }
                    }
                    rvPayments.setAdapter(new PaymentAdapter(mContext, paymentItems));
                    Log.v("test revenue",Integer.toString(totalRevenue));
                    Log.v("test expenditure", Integer.toString(totalExpenditure));
                    txt_amountExpenditure.setText(totalExpenditure + " VND");
                    txt_amountRevenue.setText(totalRevenue + " VND");
                    rvPayments.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Result items = paymentItems.get(position);
                            String id = items.getId();
                            Intent detailPayment = new Intent(mContext, DetailPayment.class);
                            detailPayment.putExtra(Constants.KEY_PAYMENTID,id);
                            startActivity(detailPayment);
                        }
                    }));
                }
                else{
                    Toast.makeText(mContext, "Failed to load payments", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
