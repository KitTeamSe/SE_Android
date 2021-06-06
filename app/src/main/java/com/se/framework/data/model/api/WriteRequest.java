package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public final class WriteRequest {
//    new WriteRequest(title, content, tagId, boardId, check);
    public WriteRequest(String title, String content, int tagId, int boardId, String secret){
        this.boardId = boardId;
        this.isNotice = "NORMAL";
        this.isSecret = secret;
        this.anonymous = new Anonymous("string", "string");
        this.attachmentList = new ArrayList<>();
        this.postContent = new PostContent(title, content);
        this.tagList = new ArrayList<>();
        if(tagId !=0){
            this.tagList.add(new TagInt(tagId));
        }

    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public List<AttachmentClass> getAttachmentList() {
        return attachmentList;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getIsNotice() {
        return isNotice;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public PostContent getPostContent() {
        return postContent;
    }

    public List<TagInt> getTagList() {
        return tagList;
    }

    @Expose
    @SerializedName("anonymous")
    private Anonymous anonymous;

    public static class Anonymous{
        public Anonymous (String anonymousNickname, String anonymousPassword){
            this.anonymousNickname = anonymousNickname;
            this.anonymousPassword = anonymousPassword;
        }

        @Expose
        @SerializedName("anonymousNickname")
        private String anonymousNickname;

        @Expose
        @SerializedName("anonymousPassword")
        private String anonymousPassword;

        public String getAnonymousNickname() {
            return anonymousNickname;
        }

        public String getAnonymousPassword() {
            return anonymousPassword;
        }
    }

    @Expose
    @SerializedName("attachmentList")
    private List<AttachmentClass> attachmentList;

    public static class AttachmentClass{
        public AttachmentClass(){

        }

        public AttachmentClass(int id){
            attachId = id;
        }

        @Expose
        @SerializedName("attachId")
        private int attachId;

        public int getAttachId() {
            return attachId;
        }
    }

    @Expose
    @SerializedName("boardId")
    private int boardId;

    @Expose
    @SerializedName("isNotice")
    private String isNotice;

    @Expose
    @SerializedName("isSecret")
    private String isSecret;

    @Expose
    @SerializedName("postContent")
    private PostContent postContent;

    public static class PostContent{
        public PostContent(String title, String text){
            this.title = title;
            this.text = text;
        }

        @Expose
        @SerializedName("text")
        private String text;

        @Expose
        @SerializedName("title")
        private String title;

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }
    }

    @Expose
    @SerializedName("tagList")
    private List<TagInt> tagList;

    public static class TagInt{
        public TagInt(int id){
            tagId = id;
        }

        @Expose
        @SerializedName("tagId")
        private int tagId;

        public int getTagId() {
            return tagId;
        }
    }
}
