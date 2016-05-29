package com.elibrary.server.utils;

import com.elibrary.common.dto.BookAuthorDto;
import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryValidationTest {

    @Test
    public void testIsBookValidFalse() throws Exception {
        assertFalse(LibraryValidation.isBookValid(null));
    }

    @Test
    public void testIsBookValidFalseNoAuthor() throws Exception {
        BookDto book = new BookDto();
        book.setIsbn("ISBN");
        book.setTitle("Title");
        List<BookDetailDto> details = new ArrayList<>();
        details.add(new BookDetailDto());
        book.setBookDetails(details);

        assertFalse(LibraryValidation.isBookValid(book));
    }

    @Test
    public void testIsBookValidFalseNoDetails() throws Exception {
        BookDto book = new BookDto();
        book.setIsbn("ISBN");
        book.setTitle("Title");
        List<BookAuthorDto> authors = new ArrayList<>();
        authors.add(new BookAuthorDto());
        book.setBookAuthors(authors);

        assertFalse(LibraryValidation.isBookValid(book));
    }

    @Test
    public void testIsBookValidTrue() throws Exception {
        BookDto book = new BookDto();
        book.setIsbn("ISBN");
        book.setTitle("Title");
        List<BookAuthorDto> authors = new ArrayList<>();
        authors.add(new BookAuthorDto());
        List<BookDetailDto> details = new ArrayList<>();
        details.add(new BookDetailDto());
        book.setBookAuthors(authors);
        book.setBookDetails(details);

        assertTrue(LibraryValidation.isBookValid(book));
    }
}