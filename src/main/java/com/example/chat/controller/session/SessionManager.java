package com.example.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Getter;

import java.util.*;

public class SessionManager {
    @Getter
    public static Map<String, HttpSession> sessionMap = new HashMap<>();

    public static void addWebSocketSession(String username, Session wsSession) {
        sessionMap.get(username).setAttribute("wsSession", wsSession);
    }

    public static Set<Session> getWebSocketSessions() {
        Set<Session> sessionSet = new HashSet<>();
        for (HttpSession httpSession : sessionMap.values()) {
            sessionSet.add((Session) httpSession.getAttribute("wsSession"));
        }
        return sessionSet;
    }

    public static Session getWebSocketSession(String username) {
        return (Session) sessionMap.get(username).getAttribute("wsSession");
    }

    public static void addHttpSession(HttpSession httpSession) {
        sessionMap.put(String.valueOf(httpSession.getAttribute("username")), httpSession);
    }

    public static boolean validateHttpSession(HttpSession httpSession) {
        return sessionMap.containsValue(httpSession);
    }

    public static boolean validateWebSocketSession(Session session) {
        for (HttpSession httpSession : sessionMap.values()) {
            if(httpSession.getAttribute("wsSession")==session){
                return true;
            }
        }
        return false;
    }

    public static boolean invalidateHttpSession(String username) {
        if (sessionMap.containsKey(username)) {
            sessionMap.get(username).invalidate();
            return true;
        }
        return false;
    }

    public static Set<HttpSession> getHttpSessions() {
        return (Set<HttpSession>) sessionMap.values();
    }

    public static Set<String> getUsernames() {
        return sessionMap.keySet();
    }

}

