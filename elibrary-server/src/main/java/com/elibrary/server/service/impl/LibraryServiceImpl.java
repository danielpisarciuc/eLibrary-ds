package com.elibrary.server.service.impl;


import com.elibrary.common.dto.BookDetail;
import com.elibrary.common.dto.Book;
import com.elibrary.common.utils.LibraryUtil;
import com.elibrary.server.dao.LibraryBookDao;
import com.elibrary.server.dao.entity.AuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.mapper.LibraryMapper;
import com.elibrary.server.service.LibraryService;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import com.elibrary.server.utils.LibraryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("libraryService")
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryBookDao libraryBookDao;

    @Override
    public void createBook(Book bookDto) throws LibraryException {
        if (!LibraryValidation.isBookValid(bookDto)) {
            throw new LibraryException(LibraryMessage.INVALID_BOOK);
        }

        libraryBookDao.addBook(LibraryMapper.mapBookDtoToEntity(bookDto));

    }

    @Override
    public void deleteBook(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID);
        }

        libraryBookDao.deleteBook(bookId);
    }


    @Override
    public void updateBook(Long bookId, Book bookDto) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID);
        } else if (!LibraryValidation.isBookValid(bookDto)) {
            throw new LibraryException(LibraryMessage.INVALID_BOOK);
        }

        libraryBookDao.updateBook(bookId, LibraryMapper.mapBookDtoToEntity(bookDto));
    }

    @Override
    public Book fetchBookById(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID);
        }

        BookEntity entity = libraryBookDao.retrieveBookById(bookId);

        return LibraryMapper.mapBookEntityToDto(entity);
    }

    @Override
    public List<Book> fetchAllBooks() throws LibraryException {
        List<BookEntity> bookEntities = libraryBookDao.retrieveAllBooks();
        return LibraryMapper.mapBooksEntityToDto(bookEntities);
    }

    @Override
    public List<BookDetail> fetchBookDetails(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID);
        }

        List<BookDetailEntity> detailEntities = libraryBookDao.retrieveBookDetails(bookId);

        return LibraryMapper.mapBookDetailsEntityToDto(detailEntities);
    }

    @Override
    public List<Book> fetchAuthorBooks(String authorName) throws LibraryException {
        if (LibraryUtil.isNullOrEmpty(authorName)) {
            throw new LibraryException(LibraryMessage.NO_AUTHOR);
        }

        List<AuthorEntity> authorEntities = libraryBookDao.retrieveAuthorBooks(authorName);

        return LibraryMapper.mapAuthorBooksEntityToDto(authorEntities);
    }

    @Override
    public List<Book> searchBook(String searchTerm, Long size) throws LibraryException {
        if (LibraryUtil.isNullOrEmpty(searchTerm)) {
            throw new LibraryException(LibraryMessage.NO_SEARCH_TERM);
        }

        List<BookEntity> bookEntities = libraryBookDao.searchBook(searchTerm, size);
        return LibraryMapper.mapBooksEntityToDto(bookEntities);
    }
}
