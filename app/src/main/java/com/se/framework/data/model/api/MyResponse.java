package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyResponse {
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
    private Data data;

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public static class Data{
        @Expose
        @SerializedName("idString")
        private String idString;

        @Expose
        @SerializedName("name")
        private String name;

        @Expose
        @SerializedName("nickname")
        private String nickname;

        @Expose
        @SerializedName("email")
        private String email;

        @Expose
        @SerializedName("type")
        private String type;

        @Expose
        @SerializedName("phoneNumber")
        private String phoneNumber;

        @Expose
        @SerializedName("studentId")
        private String studentId;

        @Expose
        @SerializedName("informationOpenAgree")
        private String informationOpenAgree;

        @Expose
        @SerializedName("accountId")
        private int accountId;

        public String getIdString() {
            return idString;
        }

        public String getName() {
            return name;
        }

        public String getNickname() {
            return nickname;
        }

        public String getEmail() {
            return email;
        }

        public String getType() {
            return type;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getInformationOpenAgree() {
            return informationOpenAgree;
        }

        public int getAccountId() {
            return accountId;
        }
    }
}
