package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.ProductModel;

import org.json.JSONArray;

public interface CartInterface {
    void getDataSuccess(String mess);
    void getDataError(String err);
    void getDataSuccess(JSONArray list);
}
