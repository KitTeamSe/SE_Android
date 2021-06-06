package com.se.framework.ui.main.mypage;

public class TagEmptyItemViewModel {
    private TagEmptyItemViewModelListener mListener;

    public TagEmptyItemViewModel(TagEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface TagEmptyItemViewModelListener {

        void onRetryClick();
    }
}
