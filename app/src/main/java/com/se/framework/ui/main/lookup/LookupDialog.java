package com.se.framework.ui.main.lookup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.se.framework.R;
import com.se.framework.SeApp;
import com.se.framework.databinding.DialogLookupBinding;
import com.se.framework.di.component.DaggerDialogComponent;
import com.se.framework.di.component.DialogComponent;
import com.se.framework.di.module.DialogModule;
import com.se.framework.ui.base.BaseDialog;

import javax.inject.Inject;

public class LookupDialog extends BaseDialog implements LookupCallback{
    private static final String TAG = "SignupDialog";

    private DialogLookupBinding binding;

    @Inject
    LookupViewModel mLookupViewModel;

    public static LookupDialog newInstance() {
        LookupDialog fragment = new LookupDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void dismissDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_lookup, container, false);
        View view = binding.getRoot();

        performDependencyInjection(getBuildComponent());

        binding.setViewModel(mLookupViewModel);

        mLookupViewModel.setNavigator(this);

        mLookupViewModel.fetchPost();

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
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void showToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
