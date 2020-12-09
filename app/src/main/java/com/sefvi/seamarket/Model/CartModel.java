package com.sefvi.seamarket.Model;

import org.json.JSONObject;

import java.util.List;

public class CartModel {
    private Integer id;
    private Integer idCartDetail;
    private Integer productId;
    private Integer quantily;
    private Integer status;
    private String name;
    private Integer price;
    private Integer sumPrice;
    private String image;

    public CartModel() {
    }

    public CartModel(Integer id, Integer idCartDetail, Integer productId, Integer quantily, Integer status, String name, Integer price, Integer sumPrice, String image) {
        this.id = id;
        this.idCartDetail = idCartDetail;
        this.productId = productId;
        this.quantily = quantily;
        this.status = status;
        this.name = name;
        this.price = price;
        this.sumPrice = sumPrice;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCartDetail() {
        return idCartDetail;
    }

    public void setIdCartDetail(Integer idCartDetail) {
        this.idCartDetail = idCartDetail;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantily() {
        return quantily;
    }

    public void setQuantily(Integer quantily) {
        this.quantily = quantily;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Integer sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
