package com.chat.controller.servlet;

import com.chat.model.entity.Attachment;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRoles;
import com.chat.model.entity.enums.FileType;
import com.chat.model.service.AttachmentService;
import com.chat.model.service.RoleService;
import com.chat.model.service.UserRolesService;
import com.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(urlPatterns = "/User")
public class UserServlet extends HttpServlet {
    @Inject
    private UserRolesService userRolesService;

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
            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            System.out.println("File : " + fileName);
            for (Part part : req.getParts()) {
                part.write("c:\\root\\" + fileName);
            }

            Attachment attachment = Attachment.builder().title(username + " Image").filePath("c:\\root\\" + fileName).fileType(FileType.jpg).active(true).build();
            attachmentService.save(attachment);

            User user = User.builder()
                    .photo(attachment)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .firstname(firstname)
                    .lastname(lastname)
                    .role(roleService.findByRole("customer"))
                    .active(true)
//                    .privateAccount(privateAcc)
//                    .photo(attachment)
                    .build();
            userService.save(user);

            UserRoles userRoles = UserRoles.builder().roleName("customer").username(user.getUsername()).build();
            userRolesService.save(userRoles);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("User", user);
            resp.sendRedirect("/login.jsp");
            System.out.println("person saved " + user);
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
