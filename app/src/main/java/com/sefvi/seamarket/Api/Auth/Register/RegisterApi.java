package com.sefvi.seamarket.Api.Auth.Register;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface
RegisterApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_CREATE_ACCOUNT)
    Call<ResponseBody> registerAccount(@Field("phone") String phone, @Field("password") String password, @Field("password_confirm") String password_confirm, @Field("fullname") String fullname, @Field("dateOfBirth") String dateOfBirth, @Field("gender") String gender, @Field("avatar") String avatar, @Field("address") String address);
}
