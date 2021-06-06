package com.se.framework.di.component;

import com.se.framework.di.module.DialogModule;
import com.se.framework.di.scope.DialogScope;
import com.se.framework.ui.login.signup.SignupDialog;
import com.se.framework.ui.main.lookup.LookupDialog;
import com.se.framework.ui.main.write.WriteDialog;

import dagger.Component;

@DialogScope
@Component(modules = DialogModule.class, dependencies = AppComponent.class)
public interface DialogComponent {
    void inject(SignupDialog dialog);

    void inject(LookupDialog dialog);

    void inject(WriteDialog dialog);
}
