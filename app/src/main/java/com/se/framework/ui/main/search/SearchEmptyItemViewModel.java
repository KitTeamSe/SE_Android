package com.se.framework.ui.main.search;

import com.se.framework.ui.main.post.PostEmptyItemViewModel;

public class SearchEmptyItemViewModel {
    private final SearchEmptyItemViewModelListener mListener;

    public SearchEmptyItemViewModel(SearchEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public interface SearchEmptyItemViewModelListener {

    }
}
