package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

public interface DetailProduct {
    void getDataSuccess(ProductModel productModel);
    void getDataError(String err);

    void getDataSuccess(JSONObject list);
}
