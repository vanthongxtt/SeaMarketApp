package com.sefvi.seamarket.Api.GetProductRandom;

import android.util.Log;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.ProductRandom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetProductRandomLml extends BaseRetrofitIml {
    GetProductRandomApi getProductRandomApi;
    Retrofit retrofit = getRetrofit();
    public void  GetProductRandomApi(String token, final ProductRandom productRandom){
        getProductRandomApi = retrofit.create(GetProductRandomApi.class);
        Call<ResponseBody> call = getProductRandomApi.GetProductRandom(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                           JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                           productRandom.getDataSuccess(jsonArray);
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
                Log.d("errCall", t.toString());
            }
        });
    }
}
