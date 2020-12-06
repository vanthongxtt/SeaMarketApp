package com.sefvi.seamarket.Api.DetailProduct;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailProductApi {
    @GET(Constant.URL_BASE_GET_PRODUCT)
    Call<ResponseBody> GetProduct(@Path("token") String token, @Query("id") Integer id);
}
