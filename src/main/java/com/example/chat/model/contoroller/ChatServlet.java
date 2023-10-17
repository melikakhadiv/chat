package com.example.chat.model.contoroller;

import com.example.chat.model.entity.Chat;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.ChatService;
import com.example.chat.model.service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.inject.Inject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "ChatServlet", urlPatterns = "/ChatServlet")
public class ChatServlet extends HttpServlet {

    @Inject
    private ChatService chatService;
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User sender = (User) req.getSession().getAttribute("user");
            User receiver = userService.findByUsername(req.getParameter("receiver"));
            String message = req.getParameter("message");
            LocalDateTime msgTime = LocalDateTime.now();
            Chat chat = Chat
                    .builder()
                    .sender(sender)
                    .receiver(receiver)
                    .message(message)
                    .date(msgTime)
                    .active(true)
                    .build();
            chatService.save(chat);
            // WsServer.sendMessage(message, (Session) req.getSession().getAttribute("session"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("username");
                chatService.removeChatsByUserName(username);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}


