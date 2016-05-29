package com.elibrary.server.utils;

public class LibraryException extends Exception {

    public LibraryException(LibraryMessage message) {
        super(message.getMessage());
    }
}
