package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.AccountModell;

public interface CreateAccountListenner {
    void getDataSuccess(AccountModell accountModel);
    void getMessageError(Exception e);
    void getDataArraySuccess();
    void getDataError(AccountModell accountModel);

}
