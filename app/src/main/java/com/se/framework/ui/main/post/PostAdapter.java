package com.se.framework.ui.main.post;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.se.framework.data.model.api.PostResponse;
import com.se.framework.databinding.ItemPostEmptyViewBinding;
import com.se.framework.databinding.ItemPostViewBinding;
import com.se.framework.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<PostResponse.Data.PostListItem.Post> mPostResponseList;

    public final MutableLiveData<PostResponse.Data.PostListItem.Post> clickedItem = new MutableLiveData<>();

    private PostAdapterListener mListener;

    public PostAdapter(List<PostResponse.Data.PostListItem.Post> postReponseList) {
        this.mPostResponseList = postReponseList;
    }

    @Override
    public int getItemCount() {
        if (mPostResponseList != null && mPostResponseList.size() > 0) {
            return mPostResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mPostResponseList != null && mPostResponseList.size() > 0) {
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
                ItemPostViewBinding itemPostViewBinding = ItemPostViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new PostViewHolder(itemPostViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemPostEmptyViewBinding emptyViewBinding = ItemPostEmptyViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<PostResponse.Data.PostListItem.Post> postList) {
        mPostResponseList.addAll(postList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPostResponseList.clear();
    }

    public void setListener(PostAdapterListener listener) {
        this.mListener = listener;
    }

    public interface PostAdapterListener {

        void onRetryClick();
    }

    public class PostViewHolder extends BaseViewHolder implements PostItemViewModel.PostItemViewModelListener {

        private final ItemPostViewBinding mBinding;

        private PostItemViewModel mPostItemViewModel;

        public PostViewHolder(ItemPostViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final PostResponse.Data.PostListItem.Post post = mPostResponseList.get(position);
            mPostItemViewModel = new PostItemViewModel(post, this);
            mBinding.setViewModel(mPostItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(PostResponse.Data.PostListItem.Post post) {
            clickedItem.postValue(post);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements PostEmptyItemViewModel.PostEmptyItemViewModelListener {

        private final ItemPostEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemPostEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            PostEmptyItemViewModel emptyItemViewModel = new PostEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}
