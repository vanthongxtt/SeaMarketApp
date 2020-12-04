package com.sefvi.seamarket.Interface;


import com.sefvi.seamarket.Model.ProductModel;

import org.json.JSONArray;

import java.util.ArrayList;

public interface ProductRandom {
    void getDataSuccess(ProductModel productModel);
    void getDataError(String err);

    void getDataSuccess(JSONArray list);
}
