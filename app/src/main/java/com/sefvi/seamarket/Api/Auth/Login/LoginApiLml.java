package com.sefvi.seamarket.Api.Auth.Login;

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

public class LoginApiLml extends BaseRetrofitIml {
    LoginApi loginApi;
    Retrofit retrofit = getRetrofit();

    public void  LoginApi(String phone, String password, final AuthInterface authInterface){
        String TAG = LoginApiLml.class.getSimpleName();
        loginApi= retrofit.create(LoginApi.class);
        Call<ResponseBody> call = loginApi.loginAccount(phone, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            AccountModell accountModell = new AccountModell();
                            accountModell.setUuid(jsonObject.getString("uuid"));
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
