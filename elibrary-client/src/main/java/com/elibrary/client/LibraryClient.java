package com.elibrary.client;

import com.elibrary.common.dto.BookDto;

import javax.ws.rs.core.Response;

public interface LibraryClient {

    /**
     * Check if application is running.
     *
     * @return if application is alive return response status 200 with an info message
     */
    Response applicationInfo();

    /**
     * Retrieve Library reference data based on type.
     *
     * @param type
     */
    Response referenceData(String type);

    /**
     * Create an new book.
     *
     * @param bookDto
     * @return if success return status 201 otherwise 304
     */
    Response createBook(BookDto bookDto);


    /**
     * Delete book based on bookId
     *
     * @param bookId
     * @return if success return status 201 otherwise 304
     */
    Response deleteBook(Long bookId);

    /**
     * Update an existing book based on bookId
     *
     * @param bookId
     * @param bookDto
     * @return status 201 otherwise 304
     */
    Response updateBook(Long bookId, BookDto bookDto);

    /**
     * Fetch book information's based on bookId
     *
     * @param bookId
     * @return book info otherwise 204
     */
    Response fetchBookById(Long bookId);

    /**
     * Fetch book details based on bookId
     * @param bookId
     * @return book details otherwise 204
     */
    Response fetchBookDetails(Long bookId);

    /**
     * Fetch all books based on author name
     * @param authorName
     * @return all books written by an author otherwise 204
     */
    Response fetchAuthorBooks(String authorName);

    /**
     * Search books by author first/last name, isbn, title.
     * @param searchTerm
     * @param size
     * @return 5 or more books depending on size criteria otherwise 204
     */
    Response searchBook(String searchTerm, Long size);
}
