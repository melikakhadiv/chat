package com.chat.controller.api;


import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.openejb.server.httpd.HttpRequest;

@Path("/users")
public class UserApi {
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        return Response.ok().entity(userService.findOnlineUsers()).build();
    }
}
