package com.sneha.sih_2022_project;

public class ItemModel {
    private String name;
    private int qty, price, image;

    public ItemModel(String name, int qty, int price, int image) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
