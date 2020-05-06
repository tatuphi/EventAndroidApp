package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
    public void create_account(View v)
    {
        Intent createAccount = new Intent(MainActivity.this, Register.class);
        startActivity(createAccount);
    }
    public void login(View v)
    {
        Intent login = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(login);
    }
    public void forget_password(View v)
    {
        Intent forgetPassword = new Intent(MainActivity.this, ForgetPassword.class);
        startActivity(forgetPassword);
    }
    public void login_with_google(View v)
    {
        Intent loginGg = new Intent(MainActivity.this, profile.class);
        startActivity(loginGg);
    }

}
