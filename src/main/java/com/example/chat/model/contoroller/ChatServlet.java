package com.example.chat.model.contoroller;

import com.example.chat.model.entity.Chat;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.ChatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ChatServlet",urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                String username = req.getSession().getAttribute("username").toString();
                String message = req.getParameter("message");
                String receiver=req.getParameter("reciver");
                String sender=req.getParameter("sender");
                String date=req.getParameter("date");
                User user=User.builder().username(receiver).build();
                User user1=User.builder().username(sender).build();
                Chat chat=Chat.builder().message(message).date(LocalDateTime.parse(date)).receiver(user).
                        sender(user1).build();

                // WsServer.sendMessage(message, (Session) req.getSession().getAttribute("session"));
        }
catch (Exception e) {
        System.out.println(e.getMessage());
        }}}

