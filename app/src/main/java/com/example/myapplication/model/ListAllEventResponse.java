package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ListAllEventResponse {
    @SerializedName("_id")
    private String _id;
    @SerializedName("status")
    private String status;
    @SerializedName("typeOfEvent")
    private String typeOfEvent;
    @SerializedName("name")
    private String name;
    @SerializedName("urlWeb")
    private String urlWeb;
    @SerializedName("isSellTicket")
    private Boolean isSellTicket;
    @SerializedName("bannerUrl")
    private String bannerUrl;
    @SerializedName("eventCategory")
    private EventCategoryResponse eventCategory;
    @SerializedName("user")
    private UserResponse user;

    public UserResponse getUser(){ return  user;}
    public void setUser(UserResponse user){
        this.user = user;
    }
    public EventCategoryResponse getEventCategory(){ return eventCategory;}
    public void setEventCategory(EventCategoryResponse eventCategory){
        this.eventCategory = eventCategory;
    }
    public String getBannerUrl(){ return  bannerUrl;}
    public void setBannerUrl(String bannerUrl){this.bannerUrl = bannerUrl;}
    public Boolean getIsSellTicket(){ return isSellTicket;}
    public void setIsSellTicket(Boolean isSellTicket){this.isSellTicket = isSellTicket;}
    public String getUrlWeb(){ return  urlWeb;}
    public void setUrlWeb(String urlWeb){ this.urlWeb = urlWeb;}
    public String getName(){ return  name;}
    public void setName(String name){this.name = name;}
    public String getTypeOfEvent(){
        return typeOfEvent;
    }
    public void setTypeOfEvent(String typeOfEvent){
        this.typeOfEvent = typeOfEvent;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getId(){
        return _id;
    }
    public void setId(String _id){
        this._id = _id;
    }
}
