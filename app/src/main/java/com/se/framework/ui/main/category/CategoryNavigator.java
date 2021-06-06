package com.se.framework.ui.main.category;

import com.se.framework.data.model.api.CategoryResponse;

import java.util.List;

public interface CategoryNavigator {
    void handleError(Throwable throwable);

    void updateCategory(List<CategoryResponse.Category> categoryList);
}
