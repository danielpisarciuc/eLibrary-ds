package com.elibrary.server.controller;


import com.elibrary.server.utils.LibraryMessage;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/app")
@Controller
public class ApplicationInfo {

    @GET
    @Path("/info")
    @Produces(MediaType.TEXT_PLAIN)
    public Response applicationInfo() {
        return Response.status(200).entity(LibraryMessage.APPLICATION_INFO.getMessage()).build();
    }
}

