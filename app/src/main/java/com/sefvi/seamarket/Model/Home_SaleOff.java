package com.sefvi.seamarket.Model;

public class Home_SaleOff {
    public  String Ten;
    public  Integer Gia;
    public Integer Hinh;

    public String getTen() {
        return Ten;
    }

    public Integer getGia() {
        return Gia;
    }

    public Integer getHinh() {
        return Hinh;
    }

    public Home_SaleOff(String ten, Integer gia, Integer hinh) {
        Ten = ten;
        Gia = gia;
        Hinh = hinh;
    }
}