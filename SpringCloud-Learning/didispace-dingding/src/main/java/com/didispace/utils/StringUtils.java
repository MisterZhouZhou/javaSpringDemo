package com.didispace.utils;

public class StringUtils {

    public static boolean isEmpty(String string) {
        if(string != null && string.length() !=0 ){
            return false;
        }
        return true;
    }
}
