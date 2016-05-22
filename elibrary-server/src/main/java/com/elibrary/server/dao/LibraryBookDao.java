package com.elibrary.server.dao;

import com.elibrary.common.dto.BookDto;
import com.elibrary.server.dao.entity.BookAuthorEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.utils.LibraryException;

import java.util.List;

public interface LibraryBookDao {

    /**
     * Persist BookEntity into database
     *
     * @param bookEntity
     */
    void addBook(BookEntity bookEntity) throws LibraryException;


    /**
     * Delete book from database based on primary key.
     *
     * @param bookId
     * @throws LibraryException;
     */
    void deleteBook(Long bookId) throws LibraryException;

    /**
     * Update book based on primary key bookId.
     *
     * @param bookId
     * @param bookEntity
     * @throws LibraryException;
     */
    void updateBook(Long bookId, BookEntity bookEntity) throws LibraryException;

    /**
     * Retrieves book from database
     *
     * @param bookId
     * @return a book entity object
     * @throws LibraryException;
     */
    BookEntity retrieveBookById(Long bookId) throws LibraryException;

    /**
     * Retrieve book details informations
     *
     * @param bookId
     * @return a list of book details
     * @throws LibraryException;
     */
    List retrieveBookDetails(Long bookId) throws LibraryException;

    /**
     * Retrieve author books based on authorName
     *
     * @param authorName
     * @return a list of books
     * @throws LibraryException
     */
    List<BookAuthorEntity> retrieveAuthorBooks(String authorName) throws LibraryException;
}
