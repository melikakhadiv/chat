package com.chat.controller.api;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.User;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.websocket.Session;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/users")
public class UserApi {
    @Inject
    UserService userService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() throws Exception {
        try {
            List<User> userList = userService.findAll();
            Set<String> onlineUsers = SessionManager.getOnlineUsers();
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("users", userList);
            responseMap.put("onlineUsers", onlineUsers);
            return Response.ok().entity(responseMap).build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.ok().status(402).build();
        }
    }
}
