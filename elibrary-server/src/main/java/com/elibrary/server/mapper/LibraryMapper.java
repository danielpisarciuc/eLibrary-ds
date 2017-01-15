package com.elibrary.server.mapper;

import com.elibrary.common.dto.Author;
import com.elibrary.common.dto.Book;
import com.elibrary.common.dto.BookDetail;
import com.elibrary.common.utils.LibraryUtil;
import com.elibrary.server.dao.entity.AuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Map an object dto to an entity and vice versa
 */
public class LibraryMapper {

    public static BookEntity mapBookDtoToEntity(Book bookDto) {
        BookEntity entity = new BookEntity();
        entity.setIsbn(bookDto.getIsbn());
        entity.setTitle(bookDto.getTitle());

        bookDto.getBookAuthors()
                .stream()
                .filter(dto -> dto.getFirstName() != null && dto.getLastName() != null)
                .forEach(dto -> {
                    AuthorEntity bookAuthorEntity = new AuthorEntity();
                    bookAuthorEntity.setFirstName(dto.getFirstName());
                    bookAuthorEntity.setLastName(dto.getLastName());
                    bookAuthorEntity.setBook(entity);

                    entity.getAuthorEntities().add(bookAuthorEntity);
                });

        bookDto.getBookDetails()
                .stream()
                .filter(dto -> !LibraryUtil.isNullOrEmpty(dto.getFormat(), dto.getLanguage(), dto.getSubject()))
                .forEach(dto -> {
                    BookDetailEntity bookDetailEntity = new BookDetailEntity();

                    bookDetailEntity.setFormat(dto.getFormat());
                    bookDetailEntity.setLanguage(dto.getLanguage());
                    bookDetailEntity.setPublicationDate(dto.getPublicationDate());
                    bookDetailEntity.setDescription(dto.getDescription());
                    bookDetailEntity.setSubject(dto.getSubject());
                    bookDetailEntity.setBook(entity);
                    entity.getDetailEntities().add(bookDetailEntity);
                });

        return entity;
    }

    public static Book mapBookEntityToDto(BookEntity entity) {
        return Book.builder()
                .id(Long.valueOf(entity.getId()))
                .isbn(entity.getIsbn())
                .title(entity.getTitle())
                .bookAuthors(mapBookAuthors(entity))
                .bookDetails(mapBookDetails(entity))
                .build();
    }

    private static List<BookDetail> mapBookDetails(BookEntity entity) {
        List<BookDetail> bookDetails = new ArrayList<>();
        entity.getDetailEntities()
                .stream()
                .forEach(detailEntity -> detailEntityToDto(bookDetails, detailEntity));

        return bookDetails;
    }

    public static List<BookDetail> mapBookDetailsEntityToDto(List<BookDetailEntity> detailEntities) {
        List<BookDetail> bookDetails = new ArrayList<>();
        detailEntities.stream().forEach(detailEntity -> detailEntityToDto(bookDetails, detailEntity));

        return bookDetails;
    }

    private static void detailEntityToDto(List<BookDetail> bookDetails, BookDetailEntity entity) {
        BookDetail bookDetail = BookDetail.builder()
                .format(entity.getFormat())
                .language(entity.getLanguage())
                .publicationDate(entity.getPublicationDate())
                .description(entity.getDescription())
                .subject(entity.getSubject())
                .build();

        bookDetails.add(bookDetail);
    }

    public static List<Book> mapAuthorBooksEntityToDto(List<AuthorEntity> authorBooks) {
        List<Book> bookDtos = new ArrayList<>();
        authorBooks.stream()
                .forEach(author -> bookDtos.add(
                        Book.builder()
                                .id(Long.valueOf(author.getBook().getId()))
                                .title(author.getBook().getTitle())
                                .isbn(author.getBook().getIsbn())
                                .build()));

        return bookDtos;
    }

    public static List<Book> mapBooksEntityToDto(List<BookEntity> bookEntities) {
        List<Book> bookDtos = new ArrayList<>();

        bookEntities.stream().forEach(book -> bookDtos.add(
                Book.builder()
                        .id(book.getId().longValue())
                        .title(book.getTitle())
                        .isbn(book.getIsbn())
                        .bookAuthors(mapBookAuthors(book))
                        .build()));

        return bookDtos;
    }

    private static List<Author> mapBookAuthors(BookEntity bookEntity) {
        List<Author> bookAuthors = new ArrayList<>();

        bookEntity.getAuthorEntities()
                .stream()
                .forEach(authorEntity -> bookAuthors.add(Author.builder()
                        .firstName(authorEntity.getFirstName())
                        .lastName(authorEntity.getLastName())
                        .build()));

        return bookAuthors;
    }
}
