package com.example.chat.controller.filter;

import com.example.chat.model.entity.User;
import com.example.chat.model.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/*")
public class JspFilter implements Filter {
    @Inject
    UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            String realUsername = ((HttpServletRequest) servletRequest).getUserPrincipal().getName();
            String username = String.valueOf(((HttpServletRequest) servletRequest).getSession().getAttribute("username"));
            String role = userService.findByUsername(realUsername).getRole().getRole();
            System.out.println(role + username + realUsername);
            if (username == null) {
                ((HttpServletRequest) servletRequest).getSession().setAttribute(
                        "username", realUsername);
                ((HttpServletRequest) servletRequest).getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(servletRequest, servletResponse);
            }

            ((HttpServletRequest) servletRequest).getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
