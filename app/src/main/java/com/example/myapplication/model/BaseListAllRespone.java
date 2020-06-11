package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseListAllRespone {
    @SerializedName("result")
    private List<ListAllEventResponse> result;

    public List<ListAllEventResponse> getResult() {
        return result;
    }

    public void setResult(List<ListAllEventResponse> result) {
        this.result = result;
    }
}
