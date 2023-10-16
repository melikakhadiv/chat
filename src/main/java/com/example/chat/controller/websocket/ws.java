//package com.example.chat.controller.websocket;
//
//import com.example.chat.model.entity.Chat;
//import jakarta.websocket.*;
//import jakarta.websocket.server.ServerEndpoint;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//@ServerEndpoint(value = "/chat", configurator = GetHttpSessionConfigurator.class)
//public class ws {
//    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
//
//    @OnMessage
//    public String onMessage(Session session , Chat chat) throws EncodeException, IOException {
//        System.out.println("message : " + chat);
//        for (Session s : sessions) {
//                s.getBasicRemote().sendObject(chat);
//
//        }
//        return chat.getMessage();
//    }
//
//    @OnOpen
//    public void  onOpen(Session session){
//        System.out.println("ws opend: " + session.getId());
//        sessions.add(session);
//    }
//
//    @OnClose
//    public void  onClose(Session session){
//        System.out.println("ws closed: " + session.getId());
//        sessions.remove(session);
//    }
//}
