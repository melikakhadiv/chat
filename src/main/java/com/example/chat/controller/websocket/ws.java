//package com.example.chat.controller.websocket;
//
//
//import com.example.chat.controller.session.SessionMager;
//import com.example.chat.model.entity.Chat;
//import jakarta.servlet.http.HttpSession;
//import jakarta.websocket.*;
//import jakarta.websocket.server.ServerEndpoint;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//@ServerEndpoint(value = "/chat",
//        configurator = GetHttpSessionConfigurator.class,
//        encoders = MessageModelEncoder.class,
//        decoders = MessageModelDecoder.class)
//public class ws {
//
//    public ws() {
//        System.out.println("web socket");
//    }
//
//    private static Set<Session> sessions = new HashSet<>();
//
//    public static void broadcast(String message) throws IOException {
//        for (Map<HttpSession, Session> webSocketSession : SessionMager.getWebSocketSessions()) {
//            webSocketSession.get("session").getBasicRemote().sendText(message);
//        }
//    }
//
//    @OnMessage
//    public void onMessage(Session session, String chat) throws EncodeException, IOException {
//        System.out.println("Stock information received: " + chat + " from " + session.getId());
//        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
//        httpSession.setAttribute("message", chat);
//        httpSession.setAttribute("senderUsername", HttpSession.class.getName());
//        session.getBasicRemote().sendObject(chat);
//    }
//
//    @OnOpen
//    public void onOpen(Session session) {
//        System.out.println("ws opened: " + session.getId());
//        HttpSession httpSession = (HttpSession) session.getUserProperties()
//                .get(HttpSession.class.getName());
//        SessionMager.addWebSocketSession(httpSession, session);
//        httpSession.setAttribute("socketSession", session);
//        sessions.add(session);
//    }
//
//    public static void send(Session session, String message) throws IOException {
//        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
//        SessionMager.getWebSocketSession(httpSession).getBasicRemote().sendText(message);
//        session.getBasicRemote().sendText(message);
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        System.out.println("WebSocket error for " + session.getId() + " " + throwable.getMessage());
//    }
//
//    @OnClose
//    public void onClose(Session session, CloseReason closeReason) {
//        System.out.println("WebSocket closed for " + session.getId() + " with reason " + closeReason.getCloseCode());
//        sessions.remove(session);
//    }
//
//}
