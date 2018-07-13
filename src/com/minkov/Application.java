package com.minkov;

import com.minkov.commands.Command;
import com.minkov.commands.CommandType;
import com.minkov.commands.base.CommandParser;
import com.minkov.models.Category;
import com.minkov.models.Product;
import com.minkov.services.base.CategoriesService;
import com.minkov.services.base.ProductsService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Application {

    private final ProductsService productsService;
    private final CategoriesService categoriesService;
    private final CommandParser commandParser;

    public Application(ProductsService productsService,
                       CategoriesService categoriesService,
                       CommandParser commandParser) {
        this.productsService = productsService;
        this.categoriesService = categoriesService;
        this.commandParser = commandParser;
    }

    void run() {
        Scanner in = new Scanner(System.in);

        String commandString = in.nextLine();
        Command command = commandParser.parse(commandString);
        while (command.getType() != CommandType.EXIT) {

            switch (command.getType()) {
                case ADD_PRODUCT:
                    handleAddProduct(command.getArgs());
                    break;
                case LIST_PRODUCTS:
                    handleListProducts();
                    break;
                case ADD_CATEGORY_TO_PRODUCT:
                    handleAddCategoryToProduct(command.getArgs());
                    break;
                case FIND_PRODUCT_BY_ID:
                    handleFindProductById(command.getArgs());
                    break;
                case FILTER_PRODUCT_BY_NAME:
                    handleFilterProductByName(command.getArgs());
                    break;
                case LIST_PRODUCTS_BY_CATEGORY:
                    handleListProductsByCategory(command.getArgs());
                    break;
                default:
                    System.exit(0);
            }


            commandString = in.nextLine();
            command = commandParser.parse(commandString);
        }
    }

    private void handleFilterProductByName(List<String> args) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String productName = args.get(0);
        Predicate<Product> name = product -> product.getName().equals(productName);
        List<Product> products = productsService.search(name);
        products.forEach(product -> System.out.printf("(%d) %s, %s $%n",
                product.getId(), product.getName(), df.format(product.getPrice())));
    }

    private void handleFindProductById(List<String> args) {
        DecimalFormat df = new DecimalFormat("#0.00");
        int productId = Integer.parseInt(args.get(0));
        Product product = productsService.findById(productId);
        System.out.printf("%s: %s $%n", product.getName(), df.format(product.getPrice()));
    }

    private void handleAddCategoryToProduct(List<String> args) {
        int productId = Integer.parseInt(args.get(0));
        String categoryName = args.get(1);
        Category category = categoriesService.getOrCreate(categoryName);
        Product product = productsService.findById(productId);
        productsService.addCategoryToProduct(product, category);
        System.out.printf("%s added to %s%n", categoryName, product.getName());
    }

    private void handleListProducts() {
        String output = productsService.listAllProducts()
            .stream()
            .map(product -> String.format("%s (%d)", product.getName(), product.getId()))
            .collect(Collectors.joining(", "));

        System.out.println(output);
    }

    private void handleAddProduct(List<String> args) {
        String productName = args.get(0);
        double productPrice = Double.parseDouble(args.get(1));
        List<Category> categories = args.subList(2, args.size())
            .stream()
            .map(categoriesService::getOrCreate)
            .collect(Collectors.toList());

        Product product = productsService.createProduct(productName, productPrice, categories);
        System.out.printf("Product %s added with Id %d!%n", product.getName(), product.getId());
    }

    private void handleListProductsByCategory(List<String> args) {
        String categoryName = args.get(0);
        Category category = categoriesService.getOrCreate(categoryName);
        List<Product> products = productsService.getProductsByCategory(category);
        products.forEach(product -> System.out.printf("(%d) %s%n", product.getId(), product.getName()));
    }
}
