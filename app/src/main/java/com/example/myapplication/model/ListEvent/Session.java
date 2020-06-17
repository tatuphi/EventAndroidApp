package com.example.myapplication.model.ListEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Session {
    @SerializedName("joinNumber")
    @Expose
    private Integer joinNumber;
    @SerializedName("documents")
    @Expose
    private List<Document> documents = null;
    @SerializedName("detail")
    @Expose
    private List<Detail> detail = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("limitNumber")
    @Expose
    private Integer limitNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("isCancel")
    @Expose
    private Boolean isCancel;

    public Integer getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(Integer joinNumber) {
        this.joinNumber = joinNumber;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Boolean isCancel) {
        this.isCancel = isCancel;
    }

}
