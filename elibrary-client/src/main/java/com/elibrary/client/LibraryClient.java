package com.elibrary.client;

import javax.ws.rs.core.Response;

public interface LibraryClient {

    Response getReferenceData(String type);
}
