package com.elibrary.server.utils;

import com.elibrary.common.dto.BookAuthorDto;
import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.common.utils.LibraryUtil;

/**
 * Validate library objects and variables.
 */
public class LibraryValidation {

    public static boolean isBookValid(BookDto bookDto) {
        if (bookDto == null) {
            return false;
        }

        if (!bookDto.hasAuthors() && !bookDto.hasDetails()) {
            return false;
        }

        for (BookAuthorDto dto : bookDto.getBookAuthors()) {
            if (LibraryUtil.isNullOrEmpty(dto.getFirstName(), dto.getLastName())) {
                return false;
            }
        }

        for (BookDetailDto dto : bookDto.getBookDetails()) {
            if (LibraryUtil.isNullOrEmpty(dto.getLanguage(), dto.getSubject(), dto.getFormat(), dto.getDescription()) || dto.getPublicationDate() == null) {
                return false;
            }
        }


        return !LibraryUtil.isNullOrEmpty(bookDto.getIsbn(), bookDto.getTitle());
    }
}
