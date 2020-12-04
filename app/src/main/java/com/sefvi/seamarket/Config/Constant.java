package com.sefvi.seamarket.Config;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Constant {

    public static final String URL_BASE = "https://api.sefvi.com/SeaMarketApi/V1/";
    public static final String URL_BASE_AUTH = "api/auth";
    public static final String URL_BASE_CREATE_ACCOUNT = "api/create-account";
    public static final String URL_BASE_GET_PRODUCT_RANDOM = "api/{token}/get-product-random";
    public static final String URL_BASE_GET_PRODUCT_HOME = "api/{token}/get-product-home";
}
