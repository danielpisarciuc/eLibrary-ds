package com.elibrary.server.dao.impl;

import com.elibrary.server.dao.LibraryBookDao;
import com.elibrary.server.dao.entity.BookAuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("libraryBookDao")
public class LibraryBookDaoImpl extends AbstractDao implements LibraryBookDao {

    @Override
    public void addBook(BookEntity entity) {
        getSession().save(entity);
    }

    @Override
    public void deleteBook(Long bookId) throws LibraryException {
        BookEntity bookEntity = (BookEntity) getSession().get(BookEntity.class, bookId.intValue());

        if (bookEntity == null) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND.getMessage() + "bookId:" + bookId);
        }

        getSession().delete(bookEntity);
    }

    @Override
    public void updateBook(Long bookId, BookEntity bookEntity) throws LibraryException {
        BookEntity entity = (BookEntity) getSession().get(BookEntity.class, bookId.intValue());

        if (entity == null && entity.getId().equals(bookId.intValue())) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND.getMessage() + "bookId:" + bookId);
        }

        getSession().update(bookEntity);
    }

    @Override
    public BookEntity retrieveBookById(Long bookId) throws LibraryException {
        BookEntity entity = (BookEntity) getSession().get(BookEntity.class, bookId.intValue());

        if (entity == null && entity.getId().equals(bookId.intValue())) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND.getMessage() + "bookId:" + bookId);
        }

        return entity;
    }

    @Override
    public List retrieveBookDetails(Long bookId) throws LibraryException {
        Query namedQuery = getSession().getNamedQuery(BookDetailEntity.BOOK_DETAILS);
        namedQuery.setParameter("bookId", bookId.intValue());

        List bookDetails = namedQuery.list();
        if (bookDetails.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND.getMessage() + "bookId:" + bookId);
        }

        return bookDetails;
    }

    @Override
    public List retrieveAuthorBooks(String authorName) throws LibraryException {
        Query namedQuery = getSession().getNamedQuery(BookAuthorEntity.AUTHOR_BOOKS);
        namedQuery.setParameter("authorName", authorName);

        List bookDetails = namedQuery.list();
        if (bookDetails.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND.getMessage() + "authorName:" + authorName);
        }

        return bookDetails;
    }
}
