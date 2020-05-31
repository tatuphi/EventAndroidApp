package com.example.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class UserResponse {
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("isReported")
    private Boolean isReported;
    @SerializedName("isActive")
    private Boolean isActive;
    @SerializedName("_id")
    private Object _id;
    @SerializedName("email")
    private String email;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("job")
    private String job;
    @SerializedName("address")
    private String address;
    @SerializedName("discription")
    private String discription;
    @SerializedName("phone")
    private String phone;
    @SerializedName("gender")
    private String gender;
    @SerializedName("birthday")
    private Date birthday;

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
    public String getJob(){
        return job;
    }
    public void setJob(String job){
        this.job = job;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getDescription(){
        return discription;
    }
    public void setDescription(String discription){
        this.discription = discription;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }

    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }


}
