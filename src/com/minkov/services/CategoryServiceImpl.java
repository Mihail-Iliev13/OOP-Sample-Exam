package com.minkov.services;

import com.minkov.models.Category;
import com.minkov.services.base.CategoriesService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoriesService {
    private static List<Category> allCategories;
    private static int categoryId;


    static {
     allCategories = new ArrayList<>();
     categoryId = 1;
    }

    @Override
    public List<Category> listAllCategories() {
        return allCategories;
    }

    @Override
    public Category findCategoryById(int id) {
        for (Category category : allCategories) {
            if(category.getId() == id){
                return category;
            }
        }
        return null;
    }

    @Override
    public Category getOrCreate(String categoryName) {
        for (Category category : allCategories) {
            if(category.getName().equals(categoryName)){
                return category;
            }
        }
        Category category = new Category(categoryName, categoryId);
        categoryId++;
        allCategories.add(category);
        return category;
    }
}
