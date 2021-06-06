package com.se.framework.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.se.framework.ViewModelProviderFactory;
import com.se.framework.data.DataManager;
import com.se.framework.ui.base.BaseFragment;
import com.se.framework.ui.main.category.CategoryAdapter;
import com.se.framework.ui.main.category.CategoryViewModel;
import com.se.framework.ui.main.mypage.MypageViewModel;
import com.se.framework.ui.main.mypage.TagAdapter;
import com.se.framework.ui.main.post.PostAdapter;
import com.se.framework.ui.main.post.PostViewModel;
import com.se.framework.ui.main.search.SearchAdapter;
import com.se.framework.ui.main.search.SearchViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private BaseFragment<?, ?> fragment;

    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    CategoryAdapter provideCategoryAdapter() { return new CategoryAdapter(new ArrayList<>()); }

    @Provides
    CategoryViewModel provideCategoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        Supplier<CategoryViewModel> supplier = () -> new CategoryViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<CategoryViewModel> factory = new ViewModelProviderFactory<>(CategoryViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(CategoryViewModel.class);
    }

    @Provides
    PostAdapter providePostAdapter() { return new PostAdapter(new ArrayList<>()); }

    @Provides
    PostViewModel providePostViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<PostViewModel> supplier = () -> new PostViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<PostViewModel> factory = new ViewModelProviderFactory<>(PostViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(PostViewModel.class);
    }

    @Provides
    TagAdapter provideTagAdapter() { return  new TagAdapter(new ArrayList<>()); }

    @Provides
    MypageViewModel provideMypageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<MypageViewModel> supplier = () -> new MypageViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<MypageViewModel> factory = new ViewModelProviderFactory<>(MypageViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(MypageViewModel.class);
    }

    @Provides
    SearchAdapter provideSearchAdapter() { return new SearchAdapter(new ArrayList<>()); }

    @Provides
    SearchViewModel provideSearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SearchViewModel> supplier = () -> new SearchViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SearchViewModel> factory = new ViewModelProviderFactory<>(SearchViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(SearchViewModel.class);
    }
}
