package com.chat.controller.api;

import com.chat.model.service.ChatService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chat")
public class ChatApi {
    @Inject
    private ChatService chatService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatsByUsername(String username){
        return Response.ok().entity(chatService.findByUsername(username)).build();
    }
}
