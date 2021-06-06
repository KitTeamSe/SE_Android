package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WriteResponse {
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("data")
    private String data;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status")
    private int status;

    public String getCode() {
        return code;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
