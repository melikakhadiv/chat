package com.example.chat.model.controller;

import com.example.chat.model.entity.User;
import com.example.chat.model.service.UserService;
import jakarta.security.enterprise.credential.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.openqa.selenium.UsernameAndPassword;

import java.io.IOException;


@ServletSecurity(value = @HttpConstraint(
        rolesAllowed = {"admin","costumer"}),
        httpMethodConstraints = {
        @HttpMethodConstraint(
                value = "GET",
                rolesAllowed = {"admin","costumer"}
        )}
)
@WebServlet(urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username= req.getParameter("username");
            String password=req.getParameter("password");
            User user = userService.login(username,password);
            req.getSession().setAttribute("username",user.getUsername());
            resp.sendRedirect("/"+user.getRole()+"/panel.jsp");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getParameter("logout");
            req.getSession().invalidate();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

