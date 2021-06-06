package com.se.framework.ui.main.category;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.databinding.FragmentCategoryBinding;
import com.se.framework.di.component.FragmentComponent;
import com.se.framework.ui.base.BaseFragment;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.ui.main.MainViewModel;

import java.util.List;

import javax.inject.Inject;

public class CategoryFragment extends BaseFragment<FragmentCategoryBinding, CategoryViewModel> implements CategoryNavigator, CategoryAdapter.CategoryAdapterListener {

    public static final String TAG = "CategoryFragment";

    FragmentCategoryBinding mFragmentCategoryBinding;

    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    CategoryAdapter mCategoryAdapter;

    public static CategoryFragment newInstance(){
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mCategoryAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() { mViewModel.fetchCategorys(); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCategoryBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void updateCategory(List<CategoryResponse.Category> categoryList){
        mCategoryAdapter.addItems(categoryList);
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCategoryBinding.categoryRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentCategoryBinding.categoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentCategoryBinding.categoryRecyclerView.setAdapter(mCategoryAdapter);

        mCategoryAdapter.itemClicked.observe(this, category -> {
            if(category.getMenuType().equals("BOARD")){
                mViewModel.setCurrentCategory(category.getMenuId());
                ((MainActivity) getContext()).showHomeFragment(category.getNameEng());
            } else if(category.getMenuType().equals("REDIRECT")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(category.getUrl()));
                startActivity(intent);
            }
        });
    }
}
