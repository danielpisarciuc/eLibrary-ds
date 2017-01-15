package com.elibrary.server.dao;

import com.elibrary.server.utils.LibraryException;

import java.util.List;

public interface ReferenceDataDao {

    /**
     * Retrieve reference date based on give type
     *
     * @param type the identifier
     * @return a reference data list with otherwise empty list
     * @throws LibraryException
     */
    List retrieveReferenceData(String type) throws LibraryException;
}
