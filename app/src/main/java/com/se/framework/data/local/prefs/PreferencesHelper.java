package com.se.framework.data.local.prefs;

import com.se.framework.data.DataManager;

public interface PreferencesHelper {
    String getAccessToken();

    void setAccessToken(String token);

    String getCurrentUserId();

    void setCurrentUserId(String userId);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    int getCurrentCategoryId();

    void setCurrentCategoryId(int categoryId);

    int getCurrentPostId();

    void setCurrentPostId(int currentPostId);

    String getFcmToken();

    void setFcmToken(String fcmToken);
}
