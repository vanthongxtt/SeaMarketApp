package com.sefvi.seamarket.Api.Auth.Login;
import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
public interface LoginApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_AUTH)
    Call<ResponseBody> loginAccount (@Field("phone") String phone, @Field("password") String password);
}
