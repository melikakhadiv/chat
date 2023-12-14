package com.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.*;

public class SessionManager {
    private static Map<String , HttpSession> httpSessionMap = new HashMap<>();
    private static Map<String , Session> webSocketSessionMap = new HashMap<>();

    public static void addHttpSession(HttpSession httpSession){
        httpSessionMap.put(String.valueOf(httpSession.getAttribute("username")), httpSession);
    }

    public static void addWebSocketSession(String username,Session session){
        webSocketSessionMap.put(username, session);
    }

    public static void removeHttpSession(HttpSession httpSession){
        httpSessionMap.remove(httpSession.getAttribute("username"));
    }

    public static void removeWebSocketSession(Session session){
        webSocketSessionMap.remove(session);
    }

    public static void onClose(String username){
        webSocketSessionMap.remove(username);
        httpSessionMap.remove(username);
        httpSessionMap.get(username).invalidate();
    }

    public static boolean isLoggedInUser(String username){
        return httpSessionMap.containsKey(username);
    }

    public static Set<String> getOnlineUsers(){
        return httpSessionMap.keySet();
    }


    public static Collection<HttpSession> getHttpSessions(){
        return (Collection<HttpSession>) httpSessionMap.values();
    }

    public static Collection<Session> getWebSocketSessions(){
        return (Collection<Session>) webSocketSessionMap.values();
    }

    public static Map<String, HttpSession> getHttpSessionMap() {
        return httpSessionMap;
    }

    public static Map<String, Session> getWebSocketSessionMap() {
        return webSocketSessionMap;
    }
}
