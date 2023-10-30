package com.example.chat.controller.servlet;

import com.example.chat.model.entity.Attachment;
import com.example.chat.model.entity.Role;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.AttachmentService;
import com.example.chat.model.service.ChatService;
import com.example.chat.model.service.RoleService;
import com.example.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/Chat")
public class ChatServlet extends HttpServlet {
    @Inject
    private ChatService chatService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("chatList", chatService.findAll());
            resp.sendRedirect("/chat.jsp");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String message = req.getParameter("message");
            User sender= (User) req.getSession().getAttribute("user");

//            User receiver=req.getSession();


            HttpSession httpSession = req.getSession();
            resp.sendRedirect("/chat.jsp");


        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
