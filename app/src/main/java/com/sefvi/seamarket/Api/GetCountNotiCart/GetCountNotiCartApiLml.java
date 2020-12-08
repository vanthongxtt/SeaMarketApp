package com.sefvi.seamarket.Api.GetCountNotiCart;

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

public class GetCountNotiCartApiLml extends BaseRetrofitIml {
    GetCountNotiCartApi getCountNotiCartApi;
    Retrofit retrofit = getRetrofit();
    public void GetCountNotiCartApi(String token, final CartInterface cartInterface){
        getCountNotiCartApi = retrofit.create(GetCountNotiCartApi.class);
        Call<ResponseBody> call = getCountNotiCartApi.GetCountNotiCart(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            cartInterface.getDataSuccess(jsonObject.getString("data"));
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
