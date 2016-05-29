package com.elibrary.server.controller;

import com.elibrary.server.dao.ReferenceDataDao;
import com.elibrary.server.utils.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reference-data")
@Controller
public class ReferenceDataController {

    @Autowired
    private ReferenceDataDao referenceDataDao;

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
