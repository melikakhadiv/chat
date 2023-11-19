package com.example.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.*;


public class SessionManager {
    public static Map<HttpSession, Session> sessionMap = new HashMap<>();

    public static void addHttpSession(HttpSession httpSession) {
        sessionMap.put(httpSession, null);
    }

    public static void addWebSocketSession(HttpSession httpSession, Session Session) {
        sessionMap.put(httpSession, Session);
    }

    public static boolean validateHttpSession(HttpSession httpSession) {
        return sessionMap.containsKey(httpSession);
    }

    public static boolean validateWebSocketSession(Session session) {
        return sessionMap.containsValue(session);
    }

    public static boolean invalidateHttpSession(HttpSession httpSession) {
        if (sessionMap.containsKey(httpSession)) {
            sessionMap.remove(httpSession);
            return true;
        }
        return false;
    }

    public static Session getWebSocketSession(HttpSession httpSession) {
        return sessionMap.get(httpSession);
    }

    public static Set<HttpSession> getHttpSessions() {
        return sessionMap.keySet();
    }


    public static Collection<Session> getWebSocketSessions() {
        return sessionMap.values();
    }


    public static Map<HttpSession, Session> getSessionMap() {
        return sessionMap;
    }
}
