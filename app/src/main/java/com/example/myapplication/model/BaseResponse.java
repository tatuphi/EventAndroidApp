package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("result")
    private UserResponse result;

    public UserResponse getResult() {
        return result;
    }

    public void setResult(UserResponse result) {
        this.result = result;
    }
}
