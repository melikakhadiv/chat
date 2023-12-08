package com.chat.controller.session;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {
     private static int online,visited;
     @Override
     public void sessionCreated(HttpSessionEvent event){
         System.out.println("Session Created");
         online++;
         visited++;
         System.out.println("Visited :"+visited);
         System.out.println("Online: "+ online);
         HttpSessionListener.super.sessionCreated(event);


}
     @Override
     public  void sessionDestroyed(HttpSessionEvent event){
         System.out.println("Session Destroyed");
         SessionManager.removeHttpSession(event.getSession());
         online--;
         HttpSessionListener.super.sessionDestroyed(event);
     }
}
