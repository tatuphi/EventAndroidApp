package com.example.myapplication.util.api;

public class UtilsApi {
//    172.16.3.198:5000

    public static String BASE_URL_API = "https://event-orgnization.herokuapp.com/api/";
    //  Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
