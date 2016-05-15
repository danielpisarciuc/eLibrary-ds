package com.elibrary.client.impl;

import com.elibrary.client.LibraryClient;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class LibraryClientImpl implements LibraryClient {

    private String eLibraryUrl;
    private Client client;
    private String referenceData;

    public LibraryClientImpl(String eLibraryUrl) {
        this.eLibraryUrl = eLibraryUrl;
        this.referenceData = this.eLibraryUrl + "/" + "reference-data?type=";
    }

    @Override
    public Response getReferenceData(String type) {
        client = ClientBuilder.newClient(new ClientConfig().register(Response.class));
        WebTarget target = client.target(this.referenceData + type);
        Response response = target.request().get();

        return response;
    }
}
