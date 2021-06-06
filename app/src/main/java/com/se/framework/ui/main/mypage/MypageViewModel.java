package com.se.framework.ui.main.mypage;

import android.text.TextUtils;
import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.MyTagAddRequest;
import com.se.framework.data.model.api.MyTagResponse;
import com.se.framework.data.model.api.TagResponse;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class MypageViewModel extends BaseViewModel<MypageNavigator> {

    private final MutableLiveData<List<TagResponse.Data.Tag>> tagListLiveData;

    private final MutableLiveData<List<MyTagResponse.Data>> myTagListLiveData;

    private final MutableLiveData<String> userTag;

    private final MutableLiveData<String> tags;

    public MypageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        tagListLiveData = new MutableLiveData<>();
        myTagListLiveData = new MutableLiveData<>();

        userTag = new MutableLiveData<>();
        tags = new MutableLiveData<>();

        fetchTags();
        fetchUserTags();
    }

    public void fetchTags(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().doTagApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(tagResponse -> {
                    tagListLiveData.setValue(tagResponse.getData().getContent());
                    setIsLoading(false);
                }, throwable -> {
                    getNavigator().handleError(throwable);
                    setIsLoading(false);
                }));
    }

    public void fetchUserTags(){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager().doMyTagApiCall()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(myTagResponse -> {
            if(myTagResponse.getStatus() == 200){
                myTagListLiveData.setValue(myTagResponse.getData());

                String tmp = "";
                int cnt = 0;
                for (MyTagResponse.Data data: myTagResponse.getData()) {
                    tmp += Integer.toString(data.getTagId());
                    if(cnt++ != myTagResponse.getData().size()){
                        tmp += " ";
                    }
                }
                tags.setValue(tmp);
            }
            setIsLoading(false);
            getNavigator().showToast(myTagResponse.getMessage());
        }, throwable -> {
            getNavigator().handleError(throwable);
            setIsLoading(false);
        }));
    }

    public void add(int id){
        setIsLoading(true);

        ArrayList<Integer> tmp = new ArrayList<>();
        for (MyTagResponse.Data data: myTagListLiveData.getValue()) {
            tmp.add(data.getTagId());
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for (TagResponse.Data.Tag data : tagListLiveData.getValue()){
            temp.add(data.getTagId());
        }
        if(temp.contains(id)){
            if(!tmp.contains(id)){
                getCompositeDisposable().add(getDataManager()
                        .doMyTagAddApiCall(new MyTagAddRequest(id)).doOnSuccess(myTagAddResponse -> myTagAddResponse.getStatus())
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(myTagAddResponse -> {
                            setIsLoading(false);
                            getNavigator().showToast(myTagAddResponse.getMessage());
                            fetchUserTags();
                        }));

            } else {
                getNavigator().showToast("존재하는 태그 입력");
                setIsLoading(false);
            }
        } else {
            getNavigator().showToast("없는 태그 입력");
            setIsLoading(false);
        }
    }

    public void onAddClick(){
        getNavigator().add();
    }

    public void del(int id){
        setIsLoading(true);

        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for (MyTagResponse.Data data: myTagListLiveData.getValue()) {
            tmp.add(data.getTagId());
            temp.add(data.getTagListeningId());
        }
        if(tmp.contains(id)){
            getCompositeDisposable().add(getDataManager()
                        .doMyTagDelApiCall(temp.get(tmp.indexOf(id)))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(myTagDelResponse -> {
                            setIsLoading(false);
                            getNavigator().showToast(myTagDelResponse.getMessage());
                            fetchUserTags();
                        }));
        } else{
            getNavigator().showToast("존재하지 않는 태그 입력");
            setIsLoading(false);
        }
    }

    public void onDeleteClick(){
        getNavigator().del();
    }

    public LiveData<List<TagResponse.Data.Tag>> getTagListLiveData() { return tagListLiveData; }

    public MutableLiveData<String> getUserTag() {
        return userTag;
    }

    public MutableLiveData<String> getTags() {
        return tags;
    }

    public boolean isIdValid(String id) {
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        return true;
    }
}
