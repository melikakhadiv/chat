package com.example.chat.model.controller;

import com.example.chat.model.entity.Attachment;
import com.example.chat.model.entity.Role;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.AttachmentService;
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

@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Inject
    private RoleService roleService;
    private AttachmentService attachmentService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
              String username= req.getParameter("username");
              String password=req.getParameter("password");
              String nickname=req.getParameter("nickname");
              String firstname=req.getParameter("firstname");
              String lastname=req.getParameter("lastname");
              Role role= RoleService.findById(req.getParameter("role"));
              Attachment attachment= AttachmentService.findById(req.getParameter("attachment"));


              User user = User.builder()
                          .username(username)
                          .password(password)
                           .nickname(nickname)
                          .firstname(firstname)
                          .lastname(lastname)
                          .role(role)
                           .attachment(attachment)
                          .build();

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User",user);
            resp.sendRedirect("/panel.jsp");

            resp.getWriter().println("User saved.");




        }
        catch (Exception e){
            System.out.println("Error"+e.getMessage());
        }
    }
}
