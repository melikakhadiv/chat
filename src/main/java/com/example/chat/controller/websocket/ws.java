package com.example.chat.controller.websocket;

import com.example.chat.controller.session.SessionManager;
import com.example.chat.model.entity.Chat;
import com.example.chat.model.util.HTMLFilter;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value = "/chat", encoders = MessageModelEncoder.class, decoders = MessageModelDecoder.class, configurator = GetHttpSessionConfigurator.class)
public class ws {
//    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());


    @OnMessage
    public String onMessage(Session session, Chat chat) throws EncodeException, IOException {
        System.out.println("message : " + chat);
//        for (Session s : sessions) {
//                s.getBasicRemote().sendObject(chat);
//
//        }
        return chat.getMessage();
    }


    @OnOpen
    public void onOpen(Session session) {
        System.out.println("ws opened: " + session.getId());
        HttpSession httpSession = (HttpSession) session.getUserProperties()
                .get(HttpSession.class.getName());
        SessionManager.addWebSocketSession(httpSession, session);
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("ws closed: " + session.getId());
        SessionManager.invalidateHttpSession((HttpSession) session.getUserProperties()
                .get(HttpSession.class.getName()), session);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {
    }

    public static void send(HttpSession httpSession, String msg) throws IOException {
        SessionManager.getWebSocketSession(httpSession).getBasicRemote().sendText(msg);
    }

    public static void broadcast(String message) throws IOException {
        for (Map<HttpSession, Session> webSocketSession : SessionManager.getWebSocketSessions()) {
            webSocketSession.get("session").getBasicRemote().sendText(message);
        }
    }

}
