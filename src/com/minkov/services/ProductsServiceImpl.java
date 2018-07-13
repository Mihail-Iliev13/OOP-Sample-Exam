package com.minkov.services;

import com.minkov.models.Category;
import com.minkov.models.Product;
import com.minkov.services.base.ProductsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductsServiceImpl implements ProductsService {
    private static List<Product> products;
    private static int productId;

    static {
        products = new ArrayList<>();
        productId = 1;
    }

    @Override
    public List<Product> listAllProducts() {
        return products;
    }

    @Override
    public List<Product> search(Predicate<Product> predicate) {
      return products.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {

      return products.stream()
              .filter(product -> product.listOfCategories().contains(category))
              .collect(Collectors.toList());
    }

    @Override
    public void addCategoryToProduct(Product product, Category category) {
        product.listOfCategories().add(category);
    }

    @Override
    public Product findById(int id) {

        for (Product product : products) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product createProduct(String productName, double productPrice, List<Category> categories) {
        Product product = new Product(productName, productPrice, categories, productId);
        productId++;
        products.add(product);
        return product;
    }
}
