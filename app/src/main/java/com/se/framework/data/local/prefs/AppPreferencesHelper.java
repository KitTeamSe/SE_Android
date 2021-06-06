package com.se.framework.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.se.framework.data.DataManager;
import com.se.framework.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";

    private static final String PREF_KEY_CURRENT_CATEGORY_ID = "PREF_KEY_CURRENT_CATEGORY_ID";

    private static final String PREF_KEY_CURRENT_POST_ID = "PREF_KEY_CURRENT_POST_ID";

    private static final String PREF_KEY_FCM_TOKEN = "PREF_KEY_FCM_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String token) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }

    @Override
    public String getCurrentUserId() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID, null);
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply();
    }

    @Override
    public int getCurrentCategoryId() {
        return mPrefs.getInt(PREF_KEY_CURRENT_CATEGORY_ID, 1);
    }

    @Override
    public void setCurrentCategoryId(int categoryId) {
        mPrefs.edit().putInt(PREF_KEY_CURRENT_CATEGORY_ID, categoryId).apply();
    }

    @Override
    public int getCurrentPostId(){
        return mPrefs.getInt(PREF_KEY_CURRENT_POST_ID, 0);
    }

    @Override
    public void setCurrentPostId(int currentPostId){
        mPrefs.edit().putInt(PREF_KEY_CURRENT_POST_ID, currentPostId).apply();
    }

    @Override
    public String getFcmToken() {
        return mPrefs.getString(PREF_KEY_FCM_TOKEN, null);
    }

    @Override
    public void setFcmToken(String fcmToken) {
        mPrefs.edit().putString(PREF_KEY_FCM_TOKEN, fcmToken).apply();
    }
}

