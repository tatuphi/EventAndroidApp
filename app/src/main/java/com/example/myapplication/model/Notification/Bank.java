package com.example.myapplication.model.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bank {
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("bankNumber")
    @Expose
    private String bankNumber;
    @SerializedName("accountOwner")
    @Expose
    private String accountOwner;
    @SerializedName("bankBranch")
    @Expose
    private String bankBranch;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}
