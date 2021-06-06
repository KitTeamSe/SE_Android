package com.se.framework.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.se.framework.R;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.databinding.ActivityMainBinding;
import com.se.framework.di.component.ActivityComponent;
import com.se.framework.ui.base.BaseActivity;
import com.se.framework.ui.login.LoginActivity;
import com.se.framework.ui.main.category.CategoryFragment;
import com.se.framework.ui.main.lookup.LookupDialog;
import com.se.framework.ui.main.mypage.MypageFragment;
import com.se.framework.ui.main.post.PostFragment;
import com.se.framework.ui.main.search.SearchFragment;
import com.se.framework.ui.main.write.WriteDialog;
import com.se.framework.ui.splash.SplashActivity;

import org.apache.http.util.EncodingUtils;

import java.net.URL;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator {
    @Inject
    MainViewModel mainViewModel;

    private ActivityMainBinding mActivityMainBinding;

    private Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mViewModel.setNavigator(this);

        setBottomNav();
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

    public void setBottomNav() {
        bottomNavigationView = mActivityMainBinding.bottomNavi;
        bottomNavigationView.setSelected(false);
        bottomNavigationView.setSelectedItemId(R.id.action_home);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fragmentLayout, PostFragment.newInstance(), PostFragment.TAG)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_category:
                        showCategoryFragment();
                        break;
                    case R.id.action_search:
                        showSearchFragment();
                        break;
                    case R.id.action_home:
                        delAllFrag();

                        getSupportFragmentManager()
                                .beginTransaction()
                                .disallowAddToBackStack()
                                .add(R.id.fragmentLayout, PostFragment.newInstance(), PostFragment.TAG)
                                .commit();
                        mViewModel.setTitle("SE board");
                        break;
                    case R.id.action_mypage:
                        showMypageFragment();
                        break;
//                    case R.id.action_notifications:
//                        fragmentManager.beginTransaction().hide(activeFragment).show(notificationiFragment).commit();
//                        activeFragment = categoryFragment;
//                        break;
                    default:
                        showCategoryFragment();
                        break;
                }
                return true;
            }
        });
    }

    public void showCategoryFragment() {
        delAllFrag();
//        onFragmentDetached(PostFragment.TAG);
//        onFragmentDetached(SearchFragment.TAG);
//        onFragmentDetached(MypageFragment.TAG);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fragmentLayout, CategoryFragment.newInstance(), CategoryFragment.TAG)
                .commit();

        mViewModel.setTitle("MENU");
    }

    public void showHomeFragment(String name) {
        delAllFrag();
//        onFragmentDetached(CategoryFragment.TAG);
//        onFragmentDetached(SearchFragment.TAG);
//        onFragmentDetached(MypageFragment.TAG);

        bottomNavigationView.setSelectedItemId(R.id.action_home);

        mViewModel.setTitle(name);
    }

    public void showMypageFragment() {
        delAllFrag();
//        onFragmentDetached(CategoryFragment.TAG);
//        onFragmentDetached(PostFragment.TAG);
//        onFragmentDetached(SearchFragment.TAG);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fragmentLayout, MypageFragment.newInstance(), MypageFragment.TAG)
                .commit();

        mViewModel.setTitle("MYPAGE");
    }

    public void showSearchFragment() {
        delAllFrag();
//        onFragmentDetached(MypageFragment.TAG);
//        onFragmentDetached(CategoryFragment.TAG);
//        onFragmentDetached(PostFragment.TAG);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.fragmentLayout, SearchFragment.newInstance(), SearchFragment.TAG)
                .commit();

        mViewModel.setTitle("SEARCH");
    }

    public void showLookupDialog() {
        LookupDialog.newInstance().show(getSupportFragmentManager());
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    public void showWriteDialog() {
        WriteDialog.newInstance().show(getSupportFragmentManager());
    }

    public void delAllFrag(){
        onFragmentDetached(MypageFragment.TAG);
        onFragmentDetached(CategoryFragment.TAG);
        onFragmentDetached(PostFragment.TAG);
        onFragmentDetached(SearchFragment.TAG);
    }
}
