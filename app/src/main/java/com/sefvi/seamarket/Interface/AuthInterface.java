package com.sefvi.seamarket.Interface;

import com.sefvi.seamarket.Model.AccountModell;

public interface AuthInterface {
    void getDataSuccess(AccountModell accountModell);
    void getDataError(String err);
}
