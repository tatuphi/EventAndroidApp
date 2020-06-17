package com.example.myapplication.model.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LinkTo {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("_id")
    @Expose
    private String id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
