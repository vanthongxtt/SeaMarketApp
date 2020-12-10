package com.sefvi.seamarket.Api.ConfrimBillOrderCart;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConfrimBillOrderCartApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_CONFRIM_BILL_ORDER_CART)
    Call<ResponseBody> ConfrimBillOrderCart(@Path("token") String token, @Field("idCart") Integer idCart, @Field("status") Integer status);
}
