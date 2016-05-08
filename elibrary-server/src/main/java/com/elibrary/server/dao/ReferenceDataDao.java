package com.elibrary.server.dao;

import com.elibrary.server.utils.LibraryException;

import java.util.List;

public interface ReferenceDataDao {

    List retrieveReferenceData(String type) throws LibraryException;
}
