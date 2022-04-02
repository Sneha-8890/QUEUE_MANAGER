package com.sneha.sih_2022_project;

public class ItemModel {
    String prod_name, prod_url;
    long prod_price;

    public ItemModel(){}

    public ItemModel(String prod_name, long prod_price, String prod_url ) {
        this.prod_name = prod_name;
        this.prod_price = prod_price;
        this.prod_url = prod_url;

    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_url() {
        return prod_url;
    }

    public void setProd_url(String prod_url) {
        this.prod_url = prod_url;
    }

    public long getProd_price() {
        return prod_price;
    }

    public void setProd_price(long prod_price) {
        this.prod_price = prod_price;
    }
}