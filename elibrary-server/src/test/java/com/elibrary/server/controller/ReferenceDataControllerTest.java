package com.elibrary.server.controller;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

public class ReferenceDataControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ReferenceDataController.class);
    }

    @Test
    public void testFetchReferenceDataNoContent() throws LibraryException {
        String type = "test";
        ReferenceDataDao referenceDataDao = mock(ReferenceDataDao.class);
        doThrow(new LibraryException(LibraryMessage.NO_REFERENCE_DATA_TYPE)).when(referenceDataDao).retrieveReferenceData(type);

        Response response = target("reference-data/" + type).request().get();

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

    }


}