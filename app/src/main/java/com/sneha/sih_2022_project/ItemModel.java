package com.sneha.sih_2022_project;

public class ItemModel {
    private String prod_url, prod_name, prod_price;
    private int qty;

    ItemModel(){}

    public ItemModel(String prod_name, String prod_price, String prod_url) {
        this.prod_url = prod_url;
        this.prod_name = prod_name;
        this.prod_price = prod_price;
    }

    public String getProd_url() {
        return prod_url;
    }

    public void setProd_url(String prod_id) {
        this.prod_url = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_price() {
        return prod_price;
    }

    public void setProd_price(String prod_price) {
        this.prod_price = prod_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
