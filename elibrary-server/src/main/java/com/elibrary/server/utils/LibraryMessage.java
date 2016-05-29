package com.elibrary.server.utils;


public enum LibraryMessage {

    APPLICATION_INFO("eLibrary data service application"),
    NO_REFERENCE_DATA("Reference data not found"),
    NO_REFERENCE_DATA_TYPE("Provide the reference data type"),
    NO_BOOK_ID("Please provide the bookId value"),
    NO_AUTHOR("Please provide the author name value"),
    NO_SEARCH_TERM("Please provide the search value"),
    NO_RECORDS_FOUND("No record/s found into database"),
    INVALID_BOOK("Please provide book isbn, title, author/s, detail/s"),

    //database error message
    NO_BOOK_ENTITY_ID("Book primary key not found");

    private String message;

    LibraryMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
