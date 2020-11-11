package com.sefvi.seamarket.Api;

import com.sefvi.seamarket.Interface.CreateAccountListenner;
import com.sefvi.seamarket.Model.AccountModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateAccountApiIml extends BaseRetrofitIml {
    CreateAccountApi createAccountApi;
    Retrofit retrofit = getRetrofit();
    public void CreateAccount(String fullname, String dateOfBirth, String gender, String avatar, String address, final CreateAccountListenner createAccountListenner){
        createAccountApi = retrofit.create(CreateAccountApi.class);
        Call<ResponseBody> call = createAccountApi.CreateAccount(fullname, dateOfBirth, gender, avatar, address);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try{
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        int status = jsonObject.getInt("success");
                        if (status == 200){
                            AccountModel accountModel = new AccountModel();
                            accountModel.setUuid(jsonObject.getInt("uuid"));
                            accountModel.setFullname(jsonObject.getString("fullname"));
                            accountModel.setDateOfBirth(jsonObject.getString("dateOfBirth"));
                            accountModel.setGender(jsonObject.getString("gender"));
                            accountModel.setAvatar(jsonObject.getString("avatar"));
                            accountModel.setAddress(jsonObject.getString("address"));
                            accountModel.setCreateAt(jsonObject.getString("createAt"));
                            accountModel.setUpdateAt(jsonObject.getString("updateAt"));
                            accountModel.setIsActive(jsonObject.getInt("isActive"));
                            createAccountListenner.getDataSuccess(accountModel);
                        } else {}
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                createAccountListenner.getMessageError(new Exception(t));
            }
        });
    }

}
