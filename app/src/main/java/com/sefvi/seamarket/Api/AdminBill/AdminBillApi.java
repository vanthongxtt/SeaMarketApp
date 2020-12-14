package com.sefvi.seamarket.Api.AdminBill;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AdminBillApi {
    @GET(Constant.URL_BASE_GET_ORDER_LIST_ADMIN)
    Call<ResponseBody> AdminBill(@Path("token") String token);
}
