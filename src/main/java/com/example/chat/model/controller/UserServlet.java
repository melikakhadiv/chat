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
import java.lang.annotation.Repeatable;

@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Inject
    private RoleService roleService;
    private UserService userService;
    private AttachmentService attachmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("userList", userService.findAll());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            Role role = RoleService.findById(req.getParameter("role"));
            Attachment attachment = AttachmentService.findById(req.getParameter("attachment"));

            User user = User.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
                    .role(role)
                    .photo(attachment)
                    .build();
            userService.save(user);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User", user);
            resp.sendRedirect("/panel.jsp");

            resp.getWriter().println("User saved.");


        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String active = req.getParameter("active");
            Role role = RoleService.findById(req.getParameter("role"));
            Attachment attachment = AttachmentService.findById(req.getParameter("attachment"));

            User user = User.builder()
                    .id(Long.parseLong(id))
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
                    .active(Boolean.parseBoolean(active))
                    .role(role)
                    .photo(attachment)
                    .build();

            userService.edit(user);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User", user);
            resp.sendRedirect("/panel.jsp");

            resp.getWriter().println("User edited.");


        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            userService.remove(Long.valueOf(req.getParameter("id")));
            resp.sendRedirect("/panel.jsp");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
