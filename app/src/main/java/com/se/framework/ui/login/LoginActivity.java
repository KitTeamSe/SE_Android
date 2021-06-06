package com.se.framework.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.databinding.ActivityLoginBinding;
import com.se.framework.di.component.ActivityComponent;
import com.se.framework.ui.base.BaseActivity;
import com.se.framework.ui.login.signup.SignupDialog;
import com.se.framework.ui.main.MainActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleError(Throwable throwable){

    }

    @Override
    public void login(){
        String id = mActivityLoginBinding.loginText.getText().toString();
        String pwd = mActivityLoginBinding.pwdText.getText().toString();
        if (mViewModel.isIdAndPasswordValid(id, pwd)) {
            hideKeyboard();
            mViewModel.login(id, pwd);
        } else {
            Toast.makeText(this, getString(R.string.invalid_id_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void signup(){
        SignupDialog.newInstance().show(getSupportFragmentManager());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }
}
