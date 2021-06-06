package com.se.framework.ui.main.mypage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.databinding.FragmentMypageBinding;
import com.se.framework.di.component.FragmentComponent;
import com.se.framework.ui.base.BaseFragment;

import javax.inject.Inject;

public class MypageFragment extends BaseFragment<FragmentMypageBinding, MypageViewModel> implements MypageNavigator, TagAdapter.TagAdapterListener{
    public static final String TAG = "MypageFragment";

    FragmentMypageBinding mFragmentMypageBinding;

    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    TagAdapter mTagAdapter;

    public static MypageFragment newInstance() {
        Bundle args = new Bundle();
        MypageFragment fragment = new MypageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mypage;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void hideKeyboard(){
        super.hideKeyboard();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        mTagAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mViewModel.fetchTags();
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMypageBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentMypageBinding.tagRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentMypageBinding.tagRecyclerView.setAdapter(mTagAdapter);
    }

    @Override
    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void add(){
        String id = mFragmentMypageBinding.tagEdit.getText().toString();
        Log.e("ID", Integer.toString(id.length()));
        if(mViewModel.isIdValid(id)){
            mViewModel.add(Integer.parseInt(id));
        } else{
            Toast.makeText(getContext(), "유효하지 않은 입력", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void del(){
        String id = mFragmentMypageBinding.tagEdit.getText().toString();
        if(mViewModel.isIdValid(id)){
            mViewModel.del(Integer.parseInt(id));
        } else{
            Toast.makeText(getContext(), "유효하지 않은 입력", Toast.LENGTH_SHORT).show();
        }
    }
}
