package com.se.framework.ui.login.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.se.framework.R;
import com.se.framework.SeApp;
import com.se.framework.databinding.DialogSignupBinding;
import com.se.framework.di.component.DaggerDialogComponent;
import com.se.framework.di.component.DialogComponent;
import com.se.framework.di.module.DialogModule;
import com.se.framework.ui.base.BaseDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SignupDialog extends BaseDialog implements SignupCallback {
    private static final String TAG = "SignupDialog";

    private DialogSignupBinding binding;

    @Inject
    SignupViewModel mSignupViewModel;

    public static SignupDialog newInstance(){
        SignupDialog fragment = new SignupDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void signup(){
        String ans = "구미";
        String email = binding.emailText.getText().toString();
        String id = binding.idText.getText().toString();
        String name = binding.nameText.getText().toString();
        String nick = binding.nickText.getText().toString();
        String pw = binding.pwText.getText().toString();
        String pwCheck = binding.pwCheckText.getText().toString();
        String phoneNumber = binding.phoneText.getText().toString();
        String studentId = binding.stuNumText.getText().toString();
        int questionId = 1;

        if(mSignupViewModel.checkPw(pw, pwCheck) && mSignupViewModel.isValid(id, pw, email, name, nick, phoneNumber, studentId)){
            hideKeyboard();
            mSignupViewModel.signup(ans, email, id, name, nick, pw, questionId, phoneNumber, studentId);
        } else {
            toast("유효하지 않은 입력");
        }
    }

    @Override
    public void toast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_signup, container, false);

        View view = binding.getRoot();

        performDependencyInjection(getBuildComponent());

        binding.setViewModel(mSignupViewModel);

        mSignupViewModel.setNavigator(this);

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
}
