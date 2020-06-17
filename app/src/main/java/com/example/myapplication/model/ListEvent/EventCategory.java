package com.example.myapplication.model.ListEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventCategory {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isDelete")
    @Expose
    private Boolean isDelete;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
