package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.AccountModel;

public interface CreateAccountListenner {
    void getDataSuccess(AccountModel accountModel);
    void getMessageError(Exception e);
    void getDataArraySuccess();
    void getDataError(AccountModel accountModel);
}
