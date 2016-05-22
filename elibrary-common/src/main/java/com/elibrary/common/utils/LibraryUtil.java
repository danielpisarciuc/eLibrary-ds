package com.elibrary.common.utils;


import java.util.Arrays;

public class LibraryUtil {

    public static boolean isNullOrEmpty(String... value) {
        return Arrays.stream(value)
                .filter(val -> val == null || val.isEmpty())
                .findFirst()
                .isPresent();
    }
}
