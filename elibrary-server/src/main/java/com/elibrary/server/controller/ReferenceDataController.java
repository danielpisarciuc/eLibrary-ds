package com.elibrary.server.controller;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reference-data")
@Controller
public class ReferenceDataController {

    @Autowired
    private ReferenceDataDao referenceDataDao;

    @GET
    @Path("/{type}")
    @Produces("application/json")
    public Response getReferenceData(@PathParam("type") String type) {
        if (type == null || type.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity(LibraryMessage.NO_REFERENCE_DATA_TYPE.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }

        try {
            return Response.ok(referenceDataDao.retrieveReferenceData(type)).build();
        } catch (LibraryException exception) {
            return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }
}
