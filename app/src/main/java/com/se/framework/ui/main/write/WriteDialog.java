package com.se.framework.ui.main.write;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.se.framework.R;
import com.se.framework.SeApp;
import com.se.framework.databinding.DialogWriteBinding;
import com.se.framework.di.component.DaggerDialogComponent;
import com.se.framework.di.component.DialogComponent;
import com.se.framework.di.module.DialogModule;
import com.se.framework.ui.base.BaseDialog;
import com.se.framework.ui.main.post.PostFragment;

import javax.inject.Inject;

public class WriteDialog extends BaseDialog implements WriteCallback {
    private static final String TAG = "WriteDialog";

    DialogWriteBinding binding;

    @Inject
    WriteViewModel mWriteViewModel;

    public static WriteDialog newInstance() {
        WriteDialog fragment = new WriteDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_write, container, false);
        View view = binding.getRoot();

        performDependencyInjection(getBuildComponent());

        binding.setViewModel(mWriteViewModel);

        mWriteViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    private DialogComponent getBuildComponent(){
        return DaggerDialogComponent.builder()
                .appComponent(((SeApp)(getContext().getApplicationContext())).appComponent)
                .dialogModule(new DialogModule(this))
                .build();
    }

    private void performDependencyInjection(DialogComponent buildComponent){
        buildComponent.inject(this);
    }

    @Override
    public void handleError(Throwable throwable){

    }

    @Override
    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void write(){
        String title = binding.titleEdit.getText().toString();
        String content = binding.contentEdit.getText().toString();

        if(mWriteViewModel.isValid(title, content)){
            hideKeyboard();
            mWriteViewModel.write(title, content, binding.spinnerTag.getSelectedItemPosition());
        } else{
            Toast.makeText(getContext(), "유효하지 않은 입력", Toast.LENGTH_SHORT).show();
        }
    }
}
