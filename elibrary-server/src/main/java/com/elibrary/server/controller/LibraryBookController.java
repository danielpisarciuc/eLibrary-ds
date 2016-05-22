package com.elibrary.server.controller;

import com.elibrary.common.dto.BookAuthorDto;
import com.elibrary.common.dto.BookDto;
import com.elibrary.common.dto.BookDetailDto;
import com.elibrary.server.utils.LibraryMessage;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/book")
@Controller
public class LibraryBookController {

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBook(BookDto bookDto) {
        return Response.status(Response.Status.NOT_FOUND).entity(LibraryMessage.NO_BOOK_ID.getMessage()).type(MediaType.TEXT_PLAIN).build();
    }

    @DELETE
    @Path("/delete/{bookId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("bookId") Long bookId) {
        return Response.status(Response.Status.NOT_FOUND).entity(LibraryMessage.NO_BOOK_ID.getMessage()).type(MediaType.TEXT_PLAIN).build();

    }

    @PUT
    @Path("/update/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_XML)
    public Response updateBook(@PathParam("bookId") Long bookId, BookDto bookDto) {
        System.out.print(bookDto);
        return Response.status(Response.Status.NOT_FOUND).entity(LibraryMessage.NO_BOOK_ID.getMessage()).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/fetch/{bookId}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response fetchBookById(@PathParam("bookId") Long bookId) {

        BookDto dto = new BookDto();
        dto.setBookId(12345L);
        dto.setIsbn("21235325dsada");
        dto.setTitle("Book Title");

        List<BookAuthorDto> authors = new ArrayList<>();
        BookAuthorDto bookAuthor = new BookAuthorDto();
        bookAuthor.setId(1L);
        bookAuthor.setFirstName("Author Name");
        bookAuthor.setLastName("Author Surname");
        authors.add(bookAuthor);

        dto.setBookAuthors(authors);

        List<BookDetailDto> details = new ArrayList<>();
        BookDetailDto bookDetail = new BookDetailDto();
        bookDetail.setId(12L);
        bookDetail.setFormat("Format Book");
        bookDetail.setDescription("Decription");
        bookDetail.setLanguage("Language EN");
        bookDetail.setPublicationDate(new Date());
        bookDetail.setSubject("Subject");
        details.add(bookDetail);

        BookDetailDto bookDetail2 = new BookDetailDto();
        bookDetail2.setFormat("Format Book");
        bookDetail2.setDescription("Decription");
        bookDetail2.setLanguage("Language EN");
        bookDetail2.setPublicationDate(new Date());
        bookDetail2.setSubject("Subject");

        details.add(bookDetail2);

        dto.setBookDetails(details);


        return Response.ok(dto).build();
    }

    @GET
    @Path("/details/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response bookDetails(@PathParam("bookId") Long bookId) {

        List<BookDetailDto> details = new ArrayList<>();
        BookDetailDto bookDetail = new BookDetailDto();
        bookDetail.setId(12L);
        bookDetail.setFormat("Format Book");
        bookDetail.setDescription("Decription");
        bookDetail.setLanguage("Language EN");
        bookDetail.setPublicationDate(new Date());
        bookDetail.setSubject("Subject");
        details.add(bookDetail);

        BookDetailDto bookDetail2 = new BookDetailDto();
        bookDetail2.setFormat("Format Book");
        bookDetail2.setDescription("Decription");
        bookDetail2.setLanguage("Language EN");
        bookDetail2.setPublicationDate(new Date());
        bookDetail2.setSubject("Subject");

        details.add(bookDetail2);

        return Response.ok(new GenericEntity<List<BookDetailDto>>(details) {}).build();
    }

    @GET
    @Path("/search/{searchTerm}")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response searchBook(@PathParam("searchTerm") String searchTerm, @QueryParam("size") Long size) {
        List<BookDto> books = new ArrayList<>();

        BookDto dto = new BookDto();
        dto.setBookId(12345L);
        dto.setIsbn("21235325dsada");
        dto.setTitle("Book Title");

        List<BookAuthorDto> authors = new ArrayList<>();
        BookAuthorDto bookAuthor = new BookAuthorDto();
        bookAuthor.setId(1L);
        bookAuthor.setFirstName("Author Name");
        bookAuthor.setLastName("Author Surname");
        authors.add(bookAuthor);

        dto.setBookAuthors(authors);

        books.add(dto);
        books.add(dto);

        return Response.ok(new GenericEntity<List<BookDto>>(books) {}).build();

    }
}
