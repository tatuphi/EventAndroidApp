package com.example.myapplication.util.api;


import com.example.myapplication.model.BaseResult;
import com.example.myapplication.model.BaseUser;
import com.example.myapplication.model.ListEvent.Example;
import com.example.myapplication.model.Notification.BadgeNumber;

import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//note: example class in different folder will be different
public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(    @Field("email") String email,
                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest( @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("fullName") String fullName);

    @FormUrlEncoded
    @POST("login-google")
    Call<ResponseBody> login_googleRequest(@Field("email") String email);

    @FormUrlEncoded
    @POST("requestForgotPassword")
    Call<ResponseBody> requestForgotPasswordRequest(@Field("email") String email);

    @FormUrlEncoded
    @POST("verifyForgotPassword")
    Call<ResponseBody> verifyForgotPasswordRequest(@Field("email") String email);
//    ???? otp sent by email

    @FormUrlEncoded
    @POST("forgotPassword")
    Call<ResponseBody> forgotPasswordRequest(@Field("email") String email,
                                             @Field("otp") String otp,
                                             @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @POST("updatePassword")
    Call<ResponseBody> updatePasswordRequest(@Field("oldpassword") String oldpassword,
                                             @Field("newpassword") String newpassword);

    @FormUrlEncoded
    @POST("user/updateInfo")
    Call<ResponseBody> updateInfoRequest(   @Field("fullName") String fullName,
                                            @Field("birthday") Date birthday,
                                            @Field("gender") String gender,
                                            @Field("job") String job,
                                            @Field("phone") String phone,
                                            @Field("discription") String discription,
                                            @Field("avatarUrl") String avatarUrl,
                                            @Field("orgName") String orgName,
                                            @Field("orgDes") String orgDes,
                                            @Field("orgWeb") String orgWeb,
                                            @Field("orgPhone") String orgPhone,
                                            @Field("orgEmail") String orgEmail,
                                            @Field("orgUrl") String orgUrl,
                                            @Field("address") String address );


//    get method

    @GET("current_user")
    Call<BaseUser> getuser_profile();
//    get history
    @GET("user/history")
    Call<BaseUser> get_History();
//    get logout
    @GET("logout")
    Call<BaseUser> getlogout();
//    get verify code/token after registering
    @GET("verifyToken")
    Call<BaseResult> getverifyToken(@Query("token") String token);
//    get list all event participate
    @GET("user/get_history_take_part_in")
    Call<Example> getHistoryTakePartIn (@Query("categoryEventId") String categoryEventId,
                                                    @Query("startDate") Date startDate,
                                                    @Query("endDate") Date endDate,
                                                    @Query("txtSearch") String txtSearch,
                                                    @Query("pageNumber") Number pageNumber,
                                                    @Query("numberRecord") Number numberRecord,
                                                    @Query("type") String type);
    @GET("user/get_history_take_part_in")
    Call<Example> getHistoryParticipate (@Query("type") String type);
//    get list event "self"
    @GET("user/historyCreate")
    Call<Example> get_HistoryCreate();
    @GET("user/historyCreate")
    Call<Example> getHistoryCreate (@Query("categoryEventId") String categoryEventId,
                                               @Query("startDate") Date startDate,
                                               @Query("endDate") Date endDate,
                                               @Query("txtSearch") String txtSearch,
                                               @Query("pageNumber") Number pageNumber,
                                               @Query("numberRecord") Number numberRecord,
                                               @Query("status") String status);
    @GET("get_event_inf")
    Call<com.example.myapplication.model.DetailEvent.Example> get_event_inf(
            @Query("eventId") String eventId);

//    get notification number
    @GET("getBadgeNumber")
    Call<BadgeNumber> getBadgeNumber();
//    get list notification
    @GET("getListNotification")
    Call<com.example.myapplication.model.Notification.Example> getListNotification();

//    setReadNotification
    @POST("setReadNotification")
    Call<ResponseBody> setReadNotification(@Field("notificationId") String notificationId);
//    setDeleteNotification
    @POST("setDeleteNotification")
    Call<ResponseBody> setDeleteNotification (@Field("notificationId") String notificationId);
}
