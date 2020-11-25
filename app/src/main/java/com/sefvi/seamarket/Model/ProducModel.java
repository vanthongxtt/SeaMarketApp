package com.sefvi.seamarket.Model;

public class ProducModel {
    public  String Name;
    public  Integer Price;
    public String describe;
    public Integer Img;

    public ProducModel(String name, Integer price, String describe, Integer img) {
        Name = name;
        Price = price;
        this.describe = describe;
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getImg() {
        return Img;
    }

    public void setImg(Integer img) {
        Img = img;
    }
}
