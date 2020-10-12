package com.sefvi.seamarket.Utils;

import android.util.Patterns;

public class Checks {
    public static boolean PhoneCheck(String phone){
        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()){
            return false;
        }
        return true;
    }
}
