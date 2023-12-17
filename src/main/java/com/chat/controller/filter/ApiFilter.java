package com.chat.controller.filter;

import com.chat.model.entity.Chat;
import com.chat.model.service.ChatService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns ="/api/chat/*")
public class ApiFilter implements Filter {
    @Inject
    ChatService chatService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String username = String.valueOf(httpServletRequest.getSession().getAttribute("username"));
        List<Chat> chatList = chatService.findByUsername(username);
        System.out.println("chatlist : " + chatList);

        boolean userInvolved = false;

        for (Chat chat : chatList) {
            if (username.equals(chat.getSender()) || username.equals(chat.getReceiver())) {
                userInvolved = true;
                break;
            }
        }
        if (!userInvolved && !chatList.isEmpty()) {
            userInvolved = true;
        }
        if (userInvolved) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.sendError(403, "Access forbidden");
        }
    }
}
