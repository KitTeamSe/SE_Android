package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyTagAddRequest {
    public MyTagAddRequest(int tagId) {
        this.tagId = tagId;
    }

    @Expose
    @SerializedName("tagId")
    private int tagId;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
