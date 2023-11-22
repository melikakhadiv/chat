package com.example.chat.controller.servlet;

import com.example.chat.controller.session.SessionManager;
import com.example.chat.model.entity.Role;
import com.example.chat.model.service.RoleService;
import com.example.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@ServletSecurity(value =
@HttpConstraint(
        rolesAllowed = {"admin", "customer"}),
        httpMethodConstraints = {
                @HttpMethodConstraint(
                        value = "GET",
                        rolesAllowed = {"admin"}
                )
        }
)

@WebServlet(urlPatterns = "/user-panel", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        System.out.println("Initializing ...");
        try {
            Role admin = Role.builder().role("admin").build();
            Role customer = Role.builder().role("customer").build();
            roleService.save(admin);
            roleService.save(customer);
        } catch (Exception e) {
            System.out.println("Init Error \n" + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = null;
        try {
            String realUsername = request.getUserPrincipal().getName();
            role = userService.findByUsername(realUsername).getRole().getRole();
            String username = String.valueOf(request.getSession().getAttribute("username"));
            request.getSession().setAttribute(
                    "username", realUsername);
            request.getSession().setAttribute(
                    "role", role);
            SessionManager.addHttpSession(request.getSession());
//
            System.out.println(role + " " + username + " " + realUsername);
            System.out.println(request.getSession().getAttribute("realUsername"));
            System.out.println(request.getSession().getAttribute("role"));
//            System.out.println(servletRequest.getAttribute("username"));
//            System.out.println(servletRequest.getAttribute("role"));
//            System.out.println(servletRequest.getAttribute("realUsername"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(request, response);
    }
}
