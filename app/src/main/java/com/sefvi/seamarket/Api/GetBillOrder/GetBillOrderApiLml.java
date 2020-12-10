package com.sefvi.seamarket.Api.GetBillOrder;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.CartInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetBillOrderApiLml extends BaseRetrofitIml {
    GetBillOrderApi getBillOrderApi;
    Retrofit retrofit = getRetrofit();
    public void GetBillOrderApi(String token, Integer id, final CartInterface cartInterface){
        getBillOrderApi = retrofit.create(GetBillOrderApi.class);
        Call<ResponseBody> call = getBillOrderApi.GetBillOrder(token, id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
//                            JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                            cartInterface.getDataSuccess(jsonObject.getJSONObject("data"));
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
