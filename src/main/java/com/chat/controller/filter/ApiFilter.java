//package com.chat.controller.filter;
//
//import com.chat.model.entity.Chat;
//import com.chat.model.service.ChatService;
//import jakarta.inject.Inject;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.List;
//
//@WebFilter(urlPatterns = "/api/chat/*")
//public class ApiFilter implements Filter {
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        String uri = httpServletRequest.getRequestURI();
//        System.out.println("uri " + uri);
//        String username = String.valueOf(httpServletRequest.getSession().getAttribute("username"));
//        String sender = httpServletRequest.getParameter("sender");
//        String receiver = httpServletRequest.getParameter("receiver");
//        System.out.println("filter username " + username);
//        System.out.println("filter sender " + sender);
//        System.out.println("filter receiver " + receiver);
//
//        if (uri.contains("/private/") && !username.equals(sender)) {
//            System.out.println("--chat post filter--");
//            httpServletResponse.sendError(403, "Access forbidden");
//            System.out.println("post filtered");
//            return;
//        } else if (uri.contains("/history/") && !username.equals(sender) || !username.equals(receiver)) {
//            System.out.println("--chat get filter--");
//            httpServletResponse.sendError(403, "Access forbidden");
//            System.out.println("get filtered");
//            return;
//        }else if (uri.contains("/group/") && !username.equals(sender)){
//            System.out.println("--chat group filter--");
//            httpServletResponse.sendError(403, "Access forbidden");
//            System.out.println("group filtered");
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
