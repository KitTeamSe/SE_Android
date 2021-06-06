package com.se.framework.ui.main.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.databinding.ItemSearchEmptyViewBinding;
import com.se.framework.databinding.ItemSearchViewBinding;
import com.se.framework.ui.base.BaseViewHolder;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<SearchResponse.Data.PostListItem.Post> mSearchResponseList;

    public final MutableLiveData<SearchResponse.Data.PostListItem.Post> clickedItem = new MutableLiveData<>();

    private SearchAdapterListener mListener;

    public SearchAdapter(List<SearchResponse.Data.PostListItem.Post> searchReponseList) {
        this.mSearchResponseList = searchReponseList;
    }

    @Override
    public int getItemCount() {
        if (mSearchResponseList != null && mSearchResponseList.size() > 0) {
            return mSearchResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mSearchResponseList != null && mSearchResponseList.size() > 0) {
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
                ItemSearchViewBinding itemSearchViewBinding = ItemSearchViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SearchViewHolder(itemSearchViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemSearchEmptyViewBinding emptyViewBinding = ItemSearchEmptyViewBinding
                        .inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<SearchResponse.Data.PostListItem.Post> searchList) {
        mSearchResponseList.addAll(searchList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mSearchResponseList.clear();
    }

    public void setListener(SearchAdapterListener listener) {
        this.mListener = listener;
    }

    public interface SearchAdapterListener {

    }

    public class SearchViewHolder extends BaseViewHolder implements SearchItemViewModel.SearchItemViewModelListener {

        private final ItemSearchViewBinding mBinding;

        private SearchItemViewModel mSearchItemViewModel;

        public SearchViewHolder(ItemSearchViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final SearchResponse.Data.PostListItem.Post search = mSearchResponseList.get(position);
            mSearchItemViewModel = new SearchItemViewModel(search, this);
            mBinding.setViewModel(mSearchItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(SearchResponse.Data.PostListItem.Post post) {
            clickedItem.postValue(post);
        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements SearchEmptyItemViewModel.SearchEmptyItemViewModelListener {

        private final ItemSearchEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemSearchEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            SearchEmptyItemViewModel emptyItemViewModel = new SearchEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }
    }
}
