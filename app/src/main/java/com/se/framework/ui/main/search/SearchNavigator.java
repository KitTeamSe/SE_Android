package com.se.framework.ui.main.search;

import com.se.framework.data.model.api.SearchResponse;

import java.util.List;

public interface SearchNavigator {
    void handleError(Throwable throwable);

    void showLookupDialog ();

    void updateSearch(List<SearchResponse.Data.PostListItem.Post> postList);

    void search();

    void showToast(String msg);
}
