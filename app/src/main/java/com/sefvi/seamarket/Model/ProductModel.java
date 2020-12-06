package com.sefvi.seamarket.Model;

import java.util.List;

public class ProductModel {

    private Integer id;
    private  Integer idType;
    private String uuid;
    private String name;
    private String description;
    private Integer price;
    private String origin;
    private String unit;
    private String image;
    private List<Object> images;
    private Integer warehouseNumber;
    private Integer sellNumber;
    private Integer isActive;

    public ProductModel() {
    }


    public ProductModel(Integer id, Integer idType, String uuid, String name, String description, Integer price, String origin, String unit, String image, Integer warehouseNumber, Integer sellNumber, Integer isActive) {
        this.id = id;
        this.idType = idType;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.origin = origin;
        this.unit = unit;
        this.image = image;
        this.warehouseNumber = warehouseNumber;
        this.sellNumber = sellNumber;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(Integer warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public Integer getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(Integer sellNumber) {
        this.sellNumber = sellNumber;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }
}
