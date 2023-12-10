package com.chat.controller.servlet;

import com.chat.controller.websocket.WebSocket;
import com.chat.model.entity.Chat;
import com.chat.model.entity.User;
import com.chat.model.service.ChatService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sound.midi.Receiver;
import java.io.IOException;

@WebServlet(urlPatterns = "/chat")
public class ChatServlet extends HttpServlet {
    @Inject
    private ChatService chatService;
    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            req.getSession().setAttribute("chatList", chatService.findAll());
//            resp.sendRedirect("/jsp/admin/panel.jsp");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String role = req.getParameter("role");
        req.getRequestDispatcher("/user-panel").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            User sender = userService.findByUsername(req.getParameter("username"));
            System.out.println("debug sender: " + req.getUserPrincipal().getName());

            String sendBtn = req.getParameter("sendBtn");
            String sendToAllBtn = req.getParameter("sendToAllBtn");
            if (sendBtn != null) {
                System.out.println("private message");
                String message = req.getParameter("message");
                System.out.println("debug message: " + message);

//                User receiver = userService.findByUsername(req.getParameter("receiver"));
                System.out.println("debug receiver: " + req.getParameter("receiver"));
// TODO: 12/10/2023 save a chat 
//                Chat chat = Chat.builder().
////                        message(message)
//                        .sender(sender)
//                        .receiver(receiver)
//                        .build();
//                chatService.save(chat);

                WebSocket.send(req.getParameter("receiver"), req.getParameter("message"));
                resp.sendRedirect("/user-panel");
            } else if (sendToAllBtn != null) {
                System.out.println("private message");
                String broadcastMsg = req.getParameter("broadcastMsg");
                System.out.println("broadcast message: " + broadcastMsg);
                WebSocket.broadcast(broadcastMsg);
                resp.sendRedirect("/user-panel");
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }

}
