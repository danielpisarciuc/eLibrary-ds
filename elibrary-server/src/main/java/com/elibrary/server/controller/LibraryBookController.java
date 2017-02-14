package com.elibrary.server.controller;

import com.elibrary.common.dto.Book;
import com.elibrary.server.service.LibraryService;
import com.elibrary.server.utils.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
@Controller
public class LibraryBookController {

    @Autowired
    private LibraryService libraryService;

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
    public Response searchBook(@PathParam("searchTerm") String searchTerm, @QueryParam("size") @DefaultValue("10") Long size) {
        try {
            return Response.ok().entity(libraryService.searchBook(searchTerm, size)).build();
        } catch (LibraryException exception) {
            return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }
}
