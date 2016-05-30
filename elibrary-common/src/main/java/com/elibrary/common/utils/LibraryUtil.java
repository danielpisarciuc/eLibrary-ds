package com.elibrary.common.utils;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

public class LibraryUtil {

    public static boolean isNullOrEmpty(String ...value) {
        if(ArrayUtils.isEmpty(value)){
            return true;
        }
        String EMPTY_STRING = "";

        return Arrays.asList(value)
                .stream()
                .map(val -> val == null ||  EMPTY_STRING.equalsIgnoreCase(val.trim()))
                .filter(val -> val)
                .findFirst()
                .orElse(false);
    }
}
