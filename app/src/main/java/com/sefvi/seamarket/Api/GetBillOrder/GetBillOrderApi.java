package com.sefvi.seamarket.Api.GetBillOrder;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetBillOrderApi {
    @GET(Constant.URL_BASE_GET_BILL_ORDER)
    Call<ResponseBody> GetBillOrder(@Path("token") String token, @Query("id") Integer id);
}
