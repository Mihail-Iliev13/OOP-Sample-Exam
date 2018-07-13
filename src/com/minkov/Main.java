package com.minkov;

import com.minkov.commands.CommandParserImpl;
import com.minkov.commands.base.CommandParser;
import com.minkov.services.CategoryServiceImpl;
import com.minkov.services.ProductsServiceImpl;
import com.minkov.services.base.CategoriesService;
import com.minkov.services.base.ProductsService;

public class Main {

    public static void main(String[] args) {
        ProductsService productsService = new ProductsServiceImpl();
        CategoriesService categoriesService = new CategoryServiceImpl();
        CommandParser commandParser = new CommandParserImpl();
        Application application = new Application(productsService, categoriesService, commandParser);
        application.run();
    }
}
