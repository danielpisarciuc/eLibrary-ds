package com.elibrary.client;

import javax.ws.rs.core.Response;

public interface LibraryClient {

    /**
     * Retrieve Library reference data based on type.
     *
     * @param type
     */
    Response referenceData(String type);

}
