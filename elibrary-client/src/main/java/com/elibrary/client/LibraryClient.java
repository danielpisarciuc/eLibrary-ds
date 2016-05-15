package com.elibrary.client;

import com.elibrary.common.dto.SearchBookDto;

import javax.ws.rs.core.Response;

public interface LibraryClient {

    Response referenceData(String type);

    Response bookSimpleSearch(String searchTerm);

    Response bookComplexSearch(SearchBookDto searchBook);
}
