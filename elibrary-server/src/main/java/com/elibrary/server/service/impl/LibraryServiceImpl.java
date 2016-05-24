package com.elibrary.server.service.impl;


import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.common.utils.LibraryUtil;
import com.elibrary.server.dao.LibraryBookDao;
import com.elibrary.server.dao.entity.BookAuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import com.elibrary.server.mapper.LibraryMapper;
import com.elibrary.server.service.LibraryService;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import com.elibrary.server.utils.LibraryValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("libraryService")
public class LibraryServiceImpl implements LibraryService {

    private final Logger LOGGER = Logger.getLogger(LibraryServiceImpl.class);

    @Autowired
    private LibraryBookDao libraryBookDao;

    @Override
    public void createBook(BookDto bookDto) throws LibraryException {
        if (!LibraryValidation.isBookValid(bookDto)) {
            throw new LibraryException(LibraryMessage.INVALID_BOOK.getMessage());
        }

        libraryBookDao.addBook(LibraryMapper.mapBookDtoToEntity(bookDto));

    }

    @Override
    public void deleteBook(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        }

        libraryBookDao.deleteBook(bookId);
    }


    @Override
    public void updateBook(Long bookId, BookDto bookDto) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        } else if (!LibraryValidation.isBookValid(bookDto)) {
            throw new LibraryException(LibraryMessage.INVALID_BOOK.getMessage());
        }

        libraryBookDao.updateBook(bookId, LibraryMapper.mapBookDtoToEntity(bookDto));
    }

    @Override
    public BookDto fetchBookById(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        }

        BookEntity entity = libraryBookDao.retrieveBookById(bookId);

        return LibraryMapper.mapBookEntityToDto(entity);
    }

    @Override
    public List<BookDetailDto> fetchBookDetails(Long bookId) throws LibraryException {
        if (bookId == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        }

        List<BookDetailEntity> detailEntities = libraryBookDao.retrieveBookDetails(bookId);

        return LibraryMapper.mapBookDetailsEntityToDto(detailEntities);
    }

    @Override
    public List<BookDto> fetchAuthorBooks(String authorName) throws LibraryException {
        if (authorName == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        }

        List<BookAuthorEntity> authorEntities = libraryBookDao.retrieveAuthorBooks(authorName);

        return LibraryMapper.mapAuthorBooksEntityToDto(authorEntities);
    }

    @Override
    public List<BookDto> searchBook(String searchTerm, Long size) throws LibraryException {
        if (LibraryUtil.isNullOrEmpty(searchTerm) || size == null) {
            throw new LibraryException(LibraryMessage.NO_BOOK_ID.getMessage());
        }

        List<BookEntity> bookEntities = libraryBookDao.searchBook(searchTerm, size);
        return LibraryMapper.mapBooksEntityToDto(bookEntities);
    }
}
