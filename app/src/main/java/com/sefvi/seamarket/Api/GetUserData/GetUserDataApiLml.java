package com.sefvi.seamarket.Api.GetUserData;

import com.sefvi.seamarket.Api.BaseRetrofitIml;
import com.sefvi.seamarket.Interface.AuthInterface;
import com.sefvi.seamarket.Model.AccountModell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetUserDataApiLml extends BaseRetrofitIml {
    GetUserDataApi getUserDataApi;
    Retrofit retrofit = getRetrofit();
    public void GetUserDataApi(String token, final AuthInterface authInterface){
        getUserDataApi = retrofit.create(GetUserDataApi.class);
        Call<ResponseBody> call = getUserDataApi.GetUserData(token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("api_status");
                        if (status == 200) {
                            JSONObject jsonObjects = jsonObject.getJSONObject("data");
                            AccountModell accountModell = new AccountModell();
                            accountModell.setUuid(jsonObjects.getString("uid"));
                            accountModell.setFullname(jsonObjects.getString("fullName"));
                            accountModell.setPhone(jsonObjects.getString("phone"));
                            accountModell.setAvatar(jsonObjects.getString("avatar"));
                            accountModell.setDateOfBirth(jsonObjects.getString("dateOfBirth"));
                            accountModell.setGender(jsonObjects.getString("gender"));
                            accountModell.setAddress(jsonObjects.getString("address"));
                            accountModell.setIsAdmin(jsonObjects.getInt("admin"));
                            authInterface.getDataSuccess(accountModell);
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
