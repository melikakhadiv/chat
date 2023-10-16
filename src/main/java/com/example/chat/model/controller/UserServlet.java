package com.example.chat.model.controller;

import com.example.chat.model.entity.User;
import com.example.chat.model.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().println("User saved.");
        try {
              String username= req.getParameter("username");
              String password=req.getParameter("password");
              String nickname=req.getParameter("nickname");
              String firstname=req.getParameter("firstname");
              String lastname=req.getParameter("lastname");

              User user = User.builder()
                          .username(username)
                          .password(password)
                           .nickname(nickname)
                          .firstname(firstname)
                          .lastname(lastname)
                          .build();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User",user);
            resp.sendRedirect("/panel.jsp");
            resp.getWriter().println("Attachment successfully saved");



            );
        }
        catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
}
