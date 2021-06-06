package com.se.framework.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.se.framework.ViewModelProviderFactory;
import com.se.framework.data.DataManager;
import com.se.framework.ui.base.BaseDialog;
import com.se.framework.ui.login.signup.SignupViewModel;
import com.se.framework.ui.main.lookup.LookupViewModel;
import com.se.framework.ui.main.write.WriteViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class DialogModule {
    private BaseDialog dialog;

    public DialogModule(BaseDialog dialog){
        this.dialog = dialog;
    }

    @Provides
    SignupViewModel provideSignupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SignupViewModel> supplier = () -> new SignupViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SignupViewModel> factory = new ViewModelProviderFactory<>(SignupViewModel.class, supplier);
        return new ViewModelProvider(dialog.getActivity(), factory).get(SignupViewModel.class);
    }

    @Provides
    LookupViewModel provideLookupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<LookupViewModel> supplier = () -> new LookupViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<LookupViewModel> factory = new ViewModelProviderFactory<>(LookupViewModel.class, supplier);
        return new ViewModelProvider(dialog.getActivity(), factory).get(LookupViewModel.class);
    }

    @Provides
    WriteViewModel provideWriteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<WriteViewModel> supplier = () -> new WriteViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<WriteViewModel> factory = new ViewModelProviderFactory<>(WriteViewModel.class, supplier);
        return new ViewModelProvider(dialog.getActivity(), factory).get(WriteViewModel.class);
    }

}
