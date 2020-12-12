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
    public static final String URL_BASE_GET_PRODUCTS = "api/{token}/get-products";
    public static final String URL_BASE_GET_PRODUCT = "api/{token}/get-product";
    public static final String URL_BASE_GET_SEARCH_PRODUCTS = "api/{token}/get-search-products";
    public static final String URL_BASE_GET_USER_DATA = "api/{token}/get-user-data";
    public static final String URL_BASE_ADD_CART = "api/{token}/add-cart";
    public static final String URL_BASE_GET_COUNT_NOTI_CART = "api/{token}/get-count-noti-cart";
    public static final String URL_BASE_GET_CART = "api/{token}/get-cart";
    public static final String URL_BASE_DELETE_CART_DETAIL = "api/{token}/delete-cart-detail";
    public static final String URL_BASE_GET_BILL_ORDER = "api/{token}/get-bill-order";
    public static final String URL_BASE_CONFRIM_BILL_ORDER_CART = "api/{token}/confrim-bill-order-cart";
    public static final String URL_BASE_GET_ORDER = "api/{token}/get-order";
    public static final String URL_BASE_ADD_PRODUCT = "api/{token}/add-product";
    public static final String URL_BASE_UPDATE_USER = "api/{token}/update-user";
}
