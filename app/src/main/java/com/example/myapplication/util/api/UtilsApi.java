package com.example.myapplication.util.api;

public class UtilsApi {
    public static String BASE_URL_API = "http:/172.16.3.168:5000/api/";
    //  Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
