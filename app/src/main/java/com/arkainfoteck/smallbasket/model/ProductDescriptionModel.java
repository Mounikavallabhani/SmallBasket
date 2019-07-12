package com.arkainfoteck.smallbasket.model;

public class ProductDescriptionModel {
    String productname;
    String productimage;
    String productcost;

    public ProductDescriptionModel(String productname, String productimage, String productcost) {
        this.productname = productname;
        this.productimage = productimage;
        this.productcost = productcost;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductcost() {
        return productcost;
    }

    public void setProductcost(String productcost) {
        this.productcost = productcost;
    }
}
