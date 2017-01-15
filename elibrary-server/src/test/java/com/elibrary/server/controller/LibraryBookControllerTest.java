package com.elibrary.server.controller;

import com.elibrary.common.dto.Book;
import com.elibrary.server.service.LibraryService;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class LibraryBookControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(LibraryBookController.class);
    }

    @Test
    public void testCreateBookNotModified() {
        Response response = target("book/create").request().post(Entity.entity(null, MediaType.APPLICATION_JSON_TYPE));

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

    }

    @Test
    public void testDeleteBookNotModified() {
        Response response = target("book/delete/1").request().delete();

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));
    }

    @Test
    @Ignore
    public void testUpdateBookNotModified() throws Exception {
        Response response = target("book/update/1").request().put(Entity.entity(new Book(), MediaType.APPLICATION_JSON_TYPE));

        assertEquals((Response.Status.NOT_MODIFIED).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));
    }

    @Test
    public void testFetchBookByIdNoContent() throws LibraryException {
        Long bookId = 1L;
        LibraryService libraryService = mock(LibraryService.class);
        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).fetchBookById(bookId);

        Response response = target("book/fetch/" + bookId).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));
    }

    @Test
    public void testFetchBookDetailsNoContent() throws Exception {
        LibraryService libraryService = mock(LibraryService.class);
        Long bookId = 1L;
        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).fetchBookDetails(bookId);

        Response response = target("book/details/" + bookId).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));
    }

    @Test
    public void testFetchAuthorBooksNoContent() throws Exception {
        String authorName = "author";

        LibraryService libraryService = mock(LibraryService.class);
        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).fetchAuthorBooks(authorName);

        Response response = target("book/author-books").queryParam(authorName).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

    }

    @Test
    public void testSearchBookNoContent() throws Exception {
        String searchTerm = "author";
        Long size = 10L;

        LibraryService libraryService = mock(LibraryService.class);
        doThrow(new LibraryException(LibraryMessage.NO_RECORDS_FOUND)).when(libraryService).searchBook(searchTerm, size);

        Response response = target("book/search/" + searchTerm).queryParam(String.valueOf(size)).request().get(Response.class);

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

    }
}