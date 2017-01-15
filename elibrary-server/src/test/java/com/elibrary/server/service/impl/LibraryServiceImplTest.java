package com.elibrary.server.service.impl;

import com.elibrary.common.dto.BookDetail;
import com.elibrary.common.dto.Book;
import com.elibrary.server.dao.LibraryBookDao;
import com.elibrary.server.dao.entity.AuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceImplTest {

    private static final Long BOOK_ID = 11223344L;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private LibraryBookDao libraryBookDao;

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Test
    public void testCreateBookInvalidBook() throws Exception {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.INVALID_BOOK.getMessage());

        libraryService.createBook(null);
    }

    @Test
    public void testDeleteBookNoBookId() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_BOOK_ID.getMessage());

        libraryService.deleteBook(null);
    }

    @Test
    public void testUpdateBookNoBookId() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_BOOK_ID.getMessage());

        libraryService.updateBook(null, new Book());
    }

    @Test
    public void testUpdateBookInvalidBook() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.INVALID_BOOK.getMessage());

        libraryService.updateBook(1L, null);
    }

    @Test
    public void testFetchBookByIdNoBookId() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_BOOK_ID.getMessage());

        libraryService.fetchBookById(null);
    }

    @Test
    public void testFetchBookDetailsNoBookId() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_BOOK_ID.getMessage());

        libraryService.fetchBookDetails(null);
    }

    @Test
    public void testFetchAuthorBooksNoAuthor() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_AUTHOR.getMessage());

        libraryService.fetchAuthorBooks(null);
    }

    @Test
    public void testSearchBookNoSearchTerm() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_SEARCH_TERM.getMessage());

        libraryService.searchBook("", null);
    }

    @Test
    public void testFetchBookById() throws LibraryException {
        BookEntity expected = new BookEntity();
        expected.setId(1234);
        expected.setIsbn("ISBN");
        expected.setTitle("Title");
        when(libraryBookDao.retrieveBookById(BOOK_ID)).thenReturn(expected);

        Book actual = libraryService.fetchBookById(BOOK_ID);

        verify(libraryBookDao).retrieveBookById(BOOK_ID);

        assertEquals(expected.getIsbn(), actual.getIsbn());
        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    public void testFetchBookDetails() throws LibraryException {
        List<BookDetailEntity> expected = new ArrayList<>();
        BookDetailEntity detail = new BookDetailEntity();
        detail.setFormat("format");
        detail.setSubject("Subject");
        detail.setDescription("Description");
        detail.setLanguage("Language");
        detail.setPublicationDate(new Date());
        expected.add(detail);

        when(libraryBookDao.retrieveBookDetails(BOOK_ID)).thenReturn(expected);

        List<BookDetail> actual = libraryService.fetchBookDetails(BOOK_ID);

        verify(libraryBookDao).retrieveBookDetails(BOOK_ID);

        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getFormat(), actual.get(0).getFormat());
        assertEquals(expected.get(0).getSubject(), actual.get(0).getSubject());
        assertEquals(expected.get(0).getLanguage(), actual.get(0).getLanguage());
    }

    @Test
    public void testFetchAuthorBooks() throws LibraryException {
        String authorName = "authorName";
        List<AuthorEntity> expected = new ArrayList<>();
        AuthorEntity author = new AuthorEntity();
        BookEntity book = new BookEntity();
        book.setId(BOOK_ID.intValue());
        book.setTitle("Title");
        book.setIsbn("ISBN");
        author.setBook(book);
        author.setFirstName("First Name");
        author.setLastName("Last Name");
        expected.add(author);


        when(libraryBookDao.retrieveAuthorBooks(authorName)).thenReturn(expected);

        List<Book> actual = libraryService.fetchAuthorBooks(authorName);

        verify(libraryBookDao).retrieveAuthorBooks(authorName);

        assertEquals(book.getTitle(), actual.get(0).getTitle());
        assertEquals(book.getIsbn(), actual.get(0).getIsbn());
    }

    @Test
    public void testSearchBook() throws LibraryException {
        String searchTerm = "searchTerm";
        Long size = 12L;
        List<BookEntity> expected = new ArrayList<>();
        BookEntity book = new BookEntity();
        book.setId(1);
        book.setTitle("Title");
        book.setIsbn("Isbn");
        book.setAuthorEntities(new HashSet<>());
        book.setDetailEntities(new HashSet<>());
        expected.add(book);


        when(libraryBookDao.searchBook(searchTerm, size)).thenReturn(expected);

        List<Book> actual = libraryService.searchBook(searchTerm, size);

        verify(libraryBookDao).searchBook(searchTerm, size);
        assertEquals(expected.get(0).getId().toString(), actual.get(0).getId().toString());
        assertEquals(expected.get(0).getIsbn(), actual.get(0).getIsbn());
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
    }
}