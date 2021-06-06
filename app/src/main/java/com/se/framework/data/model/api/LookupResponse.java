package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class LookupResponse {
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        @Expose
        @SerializedName("postId")
        private int postid;

        @Expose
        @SerializedName("boardId")
        private int boardId;

        @Expose
        @SerializedName("boardNameEng")
        private String boardNameEng;

        @Expose
        @SerializedName("views")
        private int views;

        @Expose
        @SerializedName("isSecret")
        private String isSecret;

        @Expose
        @SerializedName("isNotice")
        private String isNotice;

        @Expose
        @SerializedName("nickname")
        private String name;

        @Expose
        @SerializedName("postContent")
        private postContent postContent;

        public static class postContent{
            @Expose
            @SerializedName("title")
            private String title;

            @Expose
            @SerializedName("text")
            private String text;

            public String getTitle() {
                return title;
            }

            public String getText() {
                return text;
            }
        }

        @Expose
        @SerializedName("createdAt")
        private int[] createdAt;

        public int getPostid() {
            return postid;
        }

        public int getBoardId() {
            return boardId;
        }

        public String getBoardNameEng() {
            return boardNameEng;
        }

        public int getViews() {
            return views;
        }

        public String getIsSecret() {
            return isSecret;
        }

        public String getIsNotice() {
            return isNotice;
        }

        public String getName() {
            return name;
        }

        public Data.postContent getPostContent() {
            return postContent;
        }

        public int[] getCreatedAt() {
            return createdAt;
        }
    }
}
