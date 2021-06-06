package com.se.framework.ui.main.lookup;

public interface LookupCallback {
    void dismissDialog();

    void onDestroy();

    void showToast(String msg);
}
