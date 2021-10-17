package com.trade.project.item.application;

import com.trade.project.item.domain.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PUBLIC)

public class CategoryResponse {
    private Long id;
    private String categoryName;
    private int level;
    private int parent;

    public static CategoryResponse of(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getCategoryName(),
                category.getLevel(),
                category.getParent()
        );
    }

    public static List<CategoryResponse> listOf(List<Category> categorys) {
        List<CategoryResponse> categoryResponses = new ArrayList<>();

        for (Category c : categorys) {
            categoryResponses.add(of(c));
        }
        return categoryResponses;
    }
}
