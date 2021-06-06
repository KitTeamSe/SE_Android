package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRequest {
    public int getBoardId() {
        return boardId;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getPostSearchType() {
        return postSearchType;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public SearchRequest(int boardId, String keyword, String postSearchType, String direction, int page, int size) {
        this.boardId = boardId;
        this.keyword = keyword;
        this.postSearchType = postSearchType;
        this.pageRequest = new PageRequest(direction, page, size);
    }

    @Expose
    @SerializedName("boardId")
    private int boardId;

    @Expose
    @SerializedName("keyword")
    private String keyword;

    @Expose
    @SerializedName("postSearchType")
    private String postSearchType;

    @Expose
    @SerializedName("pageRequest")
    private PageRequest pageRequest;

    public static class PageRequest{
        public String getDirection() {
            return direction;
        }

        public int getPage() {
            return page;
        }

        public int getSize() {
            return size;
        }

        public PageRequest(String direction, int page, int size) {
            this.direction = direction;
            this.page = page;
            this.size = size;
        }

        @Expose
        @SerializedName("direction")
        private String direction;

        @Expose
        @SerializedName("page")
        private int page;

        @Expose
        @SerializedName("size")
        private int size;
    }
}
