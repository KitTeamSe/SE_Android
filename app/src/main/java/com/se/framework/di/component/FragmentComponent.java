package com.se.framework.di.component;

import com.se.framework.di.module.FragmentModule;
import com.se.framework.di.scope.FragmentScope;
import com.se.framework.ui.main.category.CategoryFragment;
import com.se.framework.ui.main.mypage.MypageFragment;
import com.se.framework.ui.main.post.PostFragment;
import com.se.framework.ui.main.search.SearchFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(CategoryFragment fragment);

    void inject(PostFragment fragment);

    void inject(MypageFragment fragment);

    void inject(SearchFragment fragment);
}
