package com.sefvi.seamarket.Api.DetailProduct;

import android.util.Log;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.DetailProduct;
import com.sefvi.seamarket.Interface.ProductRandom;
import com.sefvi.seamarket.Model.ProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailProductApiLml extends BaseRetrofitIml {
    DetailProductApi detailProductApi;
    Retrofit retrofit = getRetrofit();
    public void DetailProductApi(String token, Integer id, final DetailProduct detailProduct){
        detailProductApi = retrofit.create(DetailProductApi.class);
        Call<ResponseBody> call = detailProductApi.GetProduct(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            JSONObject jsonObjects = jsonObject.getJSONObject("data");

                            detailProduct.getDataSuccess(jsonObjects);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    detailProduct.getDataError(t.getMessage());
            }
        });
    }
}
