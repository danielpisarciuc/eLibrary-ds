package com.elibrary.server.controller;

import com.elibrary.common.dto.Author;
import com.elibrary.common.dto.Book;
import com.elibrary.common.dto.BookDetail;
import com.elibrary.server.service.LibraryService;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryBookControllerTest extends JerseyTest {

    public static final Long BOOK_ID = 1L;

    @Mock
    private static LibraryService libraryService;

    @Path("book")
    public static class LibraryBookControllerResource {

        @POST
        @Path("/create")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createBook(Book book) {
            try {
                libraryService.createBook(book);
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NOT_MODIFIED).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
            return Response.status(Response.Status.CREATED).build();
        }

        @DELETE
        @Path("/delete/{bookId}")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteBook(@PathParam("bookId") Long bookId) {
            try {
                libraryService.deleteBook(bookId);
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NOT_MODIFIED).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
            return Response.status(Response.Status.CREATED).build();

        }

        @PUT
        @Path("/update/{bookId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateBook(@PathParam("bookId") Long bookId, Book book) {
            try {
                libraryService.updateBook(bookId, book);
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NOT_MODIFIED).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
            return Response.status(Response.Status.CREATED).build();
        }

        @GET
        @Path("/fetch/{bookId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response fetchBookById(@PathParam("bookId") Long bookId) {
            try {
                return Response.ok().entity(libraryService.fetchBookById(bookId)).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }

        @GET
        @Path("/fetch/all")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response fetchAllBooks() {
            try {
                return Response.ok().entity(libraryService.fetchAllBooks()).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }

        @GET
        @Path("/details/{bookId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response fetchBookDetails(@PathParam("bookId") Long bookId) {
            try {
                return Response.ok().entity(libraryService.fetchBookDetails(bookId)).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }

        @GET
        @Path("/author-books")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response fetchAuthorBooks(@QueryParam("authorName") String authorName) {
            try {
                return Response.ok().entity(libraryService.fetchAuthorBooks(authorName)).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }

        @GET
        @Path("/search/{searchTerm}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response searchBook(@PathParam("searchTerm") String searchTerm, @QueryParam("size") @DefaultValue("5") Long size) {
            try {
                return Response.ok().entity(libraryService.searchBook(searchTerm, size)).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }
    }

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(LibraryBookControllerResource.class);
    }

    @Test
    public void testCreateBookNotModified() throws LibraryException {
        Book book = mockBook();

        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).createBook(book);

        Response response = target("book/create").request().accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(book));

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).createBook(book);
    }

    @Test
    public void testDeleteBookNotModified() throws LibraryException {
        Long bookId = 1L;

        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).deleteBook(bookId);

        Response response = target("book/delete/" + bookId).request().delete();

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).deleteBook(bookId);
    }

    @Test
    public void testUpdateBookNotModified() throws Exception {
        Book book = mockBook();

        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).updateBook(BOOK_ID, book);

        Response response = target("book/update/" + BOOK_ID).request().accept(MediaType.APPLICATION_JSON_TYPE).put(Entity.json(book));

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).updateBook(BOOK_ID, book);
    }

    @Test
    public void testFetchBookByIdNoContent() throws LibraryException {
        when(libraryService.fetchBookById(BOOK_ID)).thenThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND));

        Response response = target("book/fetch/" + BOOK_ID).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).fetchBookById(BOOK_ID);
    }

    @Test
    public void testFetchBookDetailsNoContent() throws LibraryException {
        when(libraryService.fetchBookDetails(BOOK_ID)).thenThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND));

        Response response = target("book/details/" + BOOK_ID).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).fetchBookDetails(BOOK_ID);
    }

    @Test
    public void testFetchAuthorBooksNoContent() throws LibraryException {
        String authorName = "author";

        when(libraryService.fetchAuthorBooks(authorName)).thenThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND));

        Response response = target("book/author-books")
                            .queryParam("authorName", authorName)
                            .request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).fetchAuthorBooks(authorName);
    }

    @Test
    public void testSearchBookNoContent() throws LibraryException {
        String searchTerm = "author";
        Long size = 10L;

        when(libraryService.searchBook(searchTerm, size)).thenThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND));

        Response response = target("book/search/" + searchTerm)
                .queryParam("size", String.valueOf(size))
                .request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(libraryService).searchBook(searchTerm, size);
    }

    private Book mockBook() {
        List<Author> authors = new ArrayList<>();
        authors.add(
                Author.builder()
                        .lastName("Mitrut")
                        .firstName("Ispirescu")
                        .build());

        List<BookDetail> details = new ArrayList<>();
        details.add(
                BookDetail.builder()
                        .format("PDF")
                        .description("No description")
                        .subject("No subject")
                        .language("EN")
                        .publicationDate(new Date())
                        .build());

        return Book.builder()
                .id(11L)
                .isbn("ISBN")
                .title("eLibrary")
                .bookAuthors(authors)
                .bookDetails(details)
                .build();

    }

}