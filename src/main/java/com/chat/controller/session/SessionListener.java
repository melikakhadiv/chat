package com.chat.controller.session;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionListener implements HttpSessionListener {
    private static int online, visited;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        log.info("SessionListener-Created");
        online++;
        visited++;
        log.info("SessionListener-Visited:" + visited);
        log.info("SessionListener-Online:" + online);
        HttpSessionListener.super.sessionCreated(event);


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        log.info("SessionListener-Destroyed");
        SessionManager.removeHttpSession(event.getSession());
        online--;
        HttpSessionListener.super.sessionDestroyed(event);
    }
}
