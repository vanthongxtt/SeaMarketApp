package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.ProductModel;

import org.json.JSONArray;
import org.json.JSONObject;

public interface CartInterface {
    void getDataSuccess(String mess);
    void getDataError(String err);
    void getDataSuccess(JSONArray list);
    void getDataSuccess(JSONObject jsonObject);
}
