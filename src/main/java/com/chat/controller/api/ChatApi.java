package com.chat.controller.api;

import com.chat.model.entity.Chat;
import com.chat.model.entity.User;
import com.chat.model.service.ChatService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chat")
public class ChatApi {
    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;

    // @GET
    //@Produces(MediaType.APPLICATION_JSON)
    //public Response getChatsByUsername(String username){
    //  return Response.ok().entity(chatService.findByUsername(username)).build();
    //}

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{receiver}/{sender}/{message}")
    public Response setChat(@PathParam("receiver") String receiver,
                            @PathParam("sender") String sender,
                            @PathParam("message") String message) throws Exception {
        try {
            System.out.println(" aaaa " + receiver);
            System.out.println(" bbbb " + sender);
            System.out.println(" cccc " + message);
            User senderUser = userService.findByUsername(sender);
            User receiverUser = userService.findByUsername(receiver);
            Chat chat = Chat.builder().message(message).sender(senderUser).receiver(receiverUser).build();
//          System.out.println("chat saved : " + chat);
            return Response.ok().entity(chatService.save(chat)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChatBySenderReceiver(String sender, String receiver) {
        try {

            return Response.ok().entity(chatService.findBySenderAndReceiver(sender, receiver)).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}