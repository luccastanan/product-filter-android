package com.codetouch.filtrodeprodutos.models;

import java.util.Arrays;
import java.util.List;

public class Product {

    private String productName;
    private String description;
    private String[] targetMarket;
    private String[] stack;


    public Product(){}

    public Product(String productName, String description, String[] targetMarket, String[] stack) {
        this.productName = productName;
        this.description = description;
        this.targetMarket = targetMarket;
        this.stack = stack;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTargetMarket() {
        return targetMarket;
    }

    public void setTargetMarket(String[] targetMarket) {
        this.targetMarket = targetMarket;
    }

    public String[] getStack() {
        return stack;
    }

    public void setStack(String[] stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", targetMarket=" + Arrays.toString(targetMarket) +
                ", stack=" + Arrays.toString(stack) +
                '}';
    }
}
