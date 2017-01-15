package com.elibrary.server.mapper;

import com.elibrary.common.dto.BookDetail;
import com.elibrary.common.dto.Book;
import com.elibrary.server.dao.entity.AuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;


public class LibraryMapperTest {

    @Test
    public void testMapBookDtoToEntity() throws Exception {
        Book dto = new Book();
        dto.setIsbn("Isbn");
        dto.setTitle("Title");
        dto.setBookDetails(new ArrayList<>());
        dto.setBookDetails(new ArrayList<>());
        BookEntity entity = LibraryMapper.mapBookDtoToEntity(dto);

        assertEquals(dto.getIsbn(), entity.getIsbn());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

    @Test
    public void testMapBookEntityToDto() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(112233);
        entity.setIsbn("Isbn");
        entity.setTitle("Title");
        entity.setDetailEntities(new HashSet<>());
        entity.setAuthorEntities(new HashSet<>());

        Book dto = LibraryMapper.mapBookEntityToDto(entity);

        assertEquals(entity.getId().toString(), dto.getId().toString());
        assertEquals(entity.getIsbn(), dto.getIsbn());
        assertEquals(entity.getTitle(), dto.getTitle());
    }

    @Test
    public void testMapBookDetailsEntityToDto() throws Exception {
        List<BookDetailEntity> entity = new ArrayList<>();
        BookDetailEntity bookDetail = new BookDetailEntity();
        bookDetail.setFormat("format");
        bookDetail.setLanguage("language");
        bookDetail.setPublicationDate(new Date());
        bookDetail.setDescription("No description");
        bookDetail.setSubject("No subject");
        entity.add(bookDetail);
        List<BookDetail> detailDtos = LibraryMapper.mapBookDetailsEntityToDto(entity);

        assertEquals(entity.get(0).getLanguage(), detailDtos.get(0).getLanguage());
        assertEquals(entity.get(0).getFormat(), detailDtos.get(0).getFormat());
    }

    @Test
    public void testMapAuthorBooksEntityToDto() throws Exception {
        List<AuthorEntity> entity = new ArrayList<>();
        AuthorEntity author = new AuthorEntity();
        BookEntity book = new BookEntity();
        book.setId(12345);
        book.setIsbn("1123ACD433");
        book.setTitle("Title");
        author.setBook(book);
        entity.add(author);
        List<Book> bookDtos = LibraryMapper.mapAuthorBooksEntityToDto(entity);

        assertEquals(book.getId().toString(), bookDtos.get(0).getId().toString());
    }

    @Test
    public void testMapBooksEntityToDto() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(1234);
        entity.setTitle("Title");
        entity.setIsbn("Isbn");
        entity.setDetailEntities(new HashSet<>());
        entity.setAuthorEntities(new HashSet<>());
        Book dto = LibraryMapper.mapBookEntityToDto(entity);

        assertEquals(entity.getId().toString(), dto.getId().toString());
        assertEquals(entity.getIsbn(), dto.getIsbn());
        assertEquals(entity.getTitle(), dto.getTitle());
    }
}