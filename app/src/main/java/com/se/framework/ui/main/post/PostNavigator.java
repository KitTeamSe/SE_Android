package com.se.framework.ui.main.post;

import com.se.framework.data.model.api.PostResponse;

import java.util.List;

public interface PostNavigator {
    void handleError(Throwable throwable);

    void updatePost(List<PostResponse.Data.PostListItem.Post> postList);

    void write();

    void showLookupDialog();

    void fetch();
}
