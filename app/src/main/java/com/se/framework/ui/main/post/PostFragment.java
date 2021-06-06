package com.se.framework.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.databinding.FragmentPostBinding;
import com.se.framework.di.component.FragmentComponent;
import com.se.framework.ui.base.BaseFragment;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.ui.main.lookup.LookupDialog;

import java.util.List;

import javax.inject.Inject;

public class PostFragment extends BaseFragment<FragmentPostBinding, PostViewModel> implements PostNavigator, PostAdapter.PostAdapterListener {
    public static final String TAG = "PostFragment";

    FragmentPostBinding mFragmentPostBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    PostAdapter mPostAdapter;

    public static PostFragment newInstance(){
        Bundle args = new Bundle();
        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_post;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mPostAdapter.setListener(this);

        mPostAdapter.clickedItem.observe(this, post -> {
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
    public void onRetryClick() {
        mViewModel.fetchPosts();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPostBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void updatePost(List<PostResponse.Data.PostListItem.Post> postList){
        mPostAdapter.addItems(postList);
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentPostBinding.postRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentPostBinding.postRecyclerView.setAdapter(mPostAdapter);
    }

    @Override
    public void write(){
        ((MainActivity)getContext()).showWriteDialog();
    }

    @Override
    public void fetch(){
        mViewModel.fetchPosts();
    }
}

