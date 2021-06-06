package com.se.framework.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.se.framework.BuildConfig;
import com.se.framework.data.AppDataManager;
import com.se.framework.data.DataManager;
import com.se.framework.data.local.db.AppDatabase;
import com.se.framework.data.local.db.AppDbHelper;
import com.se.framework.data.local.db.DbHelper;
import com.se.framework.data.local.prefs.AppPreferencesHelper;
import com.se.framework.data.local.prefs.PreferencesHelper;
import com.se.framework.data.remote.ApiHeader;
import com.se.framework.data.remote.ApiHelper;
import com.se.framework.data.remote.AppApiHelper;
import com.se.framework.di.ApiInfo;
import com.se.framework.di.DatabaseInfo;
import com.se.framework.di.PreferenceInfo;
import com.se.framework.utils.AppConstants;
import com.se.framework.utils.rx.AppSchedulerProvider;
import com.se.framework.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(preferencesHelper.getAccessToken());
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }
}
