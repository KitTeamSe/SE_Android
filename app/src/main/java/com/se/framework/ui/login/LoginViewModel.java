package com.se.framework.ui.login;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.se.framework.R;
import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.LoginRequest;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.CommonUtils;
import com.se.framework.utils.rx.SchedulerProvider;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {
    private static final String TAG = "LoginViewModel";

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public boolean isIdAndPasswordValid(String id, String password) {
        // validate id and password
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return true;
    }

    public void login(String id, String password){
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(id, password))
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                response.getData().getToken(),
                                id,
                                DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER ))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public void onLoginClick(){
        getNavigator().login();
    }

    public void onAutoLoginClick(){

    }

    public void onFindClick(){

    }

    public void onSignupClick(){
        getNavigator().signup();
    }

//    public void onGuestClick(){
//
//    }
}
