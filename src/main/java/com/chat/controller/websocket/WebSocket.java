package com.chat.controller.websocket;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Chat;
import com.chat.model.entity.User;
import com.chat.model.service.ChatService;
import com.chat.model.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint(value = "/chat",
        configurator = GetHttpSessionConfigurator.class,
        encoders = {MessageModelEncoder.class},
        decoders = {MessageModelDecoder.class})
@RequestScoped
public class WebSocket {
    @Inject
    ChatService chatService;
    @Inject
    UserService userService;

    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionManager.addWebSocketSession(String.valueOf(httpSession.getAttribute("username")), session);
        System.out.println("web socket opened " + session.getId());
//        String msg = "--new user joined!--";
//        broadcast(msg);
    }

    @OnMessage
    public void onMessage(Session session, Chat chat) throws Exception {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
//        SessionManager.getWebSocketSession(chat.getReceiver().getUsername()).getBasicRemote().sendText(chat.getMessage());
        session.getBasicRemote().sendObject(chat);
//        send();
        httpSession.setAttribute("message", chat.getMessage());
        httpSession.setAttribute("sender", HttpSession.class.getName());
        broadcast(chat);
//        User sender = userService.findByUsername(chat.getUsername());
        //todo: set receiver
//        chat.setSender(sender);
//        chatService.save(chat);
        System.out.println("user" + session.getId() + "sent :" + chat);
    }
//todo: how to choose broadcast or private send in js onMessage
    public static void broadcast(Chat chat) throws Exception {
        for (Session socketSessions : SessionManager.getWebSocketSessions()) {
          socketSessions.getBasicRemote().sendObject(chat);
        }
    }

    public void send(Session session, Chat chat) throws Exception {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionManager.getWebSocketSession(String.valueOf(httpSession.getAttribute("username"))).getBasicRemote().sendObject(chat);
    }


    @OnClose
    public void onClose(Session session) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionManager.invalidateHttpSession(String.valueOf(httpSession.getAttribute("username")));
        System.out.println("web socket closed " + session.getId());
    }
}
