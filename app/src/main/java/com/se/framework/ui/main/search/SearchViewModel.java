package com.se.framework.ui.main.search;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.SearchRequest;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class SearchViewModel extends BaseViewModel<SearchNavigator> {
    private final MutableLiveData<List<SearchResponse.Data.PostListItem.Post>> searchListLiveData;

    public SearchViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        searchListLiveData = new MutableLiveData<>();
    }

    public void search(String keyword) {
        setIsLoading(true);

        int boardId = getDataManager().getCurrentCategoryId();
        SearchRequest request = new SearchRequest(boardId, keyword, "TITLE_TEXT", "DESC", 1, 50);

        getCompositeDisposable().add(getDataManager().doSearchApiCall(request)
                .doOnSuccess(searchResponse -> Log.e("RES", searchResponse.getMessage()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(searchResponse -> {
                            if (searchResponse.getStatus() == 200) {
                                searchListLiveData.setValue(searchResponse.getData().getPostListItem().getContent());
                            }
                            if(searchResponse.getData().getPostListItem().getContent().size() == 0){
                                getNavigator().showToast("해당 키워드가 없엉");
                            } else{
                                getNavigator().showToast(searchResponse.getMessage());
                            }
                            setIsLoading(false);
                        }, throwable -> {
                            getNavigator().handleError(throwable);
                            getNavigator().showToast("조회 실패");
                            setIsLoading(false);
                        }
                )
        );
    }

    public void onSearchClick(){
        getNavigator().search();
    }

    public void setCurrentPostId(int postId){
        getDataManager().setCurrentPostId(postId);
        getNavigator().showLookupDialog();
    }

    public boolean isValid(String query) {
        if (TextUtils.isEmpty(query)) {
            return false;
        }
        return true;
    }

    public MutableLiveData<List<SearchResponse.Data.PostListItem.Post>> getSearchListLiveData() {
        return searchListLiveData;
    }
}
