package com.minh.zingmp3.services.category;

import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.request.category.AddPlaylistToCategoryRequest;
import com.minh.zingmp3.request.category.CreateCategoryRequest;
import com.minh.zingmp3.response.ListCategoryResponse;

public interface CategoryService {
    Category createCategory(CreateCategoryRequest request) throws  Exception;
    Category getCategoryById(long  id) throws Exception;
    ListCategoryResponse getAllCategory(int page, int size) throws Exception;
    boolean addPlaylistToCategory(AddPlaylistToCategoryRequest request) throws Exception;
}
