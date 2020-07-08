package com.example.myapplication.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;


public class SharedPrefManager{


    public static final String SP_APP = "spApp";
    public static final String SP_LOGIN = "spLogin";
    public static final String SP_OBJUSER = "spObjUser";
    public static final String SP_IDUSER = "spIdUser";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public void saveSPObjectUser(String keySP, JSONObject userObject){
        Gson gson = new Gson();
        String json = gson.toJson(userObject);
        spEditor.putString(keySP, json);
        spEditor.commit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public Boolean getSPLogin(){
        return sp.getBoolean(SP_LOGIN, false);
    }

    public JSONObject getSPObjectUser(){
        Gson gson = new Gson();
        String json = sp.getString(SP_OBJUSER, "");
        JSONObject obj = gson.fromJson(json, JSONObject.class);
        return  obj;
    }
    public String getSpIduser(){
        return sp.getString(SP_IDUSER, "");
    }
    public void logout(){
        spEditor.clear();
        spEditor.commit();
    }


}
