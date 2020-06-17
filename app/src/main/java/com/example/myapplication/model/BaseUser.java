package com.example.myapplication.model;

import com.example.myapplication.model.ListEvent.User;
import com.google.gson.annotations.SerializedName;

public class BaseUser {
    @SerializedName("result")
    private User result;

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}
