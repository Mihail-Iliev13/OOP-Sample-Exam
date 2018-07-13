package com.minkov.services.base;

import com.minkov.models.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> listAllCategories();

    Category findCategoryById(int id);

    Category getOrCreate(String categoryName);
}
