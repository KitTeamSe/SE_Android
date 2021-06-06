package com.se.framework.ui.main.mypage;

public interface MypageNavigator {
    void handleError(Throwable throwable);

    void showToast(String msg);

    void hideKeyboard();

    void add();

    void del();
}
