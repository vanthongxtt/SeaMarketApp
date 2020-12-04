package com.sefvi.seamarket.Model;

public class Home_ComboHot {
    public  String Ten;
    public  Integer Gia;
    public Integer Hinh;

    public Home_ComboHot() {

    }

    public String getTen() {
        return Ten;
    }

    public Integer getGia() {
        return Gia;
    }

    public Integer getHinh() {
        return Hinh;
    }

    public Home_ComboHot(String ten, Integer gia, Integer hinh) {
        Ten = ten;
        Gia = gia;
        Hinh = hinh;
    }
}
