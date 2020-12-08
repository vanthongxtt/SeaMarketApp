package com.sefvi.seamarket.Api.AddCart;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AddCartApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_ADD_CART)
    Call<ResponseBody> AddCart(@Path("token") String token,@Field("tokenRandom") String tokenCart ,@Field("idProduct") Integer idProduct, @Field("quantily") Integer quantily);

}
