package com.sefvi.seamarket.Utils;

public class Checks {

    public static boolean PhoneCheck(String phone) {
        String regexStr = "^[0-9]{10}$";
        if (phone.isEmpty() || !phone.matches(regexStr)) {
            return false;
        }
        return true;
    }

    public static boolean CheckEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-] + (?:\\\\.[a-zA-Z0-9_+&*-]\n" +
                "+ )*@(?:[a-zA-Z0-9-]+\\\\.) + [a-zA-Z]{2,7}$";
        if (email.isEmpty() || !email.matches(emailRegex)) {
            return false;
        }
        return true;
    }
    public static boolean CheckPassword(String pass){

        if (pass.isEmpty() || pass.length() <6){
            return false;
        }
        return true;
    }
}
