package com.elibrary.server.service;


import com.elibrary.common.dto.Book;
import com.elibrary.common.dto.BookDetail;
import com.elibrary.server.utils.LibraryException;

import java.util.List;

/**
 * Service interface that handles specific library operations
 */
public interface LibraryService {
    /**
     * Create a new book.
     *
     * @param book the dto object
     * @throws LibraryException
     */
    void createBook(Book book) throws LibraryException;

    /**
     * Delete book from database based on primary key bookId.
     *
     * @param bookId the id of the book
     * @throws LibraryException
     */
    void deleteBook(Long bookId) throws LibraryException;


    /**
     * Update existing book from database.
     *
     * @param bookId the id of the book
     * @param book   the dto object
     * @throws LibraryException
     */
    void updateBook(Long bookId, Book book) throws LibraryException;

    /**
     * Fetch book based on bookId.
     *
     * @param bookId the id of the book
     * @return book dto object
     * @throws LibraryException
     */
    Book fetchBookById(Long bookId) throws LibraryException;


    /**
     * Fetch details information's for a book
     *
     * @param bookId the id of the book
     * @return a list of book details
     * @throws LibraryException
     */
    List<BookDetail> fetchBookDetails(Long bookId) throws LibraryException;

    /**
     * Fetch author books based on author name
     *
     * @param authorName the book author
     * @return a list of books
     * @throws LibraryException
     */
    List<Book> fetchAuthorBooks(String authorName) throws LibraryException;

    /**
     * Search book based on ISBN/Title/Author Name
     *
     * @param searchTerm the value to search
     * @param size       the size of the returned list
     * @return a list of books otherwise empty list
     * @throws LibraryException if any
     */
    List<Book> searchBook(String searchTerm, Long size) throws LibraryException;

    /**
     * Fetch all books from the database
     *
     * @return a list of book otherwise empty list
     * @throws LibraryException if any
     */
    List<Book> fetchAllBooks() throws LibraryException;

    ;
}
