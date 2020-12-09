package com.sefvi.seamarket.Api.DeteteCartDetail;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DeleteCartDetailApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_DELETE_CART_DETAIL)
    Call<ResponseBody> DeleteCartDetail(@Path("token") String token, @Field("id") Integer id);
}
