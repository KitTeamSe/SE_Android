package com.se.framework.utils;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.se.framework.data.model.api.CategoryResponse;
import com.se.framework.data.model.api.PostResponse;
import com.se.framework.data.model.api.SearchResponse;
import com.se.framework.data.model.api.TagResponse;
import com.se.framework.ui.main.category.CategoryAdapter;
import com.se.framework.ui.main.mypage.TagAdapter;
import com.se.framework.ui.main.post.PostAdapter;
import com.se.framework.ui.main.post.PostItemViewModel;
import com.se.framework.ui.main.search.SearchAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("adapter")
    public static void addCategoryItems(RecyclerView recyclerView, List<CategoryResponse.Category> categorys) {
        CategoryAdapter adapter = (CategoryAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(categorys);
        }
    }

    @BindingAdapter("adapter")
    public static void addPostItems(RecyclerView recyclerView, List<PostResponse.Data.PostListItem.Post> postItems){
        PostAdapter adapter = (PostAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.clearItems();
            adapter.addItems(postItems);
        }
    }

    @BindingAdapter("adapter")
    public static void addTagItems(RecyclerView recyclerView, List<TagResponse.Data.Tag> tagItems){
        TagAdapter adapter = (TagAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.clearItems();
            adapter.addItems(tagItems);
        }
    }

    @BindingAdapter("adapter")
    public static void addSearchItems(RecyclerView recyclerView, List<SearchResponse.Data.PostListItem.Post> searchItems){
        SearchAdapter adapter = (SearchAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.clearItems();
            adapter.addItems(searchItems);
        }
    }

    @BindingAdapter(value = {"selectedValue", "selectedValueAttrChanged"}, requireAll = false)
    public static void bindSpinnerData(AppCompatSpinner pAppCompatSpinner, String newSelectedValue, final InverseBindingListener newTextAttrChanged) {
        pAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                newTextAttrChanged.onChange();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        if (newSelectedValue != null) {
            int pos = ((ArrayAdapter<String>) pAppCompatSpinner.getAdapter()).getPosition(newSelectedValue);
            pAppCompatSpinner.setSelection(pos, true);
        }
    }
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static String captureSelectedValue(AppCompatSpinner pAppCompatSpinner) {
        return (String) pAppCompatSpinner.getSelectedItem();
    }



    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
