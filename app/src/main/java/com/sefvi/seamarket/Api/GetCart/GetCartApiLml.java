package com.sefvi.seamarket.Api.GetCart;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.CartInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetCartApiLml extends BaseRetrofitIml {
    GetCartApi getCartApi;
    Retrofit retrofit = getRetrofit();
    public void GetCartList(String token, final CartInterface cartInterface){
        getCartApi = retrofit.create(GetCartApi.class);
        Call<ResponseBody> call = getCartApi.GetCartList(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            cartInterface.getDataSuccess(jsonObject.getJSONArray("data"));
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
                cartInterface.getDataError(t.getMessage());
            }
        });
    }
}
