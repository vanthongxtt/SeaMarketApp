package com.sefvi.seamarket.Api.GetProductHome;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
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

public class GetProductHomeApiLml extends BaseRetrofitIml {
    GetProductHomeApi getProductHomeApi;
    Retrofit retrofit = getRetrofit();
    public  void  GetProductHomeApi(String token, Integer limit, final ProductRandom productRandom){
        getProductHomeApi = retrofit.create(GetProductHomeApi.class);
        Call<ResponseBody> call = getProductHomeApi.GetProductHome(token, limit);
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

            }
        });

    }
}
