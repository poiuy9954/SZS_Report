package com.szskimjinho.szs.Utils;

import java.util.Base64;

public class Base64Utils {

    public static String encode(String str){
        return Base64.getEncoder().encodeToString(str.getBytes());
    }
    public static String decode(String str){
        return new String( Base64.getDecoder().decode(str));
    }
}
