package com.sefvi.seamarket.Api.GetProducts;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetProductsApi {
    @GET(Constant.URL_BASE_GET_PRODUCTS)
    Call<ResponseBody> GetProducts(@Path("token") String token, @Query("idType") Integer idType);
}
