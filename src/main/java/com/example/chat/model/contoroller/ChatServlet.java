package com.example.chat.model.contoroller;

import com.example.chat.model.entity.Chat;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.ChatService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ChatServlet",urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
                String username = req.getSession().getAttribute("username").toString();
                String message = req.getParameter("message");
                // WsServer.sendMessage(message, (Session) req.getSession().getAttribute("session"));
        }
catch (Exception e) {
        System.out.println(e.getMessage());
        }}}

