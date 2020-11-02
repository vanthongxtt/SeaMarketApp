package com.sefvi.seamarket.API;

import com.sefvi.seamarket.Interface.CreateAccountListenner;
import com.sefvi.seamarket.Model.CreateAccountModel;

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
                            CreateAccountModel createAccountModel = new CreateAccountModel();
                            createAccountModel.setUuid(jsonObject.getInt("uuid"));
                            createAccountModel.setFullname(jsonObject.getString("fullname"));
                            createAccountModel.setDateOfBirth(jsonObject.getString("dateOfBirth"));
                            createAccountModel.setGender(jsonObject.getString("gender"));
                            createAccountModel.setAvatar(jsonObject.getString("avatar"));
                            createAccountModel.setAddress(jsonObject.getString("address"));
                            createAccountListenner.getDataSuccess(createAccountModel);
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
