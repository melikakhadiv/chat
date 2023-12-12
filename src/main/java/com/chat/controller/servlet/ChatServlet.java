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
        try {
//            req.getSession().setAttribute("chatList", chatService.findAll());
//            resp.sendRedirect("/jsp/admin/panel.jsp");
            String role = (String) req.getSession().getAttribute("role");
            System.out.println("GET CHAT SERVLET");
            req.getRequestDispatcher("/user-panel").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sendToAllBtn = req.getParameter("sendToAllBtn");
            String privateSendBtn = req.getParameter("privateSendBtn");
            User sender = userService.findByUsername((String) req.getSession().getAttribute("username"));
            req.setAttribute("sender", sender);
            if (sendToAllBtn != null) {
                String broadcastMsg = req.getParameter("broadcastMsg");
                System.out.println("debug chat servlet broadcast: " + broadcastMsg);
                User receiver = userService.findByUsername("admin");

                Chat chat = Chat.builder().
                        message(broadcastMsg)
                        .sender(sender)
                        .receiver(receiver)
                        .build();
                chatService.save(chat);
                req.setAttribute("groupChat", chat);
                WebSocket.broadcast(broadcastMsg);
            } else if (privateSendBtn != null) {
                System.out.println("debug chat servlet sender: " + req.getUserPrincipal().getName());

                String message = req.getParameter("message");
                System.out.println("debug chat servlet message: " + message);

                User receiver = userService.findByUsername(req.getParameter("receiver"));
                System.out.println("debug chat servlet receiver: " + req.getParameter("receiver"));

                Chat chat = Chat.builder().
                        message(message)
                        .sender(sender)
                        .receiver(receiver)
                        .build();
                chatService.save(chat);
                req.setAttribute("chat", chat);
                System.out.println("debug chat servlet chat saved:" + chat);
                WebSocket.send(req.getParameter("receiver"), req.getParameter("message"));
            }
            req.getRequestDispatcher("/user-panel").forward(req,resp);
        } catch (
                Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();
        }
    }

}
