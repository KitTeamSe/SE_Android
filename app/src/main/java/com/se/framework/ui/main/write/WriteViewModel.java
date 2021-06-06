package com.se.framework.ui.main.write;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.se.framework.data.DataManager;
import com.se.framework.data.model.api.TagResponse;
import com.se.framework.data.model.api.WriteRequest;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

public class WriteViewModel extends BaseViewModel<WriteCallback> {

    private final MutableLiveData<List<String>> items;

    private final ObservableField<String> itemPosition;

    private final MutableLiveData<Boolean> isChecked;

    public WriteViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        items = new MutableLiveData<>();
        itemPosition = new ObservableField<>();
        isChecked = new MutableLiveData<>();

        itemPosition.set("0");
        isChecked.setValue(false);

        fetchTags();
    }


    public void write(String title, String content, int tagId){
        int boardId = getDataManager().getCurrentCategoryId();
        String check;
        if (isChecked.getValue() == true){
            check = "SECRET";
        } else{
            check = "NORMAL";
        }
        WriteRequest request = new WriteRequest(title, content, tagId, boardId, check);
        getCompositeDisposable().add(getDataManager().doWriteApiCall(request)
        .doOnSuccess( response -> Log.e("RESPONSE", response.getMessage()))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(response ->{
            getNavigator().showToast(response.getMessage());
            onCloseClick();
        }, throwable -> {
            getNavigator().handleError(throwable);
        }));
    }

    public void onWriteClick(){
        getNavigator().write();
    }

    public boolean isValid(String title, String content) {
        // validate id and password
        if (TextUtils.isEmpty(title)) {
            return false;
        }
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        return true;
    }

    public void fetchTags(){
        getCompositeDisposable().add(getDataManager().doTagApiCall()
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(tagResponse -> {
            List<String> temp = new ArrayList<>();
            for (TagResponse.Data.Tag tag: tagResponse.getData().getContent()) {
                temp.add(tag.getText());
            }
            items.setValue(temp);
        }, throwable -> {
            getNavigator().handleError(throwable);
        }));
    }

    public MutableLiveData<Boolean> getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(){
        if (isChecked.getValue()){
            isChecked.setValue(false);
        } else{
            isChecked.setValue(true);
        }
    }

    public void onCloseClick(){
        getNavigator().dismissDialog();
    }

    public ObservableField<String> getItemPosition(){
        return itemPosition;
    }

    public List<String> getItems(){
        return items.getValue();
    }
}
