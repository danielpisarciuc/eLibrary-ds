package com.elibrary.common.utils;


import java.util.Arrays;

public class LibraryUtil {

    public static boolean isNullOrEmpty(String ...value) {
        if(value == null){
            return true;
        }

        String EMPTY_STRING = "";

        return Arrays.stream(value)
                .filter(val -> val == null || EMPTY_STRING.equalsIgnoreCase(val.trim()))
                .findFirst()
                .isPresent();
    }
}
