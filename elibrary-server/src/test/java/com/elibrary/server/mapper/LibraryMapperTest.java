package com.elibrary.server.mapper;

import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.server.dao.entity.BookAuthorEntity;
import com.elibrary.server.dao.entity.BookDetailEntity;
import com.elibrary.server.dao.entity.BookEntity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.*;


public class LibraryMapperTest {

    @Test
    public void testMapBookDtoToEntity() throws Exception {
        BookDto dto = new BookDto();
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

        BookDto dto = LibraryMapper.mapBookEntityToDto(entity);

        assertEquals(entity.getId().toString(), dto.getBookId().toString());
        assertEquals(entity.getIsbn(), dto.getIsbn());
        assertEquals(entity.getTitle(), dto.getTitle());
    }

    @Test
    public void testMapBookDetailsEntityToDto() throws Exception {
        List<BookDetailEntity> entity = new ArrayList<>();
        BookDetailEntity bookDetail = new BookDetailEntity();
        bookDetail.setFormat("format");
        bookDetail.setLanguage("language");
        entity.add(bookDetail);
        List<BookDetailDto> detailDtos = LibraryMapper.mapBookDetailsEntityToDto(entity);

        assertEquals(entity.get(0).getLanguage(), detailDtos.get(0).getLanguage());
        assertEquals(entity.get(0).getFormat(), detailDtos.get(0).getFormat());
    }

    @Test
    public void testMapAuthorBooksEntityToDto() throws Exception {
        List<BookAuthorEntity> entity = new ArrayList<>();
        BookAuthorEntity author = new BookAuthorEntity();
        BookEntity book = new BookEntity();
        book.setId(12345);
        author.setBook(book);
        entity.add(author);
        List<BookDto> bookDtos = LibraryMapper.mapAuthorBooksEntityToDto(entity);

        assertEquals(book.getId().toString(), bookDtos.get(0).getBookId().toString());
    }

    @Test
    public void testMapBooksEntityToDto() throws Exception {
        BookEntity entity = new BookEntity();
        entity.setId(1234);
        entity.setTitle("Title");
        entity.setIsbn("Isbn");
        entity.setDetailEntities(new HashSet<>());
        entity.setAuthorEntities(new HashSet<>());
        BookDto dto = LibraryMapper.mapBookEntityToDto(entity);

        assertEquals(entity.getId().toString(), dto.getBookId().toString());
        assertEquals(entity.getIsbn(), dto.getIsbn());
        assertEquals(entity.getTitle(), dto.getTitle());
    }
}