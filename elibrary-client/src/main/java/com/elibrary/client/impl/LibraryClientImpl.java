package com.elibrary.client.impl;

import com.elibrary.client.LibraryClient;
import com.elibrary.common.dto.SearchBookDto;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class LibraryClientImpl implements LibraryClient {

    private String eLibraryUrl;
    private Client client;
    private String referenceData;
    private String simpleSearch;
    private String complexSearch;

    public LibraryClientImpl(String eLibraryUrl) {
        this.eLibraryUrl = eLibraryUrl;
        this.referenceData = this.eLibraryUrl + "/" + "reference-data?type=";
        this.simpleSearch = this.eLibraryUrl + "/" + "simple-search?searchTerm=";
        this.complexSearch = this.eLibraryUrl + "/" + "complex-search";
    }

    @Override
    public Response referenceData(String type) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.referenceData + type);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response bookSimpleSearch(String searchTerm) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.simpleSearch + searchTerm);
        Response response = target.request().get();

        return response;
    }

    @Override
    public Response bookComplexSearch(SearchBookDto searchBook) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.complexSearch);
        Response response = target.request().post(Entity.json(searchBook));

        return response;
    }

}
