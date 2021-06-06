package com.se.framework.ui.splash;

import com.se.framework.data.DataManager;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void startSeeding(){
        getDataManager().setCurrentCategoryId(1);

        decideNextActivity();
    }

    private void decideNextActivity(){
        if(getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()){
            getNavigator().openLoginActivity();
        } else {
            getNavigator().openMainActivity();
        }
    }
}
