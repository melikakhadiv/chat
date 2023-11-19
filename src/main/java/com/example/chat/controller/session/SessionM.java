package com.example.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SessionM {

    public static Map<HttpSession, Session> sessionMap = new HashMap<>();

    public static void addWebSocketSession(HttpSession httpSession, Session session) {
        sessionMap.put(httpSession, session);
    }

    public static Collection<Session> getWebSocketSessions() {
        return sessionMap.values();
    }

    public static Session getWebSocketSession(HttpSession httpSession) {
        return sessionMap.get(httpSession);
    }
}

