package com.elibrary.client.impl;

import com.elibrary.client.LibraryClient;
import com.elibrary.common.dto.Book;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LibraryClientImpl implements LibraryClient {

    private String eLibraryUrl;
    private Client client;
    private String appInfo;
    private String referenceData;
    private String createBook;
    private String deleteBook;
    private String updateBook;
    private String fetchBookById;
    private String fetchBookDetails;
    private String fetchAuthorBooks;
    private String searchBook;

    public LibraryClientImpl(String eLibraryUrl) {
        this.eLibraryUrl = eLibraryUrl;
        this.appInfo = this.eLibraryUrl + "/app/info";
        this.referenceData = this.eLibraryUrl + "/reference-data/";
        this.createBook = this.eLibraryUrl + "/book/create";
        this.deleteBook = this.eLibraryUrl + "/book/delete/";
        this.updateBook = this.eLibraryUrl + "/book/update/";
        this.fetchBookById = this.eLibraryUrl + "/book/fetch/";
        this.fetchBookDetails = this.eLibraryUrl + "/book/details/";
        this.fetchAuthorBooks = this.eLibraryUrl + "/book/author-books" +"?authorName=";
        this.searchBook = this.eLibraryUrl + "/book/search/";
    }

    @Override
    public Response applicationInfo() {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.appInfo);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response referenceData(String type) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.referenceData + type);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response createBook(Book bookDto) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.createBook);
        Response response = target.request().post(Entity.entity(bookDto, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

    @Override
    public Response deleteBook(Long bookId) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.deleteBook + bookId);
        Response response = target.request().delete();

        return response;
    }

    @Override
    public Response updateBook(Long bookId, Book bookDto) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.updateBook + bookId);
        Response response = target.request().put(Entity.entity(bookDto, MediaType.APPLICATION_JSON_TYPE));

        return response;
    }

    @Override
    public Response fetchBookById(Long bookId) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.fetchBookById + bookId);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response fetchBookDetails(Long bookId) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.fetchBookDetails + bookId);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response fetchAuthorBooks(String authorName) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.fetchAuthorBooks + authorName);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response searchBook(String searchTerm, Long size) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.searchBook + searchTerm + "?size=" + size);
        Response response = target.request().get();

        return response;
    }
}
