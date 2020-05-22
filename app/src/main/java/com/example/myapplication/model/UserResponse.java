package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserResponse {
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("isReported")
    private Boolean isReported;
    @SerializedName("isActive")
    private Boolean isActive;
    @SerializedName("createAt")
    private Date createAt;
    @SerializedName("_id")
    private Object _id;
    @SerializedName("email")
    private String email;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("dateCreate")
    private Date dateCreate;

    public String getAvatar(){
        return avatar;
    }
    public void setAvatar(String avatar){
        this.avatar = avatar;
    }
    public Boolean getIsReported(){
        return isReported;
    }
    public void setIsReported(Boolean isReported){
        this.isReported = isReported;
    }
    public Boolean getIsActive(){
        return isActive;
    }
    public void setIsActive(Boolean isActive){
        this.isActive = isActive;
    }
    public Date getCreateAt(){
        return createAt;
    }
    public void setCreateAt(Date createAt)
    {
        this.createAt = createAt;
    }
    public Object get_id(){
        return _id;
    }
    public void set_id(Object _id){
        this._id = _id;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public Date getDateCreate(){
        return dateCreate;
    }
    public void setDateCreate(Date createAt)
    {
        this.dateCreate = dateCreate;
    }

}
