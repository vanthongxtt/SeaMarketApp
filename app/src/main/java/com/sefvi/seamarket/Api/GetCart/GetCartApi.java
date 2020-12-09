package com.sefvi.seamarket.Api.GetCart;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetCartApi {
    @GET(Constant.URL_BASE_GET_CART)
    Call<ResponseBody> GetCartList(@Path("token") String token);
}
