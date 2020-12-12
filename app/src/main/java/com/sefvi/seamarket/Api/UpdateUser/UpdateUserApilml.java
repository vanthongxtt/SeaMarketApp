package com.sefvi.seamarket.Api.UpdateUser;

import android.util.Log;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.AuthInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateUserApilml extends BaseRetrofitIml {
    UpdateUserApi updateUserApi;
    Retrofit retrofit = getRetrofit();
    public void UpdateUserApi(String token, RequestBody file, final AuthInterface authInterface){
        updateUserApi = retrofit.create(UpdateUserApi.class);
        Call<ResponseBody> call = updateUserApi.UpdateUser(token, file);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getInt("api_status") == 200){
                            authInterface.getDataError("OK");
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
                authInterface.getDataError(t.getMessage());
            }
        });
    }
}
