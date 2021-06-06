package com.se.framework.ui.main.post;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.ui.main.MainActivity;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class PostViewModel extends BaseViewModel<PostNavigator> {
    private final MutableLiveData<List<PostResponse.Data.PostListItem.Post>> postListLiveData;

    public PostViewModel(DataManager dataManager, SchedulerProvider schedulerProvider){
        super(dataManager, schedulerProvider);
        postListLiveData = new MutableLiveData<>();
        fetchPosts();
    }

    public void fetchPosts(){
        setIsLoading(true);

        getCompositeDisposable().add(getDataManager()
                .doPostApiCall(getDataManager().getCurrentCategoryId())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(postResponse -> {
                    Log.e("POSTRESPONSE", postResponse.getData().toString());
                    if(postResponse != null && postResponse.getData() != null){
                        postListLiveData.setValue(postResponse.getData().getPostListItem().getContent());
                        setIsLoading(false);
                    }
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<PostResponse.Data.PostListItem.Post>> getPostListLiveData(){
        return postListLiveData;
    }

    public void setCurrentPostId(int postId){
        getDataManager().setCurrentPostId(postId);
        getNavigator().showLookupDialog();
    }

    public void onClickFloat(){
        getNavigator().write();
    }
}
