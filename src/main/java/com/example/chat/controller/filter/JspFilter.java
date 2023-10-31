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
            String role = userService.findByUsername(realUsername).getRole().getRole();
//         anjam bede rahi ke rafte boodi chi sho? start
            ((HttpServletRequest) servletRequest).getSession().setAttribute(
                    "username", realUsername);
            ((HttpServletRequest) servletRequest).getSession().setAttribute(
                    "role", role);
            ((HttpServletRequest) servletRequest).getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(servletRequest, servletResponse);
            System.out.println(role + " " + ((HttpServletRequest) servletRequest).getSession().getAttribute("username"));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
