package com.sefvi.seamarket.Api.GetUserData;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetUserDataApi {
    @GET(Constant.URL_BASE_GET_USER_DATA)
    Call<ResponseBody> GetUserData(@Path("token") String token);
}
