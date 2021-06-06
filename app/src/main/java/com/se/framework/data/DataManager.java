package com.se.framework.data;

import com.se.framework.data.local.db.DbHelper;
import com.se.framework.data.local.prefs.PreferencesHelper;
import com.se.framework.data.remote.ApiHelper;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {
    void setUserAsLoggedOut();

    void updateApiHeader(String accessToken);

    void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode);

    enum LoggedInMode {
        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_SERVER(1);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
