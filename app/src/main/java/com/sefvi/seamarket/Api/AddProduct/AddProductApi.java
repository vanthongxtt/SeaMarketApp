package com.sefvi.seamarket.Api.AddProduct;

import com.sefvi.seamarket.Config.Constant;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AddProductApi {
    @POST(Constant.URL_BASE_ADD_PRODUCT)
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ResponseBody> AddProduct(@Path("token") String token, @Body RequestBody files);
}
