package com.se.framework.ui.main.mypage;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.se.framework.data.model.api.TagResponse;

public class TagItemViewModel {
    public final ObservableField<String> id = new ObservableField<>();

    public final ObservableField<String> text = new ObservableField<>();

    public final TagItemViewModelListener mListener;

    public final TagResponse.Data.Tag mTag;

    public TagItemViewModel(TagItemViewModelListener mListener, TagResponse.Data.Tag mTag) {
        this.mListener = mListener;
        this.mTag = mTag;

        this.id.set(Integer.toString(mTag.getTagId()));
        this.text.set(mTag.getText());
    }

    public interface TagItemViewModelListener {

    }
}
