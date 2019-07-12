package com.arkainfoteck.smallbasket.model;

public class Itemsordermodel {

    String frasho;
    String orederitem_title;
    String kgs;
    String orderitems_price;
    String orderitems_quantity;
    String orderitems_image;

    public Itemsordermodel(String frasho, String orederitem_title, String kgs, String orderitems_price, String orderitems_quantity, String orderitems_image) {
        this.frasho = frasho;
        this.orederitem_title = orederitem_title;
        this.kgs = kgs;
        this.orderitems_price = orderitems_price;
        this.orderitems_quantity = orderitems_quantity;
        this.orderitems_image = orderitems_image;
    }

    public String getFrasho() {
        return frasho;
    }

    public void setFrasho(String frasho) {
        this.frasho = frasho;
    }

    public String getOrederitem_title() {
        return orederitem_title;
    }

    public void setOrederitem_title(String orederitem_title) {
        this.orederitem_title = orederitem_title;
    }

    public String getKgs() {
        return kgs;
    }

    public void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getOrderitems_price() {
        return orderitems_price;
    }

    public void setOrderitems_price(String orderitems_price) {
        this.orderitems_price = orderitems_price;
    }

    public String getOrderitems_quantity() {
        return orderitems_quantity;
    }

    public void setOrderitems_quantity(String orderitems_quantity) {
        this.orderitems_quantity = orderitems_quantity;
    }

    public String getOrderitems_image() {
        return orderitems_image;
    }

    public void setOrderitems_image(String orderitems_image) {
        this.orderitems_image = orderitems_image;
    }
}
