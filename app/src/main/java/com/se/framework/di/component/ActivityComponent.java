package com.se.framework.di.component;

import com.se.framework.di.module.ActivityModule;
import com.se.framework.di.scope.ActivityScope;
import com.se.framework.ui.login.LoginActivity;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.ui.splash.SplashActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);
}