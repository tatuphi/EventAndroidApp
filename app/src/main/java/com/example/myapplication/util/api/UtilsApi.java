package com.example.myapplication.util.api;

public class UtilsApi {
//    http://localhost:5000
//    172.16.3.123:5000
//  192.168.1.2  changeable


    public static String BASE_URL_API = "http:/172.16.0.49:5000/api/";
    //  Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
