package com.example.chat.controller.servlet;

import com.example.chat.model.entity.Attachment;
import com.example.chat.model.entity.Role;
import com.example.chat.model.entity.User;
import com.example.chat.model.service.AttachmentService;
import com.example.chat.model.service.RoleService;
import com.example.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Repeatable;
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Inject
    private RoleService roleService;
    @Inject
    private UserService userService;
    @Inject
    private AttachmentService attachmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("userList", userService.findAll());
            resp.sendRedirect("/jsp/admin/panel.jsp");
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
//            boolean privateAcc = Boolean.parseBoolean(req.getParameter("privateAcc"));
//            Attachment attachment = attachmentService.findById(Long.valueOf(req.getParameter("attachmentId")));


            User user = User.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
//                    .privateAccount(privateAcc)
//                    .photo(attachment)
                    .build();
            userService.save(user);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User", user);
            resp.sendRedirect("/login.jsp");
            System.out.println("person saved "+ user);
            resp.getWriter().println("User saved.");


        } catch (Exception e) {
            resp.sendError(403);
            e.printStackTrace();
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            boolean privateAcc = Boolean.parseBoolean(req.getParameter("privateAcc"));
            boolean active = Boolean.parseBoolean(req.getParameter("active"));
            Attachment attachment = attachmentService.findById(Long.valueOf(req.getParameter("attachmentId")));

            User user = User.builder()
                    .id(id)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
                    .privateAccount(privateAcc)
                    .active(active)
                    .photo(attachment)
                    .build();

            userService.edit(user);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User", user);
            resp.sendRedirect("/jsp/" + user.getRole().getRole() + "/panel.jsp");
            resp.getWriter().println("User edited.");


        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userService.remove(Long.valueOf(req.getParameter("id")));
            resp.sendRedirect("/jsp/admin/panel.jsp");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
