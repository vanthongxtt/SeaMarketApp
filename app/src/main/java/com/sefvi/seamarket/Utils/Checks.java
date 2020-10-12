package com.sefvi.seamarket.Utils;

public class Checks {

    public static boolean PhoneCheck(String phone){
        String regexStr = "^[0-9]{10}$";
        if (phone.isEmpty() || !phone.matches(regexStr)){
            return false;
        }
        return  true;

    }
}
