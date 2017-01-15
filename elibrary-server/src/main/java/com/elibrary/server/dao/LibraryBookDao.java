package com.elibrary.server.dao;

import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.utils.LibraryException;

import java.util.List;

public interface LibraryBookDao {

    /**
     * Persist BookEntity into database
     *
     * @param entity object
     * @throws LibraryException;
     */
    void addBook(BookEntity entity) throws LibraryException;


    /**
     * Delete book from database based on primary key.
     *
     * @param bookId the id of the book
     * @throws LibraryException;
     */
    void deleteBook(Long bookId) throws LibraryException;

    /**
     * Update book based on primary key bookId.
     *
     * @param bookId the book id
     * @param entity object
     * @throws LibraryException;
     */
    void updateBook(Long bookId, BookEntity entity) throws LibraryException;

    /**
     * Retrieves book from database
     *
     * @param bookId the book id
     * @return a book entity object
     * @throws LibraryException;
     */
    BookEntity retrieveBookById(Long bookId) throws LibraryException;

    /**
     * Retrieve book details informations
     *
     * @param bookId the book id
     * @return a list of book details
     * @throws LibraryException;
     */
    List retrieveBookDetails(Long bookId) throws LibraryException;

    /**
     * Retrieve author books based on authorName (firstName + lastName)
     *
     * @param authorName the book author name
     * @return a list of books
     * @throws LibraryException
     */
    List retrieveAuthorBooks(String authorName) throws LibraryException;

    /**
     * Search books from database based on ISBN/Title/Author Name
     *
     * @param searchTerm the search value
     * @param size       the size of the array list
     * @return a list of books
     * @throws LibraryException
     */
    List searchBook(String searchTerm, Long size) throws LibraryException;

    List<BookEntity> retrieveAllBooks() throws LibraryException;
}
