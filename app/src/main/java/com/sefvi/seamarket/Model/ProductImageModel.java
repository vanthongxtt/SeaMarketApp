package com.sefvi.seamarket.Model;

public class ProductImageModel {
    Integer id;
    String nameImage;

    public ProductImageModel() {
    }

    public ProductImageModel(Integer id, String nameImage) {
        this.id = id;
        this.nameImage = nameImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }
}
