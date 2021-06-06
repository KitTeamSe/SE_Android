package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagResponse {
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("data")
    private Data data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public static class Data{
        @Expose
        @SerializedName("content")
        private List<Tag> content;

        public List<Tag> getContent() {
            return content;
        }

        public static class Tag{
            @Expose
            @SerializedName("tagId")
            private int tagId;

            @Expose
            @SerializedName("text")
            private String text;

            public int getTagId() {
                return tagId;
            }

            public String getText() {
                return text;
            }
        }
    }
}
