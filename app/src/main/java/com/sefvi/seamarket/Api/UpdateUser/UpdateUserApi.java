package com.sefvi.seamarket.Api.UpdateUser;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UpdateUserApi {
    @POST(Constant.URL_BASE_UPDATE_USER)
    Call<ResponseBody> UpdateUser(@Path("token") String token, @Body RequestBody file);
}
