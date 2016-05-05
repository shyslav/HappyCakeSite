package com.sukhaniuk.models;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class dish {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private int amount;
    private double price;
    private String image;
    private String readyORnot;

    public dish(int id, int categoryId, String name, String description, int amount, double price, String image, String readyORnot) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.readyORnot = readyORnot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getReadyORnot() {
        return readyORnot;
    }

    public void setReadyORnot(String readyORnot) {
        this.readyORnot = readyORnot;
    }
}
