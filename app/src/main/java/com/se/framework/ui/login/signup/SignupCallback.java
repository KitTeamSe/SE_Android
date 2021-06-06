package com.se.framework.ui.login.signup;

public interface SignupCallback {
    void dismissDialog();

    void signup();

    void toast(String msg);
}
