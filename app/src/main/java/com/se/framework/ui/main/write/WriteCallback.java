package com.se.framework.ui.main.write;

public interface WriteCallback {
    void dismissDialog();

    void showToast(String msg);

    void handleError(Throwable throwable);

    void write();
}
