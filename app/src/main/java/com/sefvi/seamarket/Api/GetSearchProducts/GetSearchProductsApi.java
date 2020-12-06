package com.sefvi.seamarket.Api.GetSearchProducts;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetSearchProductsApi {
    @GET(Constant.URL_BASE_GET_SEARCH_PRODUCTS)
    Call<ResponseBody> GetSearchProducts(@Path("token") String token, @Query("keyword") String keyword);
}
