package com.chat.controller.servlet;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Role;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRole;
import com.chat.model.service.RoleService;
import com.chat.model.service.UserRoleService;
import com.chat.model.service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@ServletSecurity(value =
@HttpConstraint(
        rolesAllowed = {"admin", "customer"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {"admin", "customer"}
                )
        }
)

@WebServlet(urlPatterns = "/user-panel", loadOnStartup = 1)
@Slf4j
public class DispatcherServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private UserRoleService userRoleService;

    @Override
    @RequestScoped
    public void init() throws ServletException {
        log.info("Dispatcher-Servlet-Init");
        try {
            Role admin = Role.builder().role("admin").build();
            Role customer = Role.builder().role("customer").build();
            roleService.save(admin);
            roleService.save(customer);
            User user = User.builder().firstname("admin")
                    .lastname("admin")
                    .nickname("admin")
                    .password("123")
                    .username("admin")
                    .privateAccount(true)
                    .role(roleService.findByRole("admin"))
                    .build();
            userService.save(user);
            UserRole userRole = UserRole.builder()
                    .username("admin")
                    .roleName("admin")
                    .build();
            userRoleService.save(userRole);
        } catch (Exception e) {
            log.error("Dispatcher-Servlet-Init-" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = null;
        try {
            String username = request.getUserPrincipal().getName();
            role = userService.findByUsername(username).getRole().getRole();
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("username", username);
            User user = userService.findByUsername(username);
            if (user != null && user.getPhoto() != null) {
                String userImagePath = user.getPhoto().getFilePath();
                if (userImagePath != null) {
                    request.getSession().setAttribute("userImage", userImagePath);
                } else {
                    request.getSession().removeAttribute("userImage");
                }
            } else {
                request.getSession().removeAttribute("userImage");
            }
            SessionManager.addHttpSession(request.getSession());
            log.info("Dispatcher-Servlet-Get");
            request.getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(request, response);
        } catch (Exception e) {
            log.error("Dispatcher-Servlet-Get-"+ e.getMessage());
        }
    }
}
