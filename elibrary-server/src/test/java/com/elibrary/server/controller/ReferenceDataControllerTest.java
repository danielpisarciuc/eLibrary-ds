package com.elibrary.server.controller;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReferenceDataControllerTest extends JerseyTest {

    @Mock
    private static ReferenceDataDao referenceDataDao;

    @Path("reference-data")
    public static class ReferenceDataControllerResource {

        @GET
        @Path("/{type}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response fetchReferenceData(@PathParam("type") String type) {
            try {
                return Response.ok(referenceDataDao.retrieveReferenceData(type)).build();
            } catch (LibraryException exception) {
                return Response.status(Response.Status.NO_CONTENT).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
            }
        }
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(ReferenceDataControllerResource.class);
    }

    @Test
    public void testFetchReferenceDataNoContent() throws LibraryException {
        String type = "test";

        when(referenceDataDao.retrieveReferenceData(type)).thenThrow(new LibraryException(LibraryMessage.NO_REFERENCE_DATA_TYPE));

        Response response = target("reference-data/" + type).request().get();

        assertEquals((Response.Status.NO_CONTENT).getStatusCode(), response.getStatus());
        assertEquals(MediaType.TEXT_PLAIN, String.valueOf(response.getMediaType()));

        verify(referenceDataDao).retrieveReferenceData(type);
    }


}