package com.chat.controller.api;

import com.chat.controller.session.SessionManager;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/chat")
public class ChatApi {
    @Inject
    private ChatService chatService;

    @Inject
    private UserService userService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/private/{receiver}/{sender}/{message}")
    public Response setPrivateChat(@PathParam("receiver") String receiver,
                                   @PathParam("sender") String sender,
                                   @PathParam("message") String message) throws Exception {
        try {
            System.out.println("----Api private----");
            System.out.println(" receiver: " + receiver);
            System.out.println(" sender: " + sender);
            System.out.println(" message: " + message);
            String status = null;
            User senderUser = userService.findByUsername(sender);
            User receiverUser = userService.findByUsername(receiver);
            Chat chat = Chat.builder().message(message).sender(senderUser).receiver(receiverUser).build();
            chatService.save(chat);
            WebSocket.send(receiver, message);
            System.out.println("private: " + message);
            chat.setReceived(SessionManager.getOnlineUsers().contains(receiver));
            return Response.ok().entity(chat).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/broadcast/{sender}/{broadcastMsg}")
    public Response setBroadcast(@PathParam("broadcastMsg") String broadcastMsg,
                                 @PathParam("sender") String sender) {
        try {
            System.out.println("---Api broadcast---");
            User senderUser = userService.findByUsername(sender);
            Set<String> onlineUsers = SessionManager.getOnlineUsers();
            for (String username : onlineUsers) {
                User receiver = userService.findByUsername(username);
                Chat chat = Chat.builder()
                        .sender(senderUser)
                        .receiver(receiver)
                        .message(broadcastMsg)
                        .build();
                chatService.save(chat);
            }
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
    @Path("/history/{receiver}/{sender}")
//    @Path("/history/{other}")
    public Response getChatBySenderReceiver(@PathParam("sender") String sender,
                                            @PathParam("receiver") String receiver) {
        try {
            System.out.println(" receiver: " + receiver);
            System.out.println(" sender: " + sender);
            List<Chat> chatList = chatService.findBySenderAndReceiver(sender, receiver);
            System.out.println("chat: " + chatList);
            return Response.ok().entity(chatList).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}