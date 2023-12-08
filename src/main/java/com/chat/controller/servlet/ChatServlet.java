package com.chat.controller.servlet;

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
        req.getRequestDispatcher("chat.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String message = req.getParameter("message");
            User sender = userService.findByUsername(req.getParameter("sender"));
            User receiver=userService.findByUsername(req.getParameter("receiver"));
            Chat chat = Chat.builder().
                    message(message)
                    .sender(sender)
                    .receiver(receiver)
                    .build();
            chatService.save(chat);
            System.out.println("receiver:" + receiver);
            HttpSession httpSession = req.getSession();
            resp.sendRedirect("/jsp/" + sender.getRole().getRole() + "/panel.jsp");


        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
