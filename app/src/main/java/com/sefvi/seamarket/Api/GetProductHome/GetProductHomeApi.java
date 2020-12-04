package com.sefvi.seamarket.Api.GetProductHome;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetProductHomeApi {
    @GET(Constant.URL_BASE_GET_PRODUCT_HOME)
    Call<ResponseBody> GetProductHome(@Path("token") String token, @Query("limit") Integer limit);
}
