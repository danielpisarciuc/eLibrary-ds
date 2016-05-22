package com.elibrary.server.mapper;

import com.elibrary.common.dto.BookAuthorDto;
import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.common.utils.LibraryUtil;
import com.elibrary.server.dao.entity.BookAuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Map an object dto to an entity and vice versa
 */
public class LibraryMapper {

    public static BookEntity mapBookDtoToEntity(BookDto bookDto) {
        BookEntity entity = new BookEntity();
        entity.setIsbn(bookDto.getIsbn());
        entity.setTitle(bookDto.getTitle());

        bookDto.getBookAuthors()
                .stream()
                .filter(dto -> dto.getFirstName() != null && dto.getLastName() != null)
                .forEach(dto -> {
                    BookAuthorEntity bookAuthorEntity = new BookAuthorEntity();

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

    public static BookDto mapBookEntityToDto(BookEntity entity) {
        BookDto dto = new BookDto();
        dto.setBookId(Long.valueOf(entity.getId()));
        dto.setIsbn(entity.getIsbn());
        dto.setTitle(entity.getTitle());

        List<BookAuthorDto> bookAuthors = new ArrayList<>();
        entity.getAuthorEntities()
                .stream()
                .forEach(authorEntity -> {
                    BookAuthorDto bookAuthorDto = new BookAuthorDto();

                    bookAuthorDto.setFirstName(authorEntity.getFirstName());
                    bookAuthorDto.setLastName(authorEntity.getLastName());
                    bookAuthors.add(bookAuthorDto);
                });

        dto.setBookAuthors(bookAuthors);

        List<BookDetailDto> bookDetails = new ArrayList<>();
        entity.getDetailEntities()
                .stream()
                .forEach(detailEntity -> {
                    detailEntityToDto(bookDetails, detailEntity);
                });

        dto.setBookDetails(bookDetails);
        return dto;
    }

    public static List<BookDetailDto> mapBookDetailsEntityToDto(List<BookDetailEntity> detailEntities) {
        List<BookDetailDto> bookDetails = new ArrayList<>();
        detailEntities.stream().forEach(detailEntity -> {
            detailEntityToDto(bookDetails, detailEntity);
        });

        return bookDetails;
    }

    private static void detailEntityToDto(List<BookDetailDto> bookDetails, BookDetailEntity detailEntity) {
        BookDetailDto bookDetailDto = new BookDetailDto();
        bookDetailDto.setFormat(detailEntity.getFormat());
        bookDetailDto.setLanguage(detailEntity.getLanguage());
        bookDetailDto.setPublicationDate(detailEntity.getPublicationDate());
        bookDetailDto.setDescription(detailEntity.getDescription());
        bookDetailDto.setSubject(detailEntity.getSubject());
        bookDetails.add(bookDetailDto);
    }

    public static List<BookDto> mapAuthorBooksEntityToDto(List<BookAuthorEntity> authorBooks) {
        List<BookDto> bookDtos = new ArrayList<>();
        authorBooks.stream().forEach(author -> {
            BookDto dto = new BookDto();
            dto.setBookId(Long.valueOf(author.getBook().getId()));
            dto.setTitle(author.getBook().getTitle());
            dto.setIsbn(author.getBook().getIsbn());
            bookDtos.add(dto);
        });

        return bookDtos;
    }
}
