package com.se.framework.ui.main.lookup;

import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.se.framework.R;
import com.se.framework.data.DataManager;
import com.se.framework.ui.base.BaseViewModel;
import com.se.framework.utils.rx.SchedulerProvider;

public class LookupViewModel extends BaseViewModel<LookupCallback> {

    private final ObservableField<String> title;

    private final ObservableField<String> content;

    private final ObservableField<String> boardNameEng;

    private final ObservableField<String> name;

    private final ObservableField<String> date;

    public LookupViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        title = new ObservableField<>();
        content = new ObservableField<>();
        boardNameEng = new ObservableField<>();
        name = new ObservableField<>();
        date = new ObservableField<>();
    }

    public void fetchPost(){
        int postId = getDataManager().getCurrentPostId();
        getCompositeDisposable().add(getDataManager()
        .doLookupApiCall(postId)
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(lookupResponse -> {
                    boardNameEng.set(lookupResponse.getData().getBoardNameEng());
                    title.set(lookupResponse.getData().getPostContent().getTitle());

                    content.set(lookupResponse.getData().getPostContent().getText());
                    name.set(lookupResponse.getData().getName());
                    date.set(getCreateAt(lookupResponse.getData().getCreatedAt()));

                    getNavigator().showToast(lookupResponse.getMessage());
                }));
    }

    public void onCloseClick(){
        getNavigator().dismissDialog();
    }

    public ObservableField<String> getTitle(){
        return title;
    }

    public ObservableField<String> getContent(){
        return content;
    }

    public ObservableField<String> getBoardNameEng() { return boardNameEng; }

    public ObservableField<String> getName() { return name; }

    public ObservableField<String> getDate() { return date; }

    public void setTitle(String title){
        this.title.set(title);
    }

    public void setContent(String content){
        this.content.set(content);
    }

    public void setBoardNameEng(String boardNameEng) { this.boardNameEng.set(boardNameEng); }

    public void setName(String name) { this.name.set(name); }

    public void setDate(String date) { this.date.set(date); }

    public String getCreateAt(int[] create){
        String res = "";
        for(int i=0; i<5; i++){
            res += Integer.toString(create[i]);
            if(i<2){
                res += "-";
            } else if(i == 2){
                res += " ";
            } else if(i<4){
                res +=":";
            }
        }
        return res;
    }

}
