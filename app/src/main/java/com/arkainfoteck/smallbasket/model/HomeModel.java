package com.arkainfoteck.smallbasket.model;

public class HomeModel {
    String id;
    String shopimage;
    String shopname;
    String shoptitle;
    String product_type;

    public HomeModel(String id, String shopimage, String shopname, String shoptitle, String product_type) {
        this.id = id;
        this.shopimage = shopimage;
        this.shopname = shopname;
        this.shoptitle = shoptitle;
        this.product_type = product_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getShopimage() {
        return shopimage;
    }

    public void setShopimage(String shopimage) {
        this.shopimage = shopimage;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShoptitle() {
        return shoptitle;
    }

    public void setShoptitle(String shoptitle) {
        this.shoptitle = shoptitle;
    }
}
