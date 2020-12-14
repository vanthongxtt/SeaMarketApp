package com.sefvi.seamarket.Api.AdminBill;

import android.util.Log;

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

public class AdminBillApiLml extends BaseRetrofitIml {
    AdminBillApi adminBillApi;
    Retrofit retrofit = getRetrofit();
    public void BillAdminApi(String token, final CartInterface cartInterface){
        adminBillApi = retrofit.create(AdminBillApi.class);
        Call<ResponseBody> call = adminBillApi.AdminBill(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            cartInterface.getDataSuccess(jsonObject.getJSONArray("data"));
                        }else {
                            Log.d("dddd==", jsonObject.getString("api_status"));
                            cartInterface.getDataError(jsonObject.getString("message"));
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
