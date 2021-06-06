package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class CategoryResponse {
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
    private List<Category> data;

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Category> getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public static class Category{
        @Expose
        @SerializedName("menuId")
        private int menuId;

        @Expose
        @SerializedName("nameEng")
        private String nameEng;

        @Expose
        @SerializedName("nameKor")
        private String nameKor;

        @Expose
        @SerializedName("menuOrder")
        private int menuOrder;

        @Expose
        @SerializedName("menuType")
        private String menuType;

        @Expose
        @SerializedName("url")
        private String url;

        public String getNameKor() {
            return nameKor;
        }

        public int getMenuId() {
            return menuId;
        }

        public String getNameEng() {
            return nameEng;
        }

        public int getMenuOrder() {
            return menuOrder;
        }

        public String getMenuType() {
            return menuType;
        }

        public String getUrl() {
            return url;
        }
    }
}
