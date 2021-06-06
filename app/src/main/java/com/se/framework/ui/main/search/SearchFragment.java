package com.se.framework.ui.main.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.databinding.FragmentSearchBinding;
import com.se.framework.di.component.FragmentComponent;
import com.se.framework.ui.base.BaseFragment;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.ui.main.lookup.LookupDialog;
import com.se.framework.ui.main.post.PostFragment;

import java.util.List;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> implements SearchNavigator, SearchAdapter.SearchAdapterListener {
    public static final String TAG = "SearchFragment";

    FragmentSearchBinding mFragmentSearchBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    SearchAdapter mSearchAdapter;

    public static SearchFragment newInstance(){
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mSearchAdapter.setListener(this);

        mSearchAdapter.clickedItem.observe(this, post -> {
            if(post.getPostId() != 0){
                Log.e("POST", Integer.toString(post.getPostId()));
                mViewModel.setCurrentPostId(post.getPostId());
            }
        });
    }

    @Override
    public void showLookupDialog (){
        LookupDialog.newInstance().show(getFragmentManager());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentSearchBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void updateSearch(List<SearchResponse.Data.PostListItem.Post> postList){
        mSearchAdapter.addItems(postList);
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentSearchBinding.searchRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentSearchBinding.searchRecyclerView.setAdapter(mSearchAdapter);
    }

    @Override
    public void search(){
        String keyword = mFragmentSearchBinding.searchView.getText().toString();
        if(mViewModel.isValid(keyword)){
            hideKeyboard();
            mViewModel.search(keyword);
        } else {
            showToast("유효하지 않은 입력");
        }
    }

    @Override
    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
