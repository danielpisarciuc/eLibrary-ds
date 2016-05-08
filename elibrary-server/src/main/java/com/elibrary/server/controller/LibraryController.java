package com.elibrary.server.controller;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.utils.LibraryException;
import com.elibrary.server.utils.LibraryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elibrary")
@Controller
public class LibraryController {

    @Autowired
    private ReferenceDataDao referenceDataDao;

    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyRESTService() {
        return Response.status(200).entity(LibraryMessage.APPLICATION_INFO.getMessage()).build();
    }

    @GET
    @Path("/reference-data")
    @Produces("application/json")
    public Response getReferenceData(@QueryParam("type") String type) {
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
