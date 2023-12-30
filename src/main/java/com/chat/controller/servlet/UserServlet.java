package com.chat.controller.servlet;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Attachment;
import com.chat.model.entity.Role;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRole;
import com.chat.model.service.AttachmentService;
import com.chat.model.service.RoleService;
import com.chat.model.service.UserRoleService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(urlPatterns = "/User")
@Slf4j
public class UserServlet extends HttpServlet {
    @Inject
    private UserRoleService userRoleService;

    @Inject
    private RoleService roleService;

    @Inject
    private UserService userService;

    @Inject
    private AttachmentService attachmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String logout = req.getParameter("logout");
            if (logout != null) {
                SessionManager.removeHttpSession(req.getSession());
                req.getSession().invalidate();
                log.info("User-Servlet-Get-LogOut");
                resp.sendRedirect("/index.jsp");
            }
        } catch (Exception e) {
            log.error("User-Servlet-Get-" + e.getMessage());;
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
            Role role = roleService.findByRole("customer");
            Part filePart = req.getPart("file");
            String privateAcc = req.getParameter("privateAcc");
            boolean account = privateAcc != null;

            if (filePart != null && filePart.getSize() > 0) {
                String fileName = "jsp/customer/image/" + username + "_" + filePart.getSubmittedFileName();

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
                userRoleService.save(userRole);
            } else {
                User user = User.builder()
                        .username(username)
                        .password(password)
                        .nickname(nickname)
                        .firstname(firstname)
                        .lastname(lastname)
                        .role(role)
                        .active(true)
                        .privateAccount(account)
                        .photo(null)
                        .build();
                userService.save(user);

                UserRole userRole = UserRole.builder().roleName("customer").username(user.getUsername()).build();
                userRoleService.save(userRole);
            }
            log.info("User-Servlet-Post-Saved");
            resp.sendRedirect("/user-panel");
        } catch (Exception e) {
            log.error("User-Servlet-Post-" + e.getMessage());
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

            Part filePart = req.getPart("file");
            String fileName = "jsp/customer/image/" + username + "_" + filePart.getSubmittedFileName();
            for (Part part : req.getParts()) {
                part.write(getServletContext().getRealPath("/") + fileName);
            }
            Attachment attachment = Attachment.builder().title("User Image").filePath(fileName).active(true).build();

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
            log.info("User-Servlet-Put-Edited");
            resp.sendRedirect("/user-panel");
        } catch (Exception e) {
            log.error("User-Servlet-Put-" + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userService.remove(Long.valueOf(req.getParameter("id")));
            log.info("User-Servlet-Delete-Removed");
            resp.sendRedirect("/jsp/admin/user-list.jsp");
        } catch (Exception e) {
            log.error("User-Servlet-Delete-" + e.getMessage());
        }
    }
}
