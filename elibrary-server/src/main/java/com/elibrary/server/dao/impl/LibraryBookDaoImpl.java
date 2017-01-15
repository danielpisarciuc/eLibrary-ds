package com.elibrary.server.dao.impl;

import com.elibrary.server.dao.LibraryBookDao;
import com.elibrary.server.dao.entity.AuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("libraryBookDao")
public class LibraryBookDaoImpl extends AbstractDao implements LibraryBookDao {

    @Override
    public void addBook(BookEntity entity) {
        save(entity);
    }

    @Override
    public void deleteBook(Long bookId) throws LibraryException {
        BookEntity bookEntity = (BookEntity) getSession().get(BookEntity.class, bookId.intValue());

        if (bookEntity == null) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }

        delete(bookEntity);
    }

    @Override
    public void updateBook(Long bookId, BookEntity bookEntity) throws LibraryException {
        int idOfTheBook = bookId.intValue();
        BookEntity entity = (BookEntity) getSession().get(BookEntity.class, idOfTheBook);

        if (entity == null || !entity.getId().equals(idOfTheBook)) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }
        update(bookEntity);
    }

    @Override
    public BookEntity retrieveBookById(Long bookId) throws LibraryException {
        BookEntity entity = (BookEntity) getSession().get(BookEntity.class, bookId.intValue());

        if (entity == null) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        } else if (entity.getId() == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ENTITY_ID);
        }

        return entity;
    }

    @Override
    public List retrieveBookDetails(Long bookId) throws LibraryException {
        Query namedQuery = getSession().getNamedQuery(BookDetailEntity.BOOK_DETAILS);
        namedQuery.setParameter("bookId", bookId.intValue());

        List bookDetails = namedQuery.list();
        if (bookDetails.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }

        return bookDetails;
    }

    @Override
    public List retrieveAuthorBooks(String authorName) throws LibraryException {
        Query namedQuery = getSession().getNamedQuery(AuthorEntity.AUTHOR_BOOKS);
        namedQuery.setParameter("authorName", authorName);

        List bookDetails = namedQuery.list();
        if (bookDetails.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }

        return bookDetails;
    }

    @Override
    public List searchBook(String searchTerm, Long size) throws LibraryException {
        Disjunction disCriteria = Restrictions.disjunction();
        disCriteria.add(Restrictions.like("isbn", searchTerm, MatchMode.START));
        disCriteria.add(Restrictions.like("title", searchTerm, MatchMode.ANYWHERE).ignoreCase());
        disCriteria.add(Restrictions.like("author.firstName", searchTerm, MatchMode.ANYWHERE).ignoreCase());
        disCriteria.add(Restrictions.like("author.lastName", searchTerm, MatchMode.ANYWHERE).ignoreCase());

        Criteria criteria = getSession().createCriteria(BookEntity.class);
        criteria.createAlias("authorEntities", "author");
        criteria.setMaxResults(size.intValue());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.add(disCriteria);

        List books = criteria.list();
        if (books.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }

        return books;
    }

    @Override
    public List<BookEntity> retrieveAllBooks() throws LibraryException {
        Criteria criteria = getSession().createCriteria(BookEntity.class);
        List books = criteria.list();
        if (books.isEmpty()) {
            throw new LibraryException(LibraryMessage.NO_RECORDS_FOUND);
        }

        return books;
    }
}
