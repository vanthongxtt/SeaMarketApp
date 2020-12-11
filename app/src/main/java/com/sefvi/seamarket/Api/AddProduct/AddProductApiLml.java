package com.sefvi.seamarket.Api.AddProduct;

import android.util.Log;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.ProductRandom;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddProductApiLml extends BaseRetrofitIml {
    AddProductApi addProductApi;
    Retrofit retrofit = getRetrofit();
    public void AddProductApi(String token, RequestBody image, final ProductRandom productRandom){
        addProductApi = retrofit.create(AddProductApi.class);
        Call<ResponseBody> call = addProductApi.AddProduct(token, image);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d("11th12", jsonObject.toString());
                        productRandom.getDataError(jsonObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                productRandom.getDataError(t.toString());
            }
        });
    }
}
