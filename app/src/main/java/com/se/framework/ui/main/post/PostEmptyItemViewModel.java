package com.se.framework.ui.main.post;

public class PostEmptyItemViewModel {

    private final PostEmptyItemViewModelListener mListener;

    public PostEmptyItemViewModel(PostEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface PostEmptyItemViewModelListener {

        void onRetryClick();
    }
}
