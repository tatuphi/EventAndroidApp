package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class EventCategoryResponse {
    @SerializedName("name")
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
