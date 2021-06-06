package com.se.framework.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.se.framework.BR;
import com.se.framework.R;
import com.se.framework.databinding.ActivitySplashBinding;
import com.se.framework.di.component.ActivityComponent;
import com.se.framework.ui.base.BaseActivity;
import com.se.framework.ui.login.LoginActivity;
import com.se.framework.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator{
    private final int delayedTime = 3000;
    Handler handler = new Handler();

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewModel.startSeeding();
            }
        }, delayedTime);
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void openMainActivity(){
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity(){
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
