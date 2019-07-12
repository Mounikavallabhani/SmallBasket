package com.arkainfoteck.smallbasket.model;

public class CatagiresModel  {
    String catagires_id;
    String catagires_name;
    String catagires_image;

    public CatagiresModel(String catagires_id, String catagires_name, String catagires_image) {
        this.catagires_id = catagires_id;
        this.catagires_name = catagires_name;
        this.catagires_image = catagires_image;
    }

    public String getCatagires_id() {
        return catagires_id;
    }

    public void setCatagires_id(String catagires_id) {
        this.catagires_id = catagires_id;
    }

    public String getCatagires_name() {
        return catagires_name;
    }

    public void setCatagires_name(String catagires_name) {
        this.catagires_name = catagires_name;
    }

    public String getCatagires_image() {
        return catagires_image;
    }

    public void setCatagires_image(String catagires_image) {
        this.catagires_image = catagires_image;
    }
}
