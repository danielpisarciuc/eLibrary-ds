package com.elibrary.server.dao.impl;

import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ReferenceDataDaoImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ReferenceDataDaoImpl referenceDataDao = new ReferenceDataDaoImpl();

    @Test
    public void testRetrieveReferenceDataTypeIsEmpty() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_REFERENCE_DATA_TYPE.getMessage());

        referenceDataDao.retrieveReferenceData("");
    }

    @Test
    public void testRetrieveReferenceDataTypeIsNull() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_REFERENCE_DATA_TYPE.getMessage());

        referenceDataDao.retrieveReferenceData(null);
    }
}