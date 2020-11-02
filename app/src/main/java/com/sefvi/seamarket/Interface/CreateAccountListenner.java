package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.CreateAccountModel;

public interface CreateAccountListenner {
    void getDataSuccess(CreateAccountModel createAccountModel);
    void getMessageError(Exception e);
    void getDataArraySuccess();
    void getDataError(CreateAccountModel createAccountModel);
}
