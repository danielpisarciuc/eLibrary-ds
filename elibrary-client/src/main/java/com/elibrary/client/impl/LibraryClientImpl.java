package com.elibrary.client.impl;

import com.elibrary.client.LibraryClient;

import javax.ws.rs.client.Client;

public class LibraryClientImpl implements LibraryClient {

    private String eLibraryUrl;
    private Client client;

    public LibraryClientImpl(String eLibraryUrl) {
        this.eLibraryUrl = eLibraryUrl;
    }
}
