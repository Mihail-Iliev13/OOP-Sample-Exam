package com.minkov.models;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private double price;
    private int id;
    private List<Category> categories;

    public Product(String name, double price, List<Category> categories, int id){
        setName(name);
        setPrice(price);
        setCategories(categories);
        setId(id);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public List<Category> listOfCategories() {
        return categories;
    }

    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
