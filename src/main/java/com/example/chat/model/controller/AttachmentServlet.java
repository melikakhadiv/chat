package com.example.chat.model.controller;

import com.example.chat.model.entity.Attachment;
import com.example.chat.model.entity.enums.FileType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/Attachment")
public class AttachmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            String title = req.getParameter("title");
            String filePath = req.getParameter("filePath");
            String fileType = req.getParameter("fileType");
            Attachment attachment = Attachment
                    .builder()
                    .title(title)
                    .fileType(FileType.valueOf(fileType))
                    .filePath(filePath)
                    .build();
//            MultipartRequest multipartRequest = new MultipartRequest(req,filePath);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("Attachment", attachment);
            resp.sendRedirect("/panel.jsp");
            resp.getWriter().println("Attachment is successfully saved");

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}