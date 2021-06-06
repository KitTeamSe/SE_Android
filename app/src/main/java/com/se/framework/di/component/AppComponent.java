package com.se.framework.di.component;

import android.app.Application;

import com.se.framework.SeApp;
import com.se.framework.data.DataManager;
import com.se.framework.di.module.AppModule;
import com.se.framework.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(SeApp app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
