package com.se.framework.ui.main;

import android.content.res.Resources;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.FirebaseApp;
import com.se.framework.R;
import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.net.URL;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    private static final String TAG = "MainViewModel";

    private final ObservableField<String> title;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        title = new ObservableField<>();

        title.set("SE board");
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void onLogoutClick(){
        getDataManager().setUserAsLoggedOut();
        getNavigator().openLoginActivity();
    }
}
