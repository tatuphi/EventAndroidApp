package com.example.myapplication.util;

import android.content.Context;

import com.example.myapplication.activity.Login;

public class ProgressDialog {
    public void setProgressDialog(Context mContext){
        final android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(mContext);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
    }
    public void dismissProgressDialog(Context mContext){
        final android.app.ProgressDialog progressDialog = new android.app.ProgressDialog(mContext);
        progressDialog.dismiss();
    }

}
