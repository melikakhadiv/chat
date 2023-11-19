package com.example.chat.controller.websocket;

import com.example.chat.controller.session.SessionM;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat",
        configurator = GetHttpSessionConfigurator.class,
        encoders = {MessageModelEncoder.class},
        decoders = {MessageModelDecoder.class})

public class WebSocket {

    private static Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionM.addWebSocketSession(httpSession, session);
        httpSession.setAttribute("socketSession", session);
        sessions.add(session);
        System.out.println("web socket opened " + session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String msg) throws EncodeException, IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        httpSession.setAttribute("message", msg);
        httpSession.setAttribute("sender", HttpSession.class.getName());
        session.getBasicRemote().sendObject(msg);
        System.out.println("user" + session.getId() + "sent :" + msg);
    }

    public static void broadcast(String msg) throws EncodeException, IOException {
        for (Session socketSessions : SessionM.getWebSocketSessions()) {
          socketSessions.getBasicRemote().sendObject(msg);
        }
    }

    public static void send(Session session, String message) throws IOException {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        SessionM.getWebSocketSession(httpSession).getBasicRemote().sendText(message);
        session.getBasicRemote().sendText(message);
    }


    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("web socket closed " + session.getId());
    }
}
