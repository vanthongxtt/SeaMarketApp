package com.sefvi.seamarket.Api.GetOrder;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetOrderApi {
    @GET(Constant.URL_BASE_GET_ORDER)
    Call<ResponseBody> GetOrder(@Path("token") String token);
}
