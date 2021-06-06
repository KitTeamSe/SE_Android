package com.se.framework.ui.main.search;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.se.framework.data.model.api.PostResponse;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.ui.main.post.PostItemViewModel;

public class SearchItemViewModel {
    public final ObservableField<String> title = new ObservableField<>();

    public final ObservableField<String> previewText = new ObservableField<>();

    public final ObservableField<String> createAt = new ObservableField<>();

    public final ObservableField<String> nickname = new ObservableField<>();

    public final ObservableField<String> numReply = new ObservableField<>();

    public final ObservableInt boardId = new ObservableInt();

    public final ObservableField<String> isNotice = new ObservableField<>();

    public final ObservableField<String> isSecret = new ObservableField<>();

    public final ObservableInt postId = new ObservableInt();

    public final ObservableInt views = new ObservableInt();

    private final SearchResponse.Data.PostListItem.Post mPost;

    public final SearchItemViewModel.SearchItemViewModelListener mListener;

    public SearchItemViewModel(SearchResponse.Data.PostListItem.Post post, SearchItemViewModelListener listener){
        this.mPost = post;
        this.mListener = listener;

        this.title.set(post.getTitle());
        this.previewText.set(post.getPreviewText());
        this.createAt.set(getCreateAt(post.getCreateAt()));
        this.nickname.set(post.getNickname());
        this.numReply.set(Integer.toString(post.getNumReply()));
        this.boardId.set(post.getBoardId());
        this.isNotice.set(post.getIsNotice());
        this.isSecret.set(post.getIsSecret());
        this.postId.set(post.getPostId());
        this.views.set(post.getViews());
    }

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

    public void onItemClick() {
        mListener.onItemClick(mPost);
    }

    public interface SearchItemViewModelListener {
        void onItemClick(SearchResponse.Data.PostListItem.Post post);
    }
}
