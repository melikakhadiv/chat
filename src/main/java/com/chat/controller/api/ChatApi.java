package com.chat.controller.api;

import com.chat.controller.websocket.WebSocket;
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

import java.util.List;

@Path("/chat")
public class ChatApi {
    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{receiver}/{sender}/{message}")
    public Response setPrivateChat(@PathParam("receiver") String receiver,
                                   @PathParam("sender") String sender,
                                   @PathParam("message") String message) throws Exception {
        try {
            System.out.println("----Api private----");
            System.out.println(" receiver: " + receiver);
            System.out.println(" sender: " + sender);
            System.out.println(" message: " + message);
            User senderUser = userService.findByUsername(sender);
            User receiverUser = userService.findByUsername(receiver);
            Chat chat = Chat.builder().message(message).sender(senderUser).receiver(receiverUser).build();
            chatService.save(chat);
            WebSocket.send(receiver, message);
            System.out.println("private: " + message);
            return Response.ok().entity(message).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{broadcastMsg}")
    public Response setBroadcast(@PathParam("broadcastMsg") String broadcastMsg) {
        try {
            System.out.println("---Api broadcast---");
            WebSocket.broadcast(broadcastMsg);
            System.out.println("api broadcast: " + broadcastMsg);
            return Response.ok().entity(broadcastMsg).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{receiver}/{sender}")
    public Response getChatBySenderReceiver(@PathParam("sender") String sender,
                                            @PathParam("receiver") String receiver) {
        try {
            System.out.println(" receiver: " + receiver);
            System.out.println(" sender: " + sender);
            List<Chat> chat = chatService.findBySenderAndReceiver(sender, receiver);
            System.out.println("chat: " + chat);
            return Response.ok().entity(chat).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}