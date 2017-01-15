package com.elibrary.server.utils;

import com.elibrary.common.dto.Author;
import com.elibrary.common.dto.Book;
import com.elibrary.common.dto.BookDetail;
import com.elibrary.common.utils.LibraryUtil;

/**
 * Validate library objects and variables.
 */
public class LibraryValidation {

    public static boolean isBookValid(Book bookDto) {
        if (bookDto == null) {
            return false;
        }

        if (!bookDto.hasAuthors() && !bookDto.hasDetails()) {
            return false;
        }

        for (Author dto : bookDto.getBookAuthors()) {
            if (LibraryUtil.isNullOrEmpty(dto.getFirstName(), dto.getLastName())) {
                return false;
            }
        }

        for (BookDetail dto : bookDto.getBookDetails()) {
            if (LibraryUtil.isNullOrEmpty(dto.getLanguage(), dto.getSubject(), dto.getFormat(), dto.getDescription()) || dto.getPublicationDate() == null) {
                return false;
            }
        }


        return !LibraryUtil.isNullOrEmpty(bookDto.getIsbn(), bookDto.getTitle());
    }
}
