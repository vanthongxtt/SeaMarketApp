package com.sefvi.seamarket.Api.Auth.Register;

import android.util.Log;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterApiLml extends BaseRetrofitIml {
    RegisterApi registerApi;
    Retrofit retrofit = getRetrofit();
    public void RegisterApi(String phone, String password, String rePassword, String fullName, String yearOfBirth, String gender, String avatar, String address, final AuthInterface authInterface){
        registerApi = retrofit.create(RegisterApi.class);
        Call<ResponseBody> call = registerApi.registerAccount(phone,password, rePassword, fullName, yearOfBirth, gender, avatar,address);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            AccountModell accountModell = new AccountModell();
                            accountModell.setUuid(jsonObject.getString("uid"));
                            accountModell.setToken(jsonObject.getString("access_token"));
                            authInterface.getDataSuccess(accountModell);
                        } else {
                            JSONObject object = new JSONObject(jsonObject.getString("errors"));
                            authInterface.getDataError(object.getString("error_text"));
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
                Log.d("onFail", t.toString());
            }
        });
    }
}
