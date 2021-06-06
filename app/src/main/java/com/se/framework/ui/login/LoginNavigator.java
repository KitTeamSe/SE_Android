package com.se.framework.ui.login;

public interface LoginNavigator {
    void handleError(Throwable throwable);

    void login();

    void signup();

    void openMainActivity();
}
