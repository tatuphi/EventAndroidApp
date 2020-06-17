package com.example.myapplication.util.api;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                // .addHeader(Constant.Header, authToken)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .cookieJar(new SessionCookieJar())
                .build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    private static class SessionCookieJar implements CookieJar {
        private List<Cookie> cookies;
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

            if (url.encodedPath().endsWith("login") || url.encodedPath().endsWith("register")) {
                this.cookies = new ArrayList<>(cookies);
                this.cookies.addAll(cookies);
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            if (!(url.encodedPath().endsWith("login") || url.encodedPath().endsWith("register")
        || url.encodedPath().endsWith("login-google")|| url.encodedPath().endsWith("requestForgotPassword")
            || url.encodedPath().endsWith("verifyForgotPassword")
        || url.encodedPath().endsWith("forgotPassword")) && cookies != null)
            {
                return cookies;
            }
            return Collections.emptyList();
        }
    }
}
