package com.chat.controller.servlet;

import com.chat.controller.exception.ExceptionHandler;
import com.chat.controller.websocket.WebSocket;
import com.chat.model.entity.Chat;
import com.chat.model.entity.User;
import com.chat.model.service.ChatService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@WebServlet(urlPatterns = "/chat")
@Slf4j
public class ChatServlet extends HttpServlet {
    @Inject
    private ChatService chatService;
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            String role = (String) req.getSession().getAttribute("role");
            log.info("Chat-Servlet-Get");
            req.getRequestDispatcher("/user-panel").forward(req, resp);
        } catch (Exception e) {
            log.error("Chat-Servlet-Get-"+ ExceptionHandler.getException().getMessage(e));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String sendToAllBtn = req.getParameter("sendToAllBtn");
            String privateSendBtn = req.getParameter("privateSendBtn");
            User sender = userService.findByUsername((String) req.getSession().getAttribute("username"));
            req.setAttribute("sender", sender);
            if (sendToAllBtn != null) {
                String broadcastMsg = req.getParameter("broadcastMsg");
                User receiver = userService.findByUsername("admin");

                Chat chat = Chat.builder().
                        message(broadcastMsg)
                        .sender(sender)
                        .receiver(receiver)
                        .build();
                chatService.save(chat);
                req.setAttribute("groupChat", chat);
                log.info("Chat-Servlet-Post-Broadcast");
                WebSocket.broadcast(broadcastMsg);
            } else if (privateSendBtn != null) {
                String message = req.getParameter("message");
                User receiver = userService.findByUsername(req.getParameter("receiver"));
                Chat chat = Chat.builder().
                        message(message)
                        .sender(sender)
                        .receiver(receiver)
                        .build();
                chatService.save(chat);
                req.setAttribute("chat", chat);
                log.info("Chat-Servlet-Post-Private");
                WebSocket.send(req.getParameter("receiver"), req.getParameter("message"));
            }
            req.getRequestDispatcher("/user-panel").forward(req,resp);
        } catch (Exception e) {
            log.error("Chat-Servlet-Post-" + ExceptionHandler.getException().getMessage(e));
        }
    }

}
