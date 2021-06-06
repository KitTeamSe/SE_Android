package com.se.framework.ui.main.category;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.se.framework.R;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.databinding.ItemCategoryEmptyViewBinding;
import com.se.framework.databinding.ItemCategoryViewBinding;
import com.se.framework.ui.base.BaseViewHolder;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.utils.AppLogger;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class CategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private final List<CategoryResponse.Category> mCategoryResponseList;

    private CategoryAdapterListener mListener;

    public final MutableLiveData<CategoryResponse.Category> itemClicked = new MutableLiveData<>();

    public CategoryAdapter(List<CategoryResponse.Category> categoryResponseList) {
        this.mCategoryResponseList = categoryResponseList;
    }

    @Override
    public int getItemCount(){
        if (mCategoryResponseList != null && mCategoryResponseList.size() > 0){
            return mCategoryResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mCategoryResponseList != null && !mCategoryResponseList.isEmpty()) {
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
                ItemCategoryViewBinding categoryViewBinding = ItemCategoryViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new CategoryViewHolder(categoryViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemCategoryEmptyViewBinding emptyViewBinding = ItemCategoryEmptyViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<CategoryResponse.Category> categoryList) {
        mCategoryResponseList.addAll(categoryList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCategoryResponseList.clear();
    }

    public void setListener(CategoryAdapterListener listener) {
        this.mListener = listener;
    }

    public interface CategoryAdapterListener {
        void onRetryClick();
    }

    public class CategoryViewHolder extends BaseViewHolder implements CategoryItemViewModel.CategoryItemViewModelListener {
        private ItemCategoryViewBinding mBinding;

        private CategoryItemViewModel mCategoryItemViewModel;

        public CategoryViewHolder(ItemCategoryViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final CategoryResponse.Category category = mCategoryResponseList.get(position);
            mCategoryItemViewModel = new CategoryItemViewModel(category, this);
            mBinding.setViewModel(mCategoryItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(CategoryResponse.Category category) {
            itemClicked.postValue(category);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements CategoryEmptyItemViewModel.CategoryEmptyItemViewModelListener {
        private ItemCategoryEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemCategoryEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            CategoryEmptyItemViewModel emptyItemViewModel = new CategoryEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}
