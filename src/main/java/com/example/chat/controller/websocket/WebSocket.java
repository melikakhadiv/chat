package com.example.chat.controller.websocket;

import com.example.chat.controller.session.SessionManager;
import com.example.chat.model.entity.Chat;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat",
        configurator = GetHttpSessionConfigurator.class,
        encoders = {MessageModelEncoder.class},
        decoders = {MessageModelDecoder.class})

public class WebSocket {

    public static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionManager.addWebSocketSession(httpSession, session);
        httpSession.setAttribute("socketSession", session);
        sessions.add(session);
        System.out.println("web socket opened " + session.getId());
//        String msg = "--new user joined!--";
//        broadcast(msg);
    }

    @OnMessage
    public void onMessage(Session session, Chat chat) throws EncodeException, IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        httpSession.setAttribute("message", chat.getMessage());
        httpSession.setAttribute("sender", HttpSession.class.getName());
        broadcast(chat);
        System.out.println("user" + session.getId() + "sent :" + chat);
    }

    public static void broadcast(Chat chat) throws EncodeException, IOException {
        for (Session socketSessions : SessionManager.getWebSocketSessions()) {
          socketSessions.getBasicRemote().sendObject(chat);
        }
    }

    public void send(Session session, Chat chat) throws IOException, EncodeException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionManager.getWebSocketSession(httpSession).getBasicRemote().sendObject(chat);
        session.getBasicRemote().sendObject(chat);
    }


    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("web socket closed " + session.getId());
    }
}
