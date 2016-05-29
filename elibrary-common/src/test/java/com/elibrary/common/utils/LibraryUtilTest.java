package com.elibrary.common.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryUtilTest {

    @Test
    public void testIsNullOrEmpty() {
        assertTrue(LibraryUtil.isNullOrEmpty("", null));
        assertTrue(LibraryUtil.isNullOrEmpty("", ""));

        assertTrue(LibraryUtil.isNullOrEmpty("1", ""));
        assertTrue(LibraryUtil.isNullOrEmpty("", "2"));

        assertFalse(LibraryUtil.isNullOrEmpty("1", "2"));
        assertFalse(LibraryUtil.isNullOrEmpty("null"));

//        assertTrue(LibraryUtil.isNullOrEmpty(null));

    }
}