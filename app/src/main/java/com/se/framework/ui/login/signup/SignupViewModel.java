package com.se.framework.ui.login.signup;

import android.text.TextUtils;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.SignupRequest;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.CommonUtils;
import com.se.framework.utils.rx.SchedulerProvider;

public class SignupViewModel extends BaseViewModel<SignupCallback> {

    public SignupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        super(dataManager, schedulerProvider);
    }


    public void onCloseClick(){
        getNavigator().dismissDialog();
    }

    public void onSignupFinClick(){
        getNavigator().signup();
    }

    public void signup(String ans, String email, String id, String name, String nick, String pw, int questionId, String phoneNumber, String studentId){
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager()
        .doSignupApiCall(new SignupRequest(ans, email, id, name, nick, pw, questionId, phoneNumber, Integer.parseInt(studentId)))
                .doOnSuccess(signupResponse -> signupResponse.getStatus())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(signupResponse -> {
                    setIsLoading(false);
                    getNavigator().toast(signupResponse.getMessage());
                    if(signupResponse.getStatus() == 201){
                        getNavigator().dismissDialog();
                    }
                }, throwable -> {
                    setIsLoading(false);
                }));
    }

    public boolean checkPw(String pw, String check){
        if(pw.equals(check)){
            return true;
        }
        return false;
    }

    public boolean isValid(String id, String pw, String email, String name, String nick, String phoneNumber, String studentId){
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        if (TextUtils.isEmpty(pw)){
            return false;
        }
        if (TextUtils.isEmpty(email)){
            return false;
        }
        if (!CommonUtils.isEmailValid(email)){
            return false;
        }
        if (TextUtils.isEmpty(name)){
            return false;
        }
        if (TextUtils.isEmpty(nick)){
            return false;
        }
        if(TextUtils.isEmpty(phoneNumber)){
            return false;
        }
        if(TextUtils.isEmpty(studentId)){
            return false;
        }
        return true;
    }
}
