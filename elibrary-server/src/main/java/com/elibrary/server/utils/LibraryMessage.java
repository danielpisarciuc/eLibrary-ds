package com.elibrary.server.utils;


public enum LibraryMessage {

    APPLICATION_INFO("eLibrary data service application");

    private String message;

    LibraryMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
