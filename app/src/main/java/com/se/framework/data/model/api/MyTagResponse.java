package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyTagResponse {
    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Data> data;

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Data> getData(){
        return data;
    }

    public static class Data{
        @Expose
        @SerializedName("accountId")
        private int accountId;

        @Expose
        @SerializedName("accountIdString")
        private String accountIdString;

        @Expose
        @SerializedName("tagId")
        private int tagId;

        @Expose
        @SerializedName("tagListeningId")
        private int tagListeningId;

        @Expose
        @SerializedName("tagName")
        private String tagName;

        public int getAccountId() {
            return accountId;
        }

        public String getAccountIdString() {
            return accountIdString;
        }

        public int getTagId() {
            return tagId;
        }

        public int getTagListeningId() {
            return tagListeningId;
        }

        public String getTagName() {
            return tagName;
        }
    }
}
