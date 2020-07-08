package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.util.ProgressDialog;
import com.example.myapplication.util.SharedPrefManager;
import com.example.myapplication.util.Validate;
import com.example.myapplication.util.api.BaseApiService;
import com.example.myapplication.util.api.UtilsApi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.input_email) EditText input_email;
    @BindView(R.id.input_password) EditText input_password;
    @BindView(R.id.btn_login) Button btn_login;
    @BindView(R.id.txt_forgetPassword) TextView txt_forgetPassword;
    @BindView(R.id.txt_createAccount) TextView txt_createAccount;
    @BindView(R.id.txt_loginGoogle) TextView txt_loginGoogle;

    Context mContext;
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    Validate mValidate;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        sharedPrefManager = new SharedPrefManager(this);
        mValidate = new Validate();
        mProgressDialog = new ProgressDialog();


//        click button
//        button login clicked
        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//               validate all fields and call login api
                if (mValidate.validate(input_email) && mValidate.validate(input_password)){
                    login();
                }
            }
        });

//        textview txt_loginGoogle clicked
        txt_loginGoogle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent loginGg = new Intent(mContext, profile.class);
                startActivity(loginGg);
            }
        });
//        textview txt_createAccount clicked
        txt_createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent createAccount = new Intent(mContext, Register.class);
                startActivity(createAccount);
            }
        });
//        textview txt_forgetPassword clicked
        txt_forgetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent forgetPassword = new Intent(mContext, ForgetPassword.class);
                startActivity(forgetPassword);
            }
        });
//        if (sharedPrefManager.getSPLogin()==true){
//            Intent home = new Intent(mContext, HomeActivity.class);
//            startActivity(home);
//            finish();
//        }
    }

    private void login(){
        mProgressDialog.dismissProgressDialog(mContext);
        mApiService.loginRequest(input_email.getText().toString(), input_password.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                Toast.makeText(mContext, "Logined", Toast.LENGTH_SHORT).show();
                                sharedPrefManager.saveSPObjectUser(SharedPrefManager.SP_OBJUSER, jsonRESULTS.getJSONObject("result"));
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_IDUSER,sharedPrefManager.getSPObjectUser().getString("_id") );
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, false);
                                Log.e("debug", "onFailure: sharepreferences > " + sharedPrefManager.getSPLogin() );
                                Log.e("debug", "onFailure: id is  > " + sharedPrefManager.getSPObjectUser().getString("_id"));

                                startActivity(new Intent(mContext, HomeActivity.class));
                                finish();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                JSONObject jsonError = new JSONObject(response.errorBody().string());
                                Log.e("debug", "onFailure: ERROR 600 > " + jsonError.getJSONObject("error").getString("message") );
                                Toast.makeText(mContext, jsonError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        mProgressDialog.dismissProgressDialog(mContext);
                    }
                });
    }

}
