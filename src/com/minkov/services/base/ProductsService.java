package com.minkov.services.base;

import com.minkov.models.Category;
import com.minkov.models.Product;

import java.util.List;
import java.util.function.Predicate;

public interface ProductsService {
    List<Product> listAllProducts();

    List<Product> search(Predicate<Product> predicate);

    List<Product> getProductsByCategory(Category category);

    void addCategoryToProduct(Product product, Category category);

    Product findById(int id);

    Product createProduct(String productName, double productPrice, List<Category> categories);
}
