package com.sefvi.seamarket.Api.GetCountNotiCart;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetCountNotiCartApi {
    @GET(Constant.URL_BASE_GET_COUNT_NOTI_CART)
    Call<ResponseBody> GetCountNotiCart(@Path("token") String token);
}
