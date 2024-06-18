package com.szskimjinho.Utils;

public class StringUtils {

    public static boolean isNone(String str){
        return str == null || str.replace(" ","").isEmpty();
    }
    public static boolean isNotNone(String str){
        return !isNone(str);
    }
}
