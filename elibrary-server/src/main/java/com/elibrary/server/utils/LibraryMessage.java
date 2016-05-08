package com.elibrary.server.utils;


public enum LibraryMessage {

    APPLICATION_INFO("eLibrary data service application"),
    NO_REFERENCE_DATA("Reference data not found"),
    NO_REFERENCE_DATA_TYPE("Provide the reference data type");

    private String message;

    LibraryMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
