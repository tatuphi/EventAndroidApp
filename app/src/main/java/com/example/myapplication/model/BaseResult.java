package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class BaseResult {
    @SerializedName("result")
    private Boolean result;
    public Boolean getResult(){
        return result;
    }
    public void setResult(Boolean result){
        this.result = result;
    }
}
