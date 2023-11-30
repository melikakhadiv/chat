package com.chat.controller.servlet;

import com.chat.controller.session.SessionManager;
import com.chat.model.entity.Role;
import com.chat.model.entity.User;
import com.chat.model.entity.UserRole;
import com.chat.model.service.RoleService;
import com.chat.model.service.UserRoleService;
import com.chat.model.service.UserService;
import jakarta.enterprise.context.RequestScoped;
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
                        rolesAllowed = {"admin", "customer"}
                )
        }
)

@WebServlet(urlPatterns = "/user-panel", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private UserRoleService userRoleService;

    @Override
    @RequestScoped
    public void init() throws ServletException {
        System.out.println("Initializing ...");
        try {
            Role admin = Role.builder().role("admin").build();
            Role customer = Role.builder().role("customer").build();
            roleService.save(admin);
            roleService.save(customer);
//todo: create admin in init has an error
//            User user = User.builder().firstname("admin")
//                    .lastname("admin")
//                    .nickname("admin")
//                    .password("123")
//                    .username("admin")
//                    .privateAccount(true)
//                    .role(roleService.findByRole("admin"))
//                    .build();
//            userService.save(user);
//            UserRole userRole = UserRole.builder()
//                    .username(user.getUsername())
//                    .roleName("admin")
//                    .build();
//            userRoleService.save(userRole);
//            System.out.println(userRole);
        } catch (Exception e) {
            System.out.println("Init Error \n" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dispatch Post");
        String role = null;
        try {
            System.out.println("getName: " +request.getUserPrincipal().getName());
            String username = request.getUserPrincipal().getName();
            role = userService.findByUsername(username).getRole().getRole();
            request.getSession().setAttribute("username", username);
            request.getSession().setAttribute("userImage", userService.findByUsername(username).getPhoto().getFilePath());
            request.getSession().setAttribute("role", role);
            SessionManager.addHttpSession(request.getSession());

            System.out.println(role + " " + username);
            System.out.println("role: " + request.getSession().getAttribute("role"));
            request.getRequestDispatcher("/jsp/" + role + "/panel.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Redirect : " + "/login.jsp");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            System.out.println("Error : " + e.getMessage());
        }
    }
}
