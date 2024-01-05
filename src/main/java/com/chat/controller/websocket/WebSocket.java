package com.chat.controller.websocket;

import com.chat.controller.session.SessionManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

@ServerEndpoint(value = "/chat",
        configurator = GetHttpSessionConfigurator.class,
        encoders = {MessageModelEncoder.class},
        decoders = {MessageModelDecoder.class})
@RequestScoped
@Slf4j
public class WebSocket {


    @OnOpen
    public void onOpen(Session session) {
        SessionManager.addWebSocketSession(String.valueOf(session.getUserProperties().get("username")), session);
        log.info("Websocket-onOpen-Id:" + session.getId() + "-Username:" + session.getUserProperties().get("username"));
    }

    @OnMessage
    public void onMessage(Session session, String chat) throws Exception {
        log.info("Websocket-onMessage-Username:" + session.getUserProperties().get("username") + "-Message:" + chat);
    }

    public static void broadcast(String chat) throws Exception {
        log.info("Websocket-Broadcast-Message:" + chat);
        for (Session socketSessions : SessionManager.getWebSocketSessions()) {
            socketSessions.getBasicRemote().sendText(chat);
        }
    }

    public static void send(String username, String chat) throws Exception {
        log.info("Websocket-Private-Receiver:" + username + "-Message:" + chat);
        SessionManager.getWebSocketSessionMap().get(username).getBasicRemote().sendText(chat);
    }


    @OnClose
    public void onClose(Session session) {
        SessionManager.onClose(String.valueOf(session.getUserProperties().get("username")));
        log.info("Websocket-onClose-Id:" + session.getId() + "-Username:" + session.getUserProperties().get("username"));
    }
}
