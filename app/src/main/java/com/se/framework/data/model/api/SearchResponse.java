package com.se.framework.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
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

    public static class Data {
        @Expose
        @SerializedName("postListItem")
        private PostListItem postListItem;

        public PostListItem getPostListItem() {
            return postListItem;
        }

        public void setPostListItem(PostListItem postListItem) {
            this.postListItem = postListItem;
        }

        public static class PostListItem {
            private List<Post> content;

            public List<Post> getContent() {
                return content;
            }

            public void setContent(List<Post> content) {
                this.content = content;
            }

            public static class Post {
                @Expose
                @SerializedName("postId")
                private int postId;

                @Expose
                @SerializedName("boardId")
                private int boardId;

                @Expose
                @SerializedName("createAt")
                private int[] createAt;

                @Expose
                @SerializedName("isNotice") // NORMAL, NOTICE
                private String isNotice;

                @Expose
                @SerializedName("isSecret")
                private String isSecret;

                @Expose
                @SerializedName("nickname")
                private String nickname;

                @Expose
                @SerializedName("numReply")
                private int numReply;

                @Expose
                @SerializedName("previewText")
                private String previewText;

                @Expose
                @SerializedName("title")
                private String title;

                @Expose
                @SerializedName("views")
                private int views;

                public int getBoardId() {
                    return boardId;
                }

                public int[] getCreateAt() {
                    return createAt;
                }

                public String getIsNotice() {
                    return isNotice;
                }

                public String getIsSecret() {
                    return isSecret;
                }

                public String getNickname() {
                    return nickname;
                }

                public int getNumReply() {
                    return numReply;
                }

                public int getPostId() {
                    return postId;
                }

                public String getPreviewText() {
                    return previewText;
                }

                public String getTitle() {
                    return title;
                }

                public int getViews() {
                    return views;
                }
            }
        }
    }
}
