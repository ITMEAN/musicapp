package com.minh.zingmp3.response;

import com.minh.zingmp3.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListCategoryResponse {
    private int total;
    private List<Category> categories;
}
