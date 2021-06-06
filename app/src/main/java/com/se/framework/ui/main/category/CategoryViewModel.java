package com.se.framework.ui.main.category;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class CategoryViewModel extends BaseViewModel<CategoryNavigator> {
    private final MutableLiveData<List<CategoryResponse.Category>> categoryItemsLiveData;

    public CategoryViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        categoryItemsLiveData = new MutableLiveData<>();

        fetchCategorys();
    }

    public void fetchCategorys(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doCategoryApiCall()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(categoryResponse -> {
            if(categoryResponse != null && categoryResponse.getData() != null){
                categoryItemsLiveData.setValue(categoryResponse.getData());
            }
            setIsLoading(false);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleError(throwable);
        }));
    }

    public void setCurrentCategory(int currentCategoryId){
        getDataManager().setCurrentCategoryId(currentCategoryId);
    }

    public LiveData<List<CategoryResponse.Category>> getCategoryListLiveData() { return categoryItemsLiveData; }
}
