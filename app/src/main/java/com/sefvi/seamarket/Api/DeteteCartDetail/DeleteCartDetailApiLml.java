package com.sefvi.seamarket.Api.DeteteCartDetail;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.CartInterface;
import com.sefvi.seamarket.Model.AccountModell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeleteCartDetailApiLml extends BaseRetrofitIml {
    DeleteCartDetailApi deleteCartDetailApi;
    Retrofit retrofit = getRetrofit();
    public void DeleteCartDetailApi(String token, Integer id, final CartInterface cartInterface){
        deleteCartDetailApi = retrofit.create(DeleteCartDetailApi.class);
        Call<ResponseBody> call = deleteCartDetailApi.DeleteCartDetail(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            cartInterface.getDataSuccess(jsonObject.getString("message"));
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
