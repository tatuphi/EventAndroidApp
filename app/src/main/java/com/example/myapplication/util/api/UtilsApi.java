package com.example.myapplication.util.api;

public class UtilsApi {

    public static String BASE_URL_API = "http:/192.168.1.2:5000/api/";
    //  Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
