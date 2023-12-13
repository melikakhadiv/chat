package com.chat.controller.websocket;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Chat;
import com.chat.model.service.ChatService;
import com.chat.model.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;

@ServerEndpoint(value = "/chat",
        configurator = GetHttpSessionConfigurator.class,
        encoders = {MessageModelEncoder.class},
        decoders = {MessageModelDecoder.class})
@RequestScoped
public class WebSocket {


    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException {
        SessionManager.addWebSocketSession(String.valueOf(session.getUserProperties().get("username")), session);
        System.out.println("session opened: " + session.getId() + " username: " + session.getUserProperties().get("username"));
    }

    @OnMessage
    public void onMessage(Session session, String chat) throws Exception {
        System.out.println("onMessage: " + chat);
    }

    public static void broadcast(String chat) throws Exception {
        System.out.println("websocket broadcast");
        for (Session socketSessions : SessionManager.getWebSocketSessions()) {
            socketSessions.getBasicRemote().sendText(chat);
        }
    }

    public static void send(String username, String chat) throws Exception {
        System.out.println("private message to: " + username + " message: " + chat);
        SessionManager.getWebSocketSessionMap().get(username).getBasicRemote().sendText(chat);
    }


    @OnClose
    public void onClose(Session session) {
        SessionManager.onClose(String.valueOf(session.getUserProperties().get("username")));
        System.out.println("session : " + session.getUserProperties().get("username") + " closed.");
    }
}
