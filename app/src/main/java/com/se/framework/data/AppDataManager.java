package com.se.framework.data;

import android.content.Context;

import com.google.gson.Gson;
import com.se.framework.data.local.db.DbHelper;
import com.se.framework.data.local.prefs.PreferencesHelper;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.data.model.api.LoginRequest;
import com.se.framework.data.model.api.LoginResponse;
import com.se.framework.data.model.api.LookupResponse;
import com.se.framework.data.model.api.MyResponse;
import com.se.framework.data.model.api.MyTagAddRequest;
import com.se.framework.data.model.api.MyTagAddResponse;
import com.se.framework.data.model.api.MyTagDelResponse;
import com.se.framework.data.model.api.MyTagResponse;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.data.model.api.SearchRequest;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.data.model.api.SignupRequest;
import com.se.framework.data.model.api.SignupResponse;
import com.se.framework.data.model.api.TagResponse;
import com.se.framework.data.model.api.WriteRequest;
import com.se.framework.data.model.api.WriteResponse;
import com.se.framework.data.model.db.User;
import com.se.framework.data.remote.ApiHeader;
import com.se.framework.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager{
    private static final String TAG = "AppDataManager";

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final PreferencesHelper mPreferencesHelper;

    private final ApiHelper mApiHelper;

    private final Gson mGson;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }
    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Single<SignupResponse> doSignupApiCall(SignupRequest request) {
        return mApiHelper.doSignupApiCall(request);
    }

    @Override
    public Single<CategoryResponse> doCategoryApiCall() {
        return mApiHelper.doCategoryApiCall();
    }

    @Override
    public Single<PostResponse> doPostApiCall(int currentCategory) {
        return mApiHelper.doPostApiCall(currentCategory);
    }

    @Override
    public Single<LookupResponse> doLookupApiCall(int postId) {
        return mApiHelper.doLookupApiCall(postId);
    }

    @Override
    public Single<TagResponse> doTagApiCall() {
        return mApiHelper.doTagApiCall();
    }

    @Override
    public Single<WriteResponse> doWriteApiCall(WriteRequest request) {
        return mApiHelper.doWriteApiCall(request);
    }

    @Override
    public Single<MyResponse> doMyApiCall() {
        return mApiHelper.doMyApiCall();
    }

    @Override
    public Single<MyTagResponse> doMyTagApiCall() {
        return mApiHelper.doMyTagApiCall();
    }

    @Override
    public Single<MyTagAddResponse> doMyTagAddApiCall(MyTagAddRequest request) {
        return mApiHelper.doMyTagAddApiCall(request);
    }

    @Override
    public Single<MyTagDelResponse> doMyTagDelApiCall(int delTagId) {
        return mApiHelper.doMyTagDelApiCall(delTagId);
    }

    @Override
    public Single<SearchResponse> doSearchApiCall(SearchRequest request) {
        return mApiHelper.doSearchApiCall(request);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public int getCurrentCategoryId() {
        return mPreferencesHelper.getCurrentCategoryId();
    }

    @Override
    public void setCurrentCategoryId(int categoryId) {
        mPreferencesHelper.setCurrentCategoryId(categoryId);
    }

    @Override
    public int getCurrentPostId() {
        return mPreferencesHelper.getCurrentPostId();
    }

    @Override
    public void setCurrentPostId(int currentPostId) {
        mPreferencesHelper.setCurrentPostId(currentPostId);
    }

    @Override
    public String getFcmToken() {
        return mPreferencesHelper.getFcmToken();
    }

    @Override
    public void setFcmToken(String fcmToken) {
        mPreferencesHelper.setFcmToken(fcmToken);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null, null, DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
    }

    @Override
    public void updateApiHeader(String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public void updateUserInfo(
            String accessToken,
            String userId,
            LoggedInMode loggedInMode){

        setAccessToken(accessToken);
        setCurrentUserId(userId);
        setCurrentUserLoggedInMode(loggedInMode);

        updateApiHeader(accessToken);
    }

    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }
}
