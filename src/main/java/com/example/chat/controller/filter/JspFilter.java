package com.example.chat.controller.filter;


//import com.example.chat.model.service.UserService;
//import jakarta.inject.Inject;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import javax.naming.Context;
//import java.io.IOException;

//@WebFilter(urlPatterns = "/jsp/*")
public class JspFilter {//implements Filter {
//    @Inject
//    UserService userService;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        String role = null;
//        try {
//            String realUsername = ((HttpServletRequest) servletRequest).getUserPrincipal().getName();
//            role = userService.findByUsername(realUsername).getRole().getRole();
//            String username = String.valueOf(((HttpServletRequest) servletRequest).getSession().getAttribute("username"));
//                ((HttpServletRequest) servletRequest).getSession().setAttribute(
//                        "username", realUsername);
//                ((HttpServletRequest) servletRequest).getSession().setAttribute(
//                        "role", role);
////
//            System.out.println(role + " " + username+ " " + realUsername);
//            System.out.println( ((HttpServletRequest) servletRequest).getSession().getAttribute("realUsername"));
//            System.out.println( ((HttpServletRequest) servletRequest).getSession().getAttribute("role"));
////            System.out.println(servletRequest.getAttribute("username"));
////            System.out.println(servletRequest.getAttribute("role"));
////            System.out.println(servletRequest.getAttribute("realUsername"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        ((HttpServletRequest) servletRequest).getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(servletRequest,servletResponse);
//    }
}
