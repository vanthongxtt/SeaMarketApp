package com.sefvi.seamarket.Api;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CreateAccountApi {
    @FormUrlEncoded
    @POST(Constant.URL_BASE_CREATE_ACCOUNT)
    Call<ResponseBody> CreateAccount(@Field ("fullname") String fullname, @Field("dateOfBirth") String dateOfBirth, @Field("gender") String gender, @Field("avatar") String avatar, @Field("address") String address);
}
