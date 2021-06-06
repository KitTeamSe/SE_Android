package com.se.framework.ui.main.category;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.se.framework.data.model.api.CategoryResponse;

public class CategoryItemViewModel {

    public final ObservableInt menuId = new ObservableInt();
    public final ObservableField<String> nameEng = new ObservableField<>();
    public final ObservableInt menuOrder = new ObservableInt();
    public final ObservableField<String> menuType = new ObservableField<>();
    public final ObservableField<String> url = new ObservableField<>();

    public final CategoryItemViewModelListener mListener;

    private final CategoryResponse.Category mCategory;

    public CategoryItemViewModel(CategoryResponse.Category category, CategoryItemViewModelListener listener) {
        mCategory = category;
        mListener = listener;

        this.menuId.set(category.getMenuId());
        this.nameEng.set(category.getNameEng());
        this.menuOrder.set(category.getMenuOrder());
        this.menuType.set(category.getMenuType());
        this.url.set(category.getUrl());
    }

    public void onItemClick() { mListener.onItemClick(mCategory); }

    public interface CategoryItemViewModelListener{
        void onItemClick(CategoryResponse.Category mCategory);
    }
}
