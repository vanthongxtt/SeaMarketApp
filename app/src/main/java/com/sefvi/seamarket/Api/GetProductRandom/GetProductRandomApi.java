package com.sefvi.seamarket.Api.GetProductRandom;


import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GetProductRandomApi {
    @GET(Constant.URL_BASE_GET_PRODUCT_RANDOM)
    Call<ResponseBody> GetProductRandom(@Path("token") String token);
}
