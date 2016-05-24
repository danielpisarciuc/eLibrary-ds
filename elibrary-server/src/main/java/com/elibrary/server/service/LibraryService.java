package com.elibrary.server.service;


import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.server.utils.LibraryException;

import java.util.List;

public interface LibraryService {
    /**
     * Create a new book.
     *
     * @param bookDto
     * @throws LibraryException
     */
    void createBook(BookDto bookDto) throws LibraryException;

    /**
     * Delete book from database based on primary key bookId.
     *
     * @param bookId
     * @throws LibraryException
     */
    void deleteBook(Long bookId) throws LibraryException;


    /**
     * Update existing book from database.
     *
     * @param bookId
     * @param bookDto
     * @throws LibraryException
     */
    void updateBook(Long bookId, BookDto bookDto) throws LibraryException;

    /**
     * Fetch book based on bookId.
     *
     * @param bookId
     * @return book dto object
     * @throws LibraryException
     */
    BookDto fetchBookById(Long bookId) throws LibraryException;


    /**
     * Fetch details information's for a book
     *
     * @param bookId
     * @return a list of book details
     * @throws LibraryException
     */
    List<BookDetailDto> fetchBookDetails(Long bookId) throws LibraryException;

    /**
     * Fetch author books based on author name
     *
     * @param authorName
     * @return a list of books
     * @throws LibraryException
     */
    List<BookDto> fetchAuthorBooks(String authorName) throws LibraryException;

    /**
     * Search book based on ISBN/Title/Author Name
     *
     * @param searchTerm
     * @param size
     * @return a list of books
     * @throws LibraryException
     */
    List<BookDto> searchBook(String searchTerm, Long size) throws LibraryException;
}
