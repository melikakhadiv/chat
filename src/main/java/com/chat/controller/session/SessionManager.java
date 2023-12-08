package com.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SessionManager {
    private static Map<String, HttpSession> httpSessionMap = new HashMap<>();
    private static Map<String , Session> webSessionMap = new HashMap<>();

    public static void addHttpSession(HttpSession httpSession){
        httpSessionMap.put((String) httpSession.getAttribute("username"),httpSession);
    }

    public static void addWebSocketSession(String username , Session session){
        webSessionMap.put(username,session);
    }

    public static void removeHttpSession(HttpSession httpSession){
        httpSessionMap.remove(httpSession.getAttribute("username"));
    }

    public static void removeWebSocketSession(Session session){
        webSessionMap.remove(session);
    }

    public static void onClose(String username){
        webSessionMap.remove(username);
        httpSessionMap.remove(username);
    }

    public static Set<String> getUsers(){
        return httpSessionMap.keySet();
    }

    public static Set<HttpSession> getHttpSessions(){
        return (Set<HttpSession>) httpSessionMap.values();
    }

    public static Set<Session> getWebSocketSessions(){
        return (Set<Session>) webSessionMap.values();
    }

    public static Map<String,HttpSession> getHttpSessionMap(){
        return httpSessionMap;
    }

    public static Map<String,Session> getWebSessionMap(){
        return webSessionMap;
    }
}

