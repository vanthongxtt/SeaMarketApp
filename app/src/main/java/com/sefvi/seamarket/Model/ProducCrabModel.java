package com.sefvi.seamarket.Model;

public class ProducCrabModel {
    public  String Name;
    public  Integer Price;
    public Integer Img;

    public ProducCrabModel(String name, Integer price, Integer img) {
        Name = name;
        Price = price;
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public Integer getPrice() {
        return Price;
    }


    public Integer getImg() {
        return Img;
    }

}
