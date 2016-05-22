package com.elibrary.server.utils;

import com.elibrary.common.dto.BookDto;
import com.elibrary.common.utils.LibraryUtil;

/**
 * Validate library objects and variables.
 */
public class LibraryValidation {

    public static boolean isBookValid(BookDto bookDto) {
        if (bookDto == null || LibraryUtil.isNullOrEmpty(bookDto.getIsbn() , bookDto.getTitle()) || !bookDto.hasAuthors() || !bookDto.hasDetails()){
            return false;
        }

        return true;
    }
}
