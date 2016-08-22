package com.sukhaniuk.models;

import java.io.InputStream;

/**
 * Created by shyshkin_vlad on 22.04.16.
 */
public class Dish {
    private int id;
    private int categoryId;
    private String name;
    private String description;
    private int amount;
    private double price;
    private byte [] image;
    private String readyORnot;
    private String sell;

    public Dish(int id, int categoryId, String name, String description, int amount, double price, byte[] image, String readyORnot, String sell) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.image = image;
        this.readyORnot = readyORnot;
        this.sell = sell;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getReadyORnot() {
        return readyORnot;
    }

    public void setReadyORnot(String readyORnot) {
        this.readyORnot = readyORnot;
    }
}
