package com.example.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SessionManager {
    public static Map<String, Map<HttpSession, Session>> sessionMap = new HashMap<>();

    public static void addHttpSession(HttpSession httpSession) {
        Map<HttpSession, Session> session = new HashMap<>();
        session.put(httpSession, null);
        sessionMap.put(httpSession.getAttribute("username").toString(), session);
    }

    public static void addWebSocketSession(HttpSession httpSession, Session session) {
        sessionMap.get(httpSession.getAttribute("username").toString()).put(httpSession, session);
    }

    public static boolean validateHttpSession(HttpSession httpSession) {
        return sessionMap.get(httpSession.getAttribute("username").toString()).containsKey(httpSession);
    }

    public static boolean validateWebSocketSession(HttpSession httpSession, Session session) {
        return sessionMap.get(httpSession.getAttribute("username").toString()).containsKey(session);
    }

    public static void invalidateHttpSession(HttpSession httpSession, Session session) {
        sessionMap.remove(httpSession.getAttribute("username").toString());
    }

    public static Session getWebSocketSession(HttpSession httpSession) {
        return sessionMap.get(httpSession.getAttribute("username").toString()).get(httpSession);
    }

    //    public static Set<HttpSession> getHttpSessions() {
//        Set<HttpSession> httpSessions = new HashSet<>();
//        for (Map<HttpSession, Session> value : sessionMap.values()) {
//            httpSessions.add(value.keySet());
//        }
//        return sessionMap.keySet();
//    }
//
    public static Collection<Map<HttpSession, Session>> getWebSocketSessions() {
        return sessionMap.values();
    }


//    public static Map<HttpSession, Session> getSessionMap() {
//        return sessionMap;
//    }
}
