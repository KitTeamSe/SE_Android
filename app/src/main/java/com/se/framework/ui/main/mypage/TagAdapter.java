package com.se.framework.ui.main.mypage;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.se.framework.data.model.api.TagResponse;
import com.se.framework.databinding.ItemTagEmptyViewBinding;
import com.se.framework.databinding.ItemTagViewBinding;
import com.se.framework.ui.base.BaseViewHolder;

import java.util.List;

public class TagAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<TagResponse.Data.Tag> mTagResponseList;

    private TagAdapterListener mListener;

    public TagAdapter(List<TagResponse.Data.Tag> tagResponseList){
        this.mTagResponseList = tagResponseList;
    }

    @Override
    public int getItemCount() {
        if (mTagResponseList != null && mTagResponseList.size() > 0) {
            return mTagResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mTagResponseList != null && !mTagResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemTagViewBinding tagViewBinding = ItemTagViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new TagViewHolder(tagViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemTagEmptyViewBinding emptyViewBinding = ItemTagEmptyViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<TagResponse.Data.Tag> tagList) {
        mTagResponseList.addAll(tagList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mTagResponseList.clear();
    }

    public void setListener(TagAdapterListener listener) {
        this.mListener = listener;
    }

    public interface TagAdapterListener {

        void onRetryClick();
    }

    public class TagViewHolder extends BaseViewHolder implements TagItemViewModel.TagItemViewModelListener {

        private ItemTagViewBinding mBinding;

        private TagItemViewModel mTagItemViewModel;

        public TagViewHolder(ItemTagViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final TagResponse.Data.Tag tag = mTagResponseList.get(position);
            mTagItemViewModel = new TagItemViewModel(this, tag);
            mBinding.setViewModel(mTagItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }
    }
    public class EmptyViewHolder extends BaseViewHolder implements TagEmptyItemViewModel.TagEmptyItemViewModelListener {

        private ItemTagEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemTagEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            TagEmptyItemViewModel emptyItemViewModel = new TagEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}
