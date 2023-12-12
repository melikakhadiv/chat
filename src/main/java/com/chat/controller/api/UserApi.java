package com.chat.controller.api;

import com.chat.controller.session.SessionManager;
import jakarta.websocket.Session;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.Set;

@Path("/users")
public class UserApi {

    @GET
//    @Path("/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
//        return Response.ok().entity(SessionManager.getOnlineUsers().remove(username)).build();
        return Response.ok().entity(SessionManager.getOnlineUsers()).build();
    }
}
