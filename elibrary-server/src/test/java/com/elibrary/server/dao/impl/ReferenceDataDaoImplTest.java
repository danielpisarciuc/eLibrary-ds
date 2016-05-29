package com.elibrary.server.dao.impl;

import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReferenceDataDaoImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    ReferenceDataDaoImpl referenceDataDao;

    @Test
    public void testRetrieveReferenceDataTypeIsEmpty() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_REFERENCE_DATA_TYPE.getMessage());

        referenceDataDao.retrieveReferenceData("");
    }

    @Test
    @Ignore
    public void testRetrieveReferenceDataTypeIsNull() throws LibraryException {
        expectedException.expect(LibraryException.class);
        expectedException.expectMessage(LibraryMessage.NO_REFERENCE_DATA_TYPE.getMessage());

        referenceDataDao.retrieveReferenceData(null);
    }
}