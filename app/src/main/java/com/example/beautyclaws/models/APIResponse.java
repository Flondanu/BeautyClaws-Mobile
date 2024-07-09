package com.example.beautyclaws.models;

import com.google.gson.annotations.SerializedName;

public class APIResponse {



    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("statusDescription")
    private String statusDescription;
    @SerializedName("createdResourceId")
    private long createdResourceId;


    public APIResponse(int statusCode, String statusDescription, long createdResourceId) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.createdResourceId = createdResourceId;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public long getCreatedResourceId() {
        return createdResourceId;
    }

    public void setCreatedResourceId(long createdResourceId) {
        this.createdResourceId = createdResourceId;
    }
}
