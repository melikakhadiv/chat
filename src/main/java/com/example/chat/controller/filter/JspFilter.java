package com.example.chat.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/*")
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username =  ((HttpServletRequest) servletRequest).getSession().getAttribute("username").toString();
        if(username==null){
            ((HttpServletRequest) servletRequest).getSession().setAttribute(
                    "username",
                    ((HttpServletRequest) servletRequest).getUserPrincipal().getName()
            );
        }
        String role;
//        dispatch(jsp/user/chat.jsp)
    }
}
