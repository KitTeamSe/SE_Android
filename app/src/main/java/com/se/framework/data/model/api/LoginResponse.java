package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class LoginResponse {
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("data")
    private Data data;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status")
    private int status;

    public String getCode() {
        return code;
    }

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static class Data{
        @Expose
        @SerializedName("token")
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
