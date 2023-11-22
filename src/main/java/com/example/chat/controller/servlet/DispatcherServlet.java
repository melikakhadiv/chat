package com.example.chat.controller.servlet;

import com.example.chat.controller.session.SessionManager;
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

@WebServlet(urlPatterns = "/user-panel")
public class DispatcherServlet extends HttpServlet {
    @Inject
    private UserService userService;

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
