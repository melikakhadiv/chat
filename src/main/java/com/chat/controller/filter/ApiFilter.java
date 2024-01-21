package com.chat.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/chat/*")
public class ApiFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String username = String.valueOf(httpServletRequest.getSession().getAttribute("username"));

        String uri = httpServletRequest.getRequestURI();
        String[] uriAddress = uri.split("/");
        String sender = uriAddress[5];
        String receiver = uriAddress[4];
        if (((HttpServletRequest) servletRequest).getMethod().equals("post") && username.equals(sender)) {
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("Filter-Api-Chat-Post");

        } else if (((HttpServletRequest) servletRequest).getMethod().equals("get") && username.equals(receiver) || username.equals(sender)) {
            filterChain.doFilter(servletRequest, servletResponse);
            log.info("Filter-Api-Chat-Get");
        }
        httpServletResponse.sendError(403, "Access forbidden");
        log.error("Filter-Api-Chat");
    }
}
