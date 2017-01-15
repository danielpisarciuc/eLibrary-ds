package com.elibrary.server.utils;

import com.elibrary.common.dto.Author;
import com.elibrary.common.dto.Book;
import com.elibrary.common.dto.BookDetail;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryValidationTest {

    @Test
    public void testIsBookValidFalse() throws Exception {
        assertFalse(LibraryValidation.isBookValid(null));
    }

    @Test
    public void testIsBookValidFalseNoAuthor() throws Exception {
        Book book = new Book();
        book.setIsbn("ISBN");
        book.setTitle("Title");
        List<BookDetail> details = new ArrayList<>();
        details.add(new BookDetail());
        book.setBookDetails(details);

        assertFalse(LibraryValidation.isBookValid(book));
    }

    @Test
    public void testIsBookValidFalseNoDetails() throws Exception {
        Book book = new Book();
        book.setIsbn("ISBN");
        book.setTitle("Title");
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        book.setBookAuthors(authors);

        assertFalse(LibraryValidation.isBookValid(book));
    }

    @Test
    public void testIsBookValidTrue() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(
                Author.builder()
                        .lastName("Mitrut")
                        .firstName("Ispirescu")
                        .build());

        List<BookDetail> details = new ArrayList<>();
        details.add(
                BookDetail.builder()
                        .format("PDF")
                        .description("No description")
                        .subject("No subject")
                        .language("EN")
                        .publicationDate(new Date())
                        .build());

        boolean bookValid = LibraryValidation.isBookValid(
                Book.builder()
                        .id(11L)
                        .isbn("ISBN")
                        .title("eLibrary")
                        .bookAuthors(authors)
                        .bookDetails(details)
                        .build());


        assertTrue(bookValid);
    }
}