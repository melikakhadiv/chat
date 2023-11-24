package com.chat.controller.servlet;

import com.chat.model.entity.Attachment;
import com.chat.model.entity.Role;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRole;
import com.chat.model.entity.enums.FileType;
import com.chat.model.service.AttachmentService;
import com.chat.model.service.RoleService;
import com.chat.model.service.UserRoleService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.swing.*;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Inject
    private UserRoleService UserRoleService;

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
        System.out.println("User Post");
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String nickname = req.getParameter("nickname");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            Role role = roleService.findByRole("customer");
            String privateAcc = req.getParameter("privateAcc");
            boolean account = false;
            account = privateAcc != null;
            Part filePart = req.getPart("file");
            String fileName = "jsp/customer/image/" + filePart.getSubmittedFileName();
            System.out.println(fileName);
            System.out.println(getServletContext().getRealPath("/") + fileName);
            for (Part part : req.getParts()) {
                part.write(getServletContext().getRealPath("/") + fileName);
            }

            Attachment attachment = Attachment.builder().title("User Image").filePath(fileName).active(true).build();

            User user = User.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
                    .role(role)
                    .active(true)
                    .privateAccount(account)
                    .photo(attachment)
                    .build();
            userService.save(user);

            UserRole userRole = UserRole.builder().roleName("customer").username(user.getUsername()).build();
            UserRoleService.save(userRole);

            resp.sendRedirect("/user-panel");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
            resp.sendError(403);
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
            resp.sendRedirect("/user-panel");
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
