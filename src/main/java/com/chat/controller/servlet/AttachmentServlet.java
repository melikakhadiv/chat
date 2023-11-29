package com.chat.controller.servlet;

import com.chat.model.entity.Attachment;
import com.chat.model.entity.enums.FileType;
import com.chat.model.service.AttachmentService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = "/Attachment")
public class AttachmentServlet extends HttpServlet {
    @Inject
    private AttachmentService attachmentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("attachmentList", attachmentService.findAll());
            resp.sendRedirect("/jsp/admin/attachment-list.jsp");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part filePart = req.getPart("file");
            String fileName = "jsp/customer/attachment/" + filePart.getSubmittedFileName();
            for (Part part : req.getParts()) {
                part.write(getServletContext().getRealPath("/") + fileName);
            }
            Attachment attachment = Attachment.builder().title("User attachment").filePath(fileName).active(true).build();
            attachmentService.save(attachment);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("Attachment", attachment);
            resp.sendRedirect("/jsp/admin/attachment-list.jsp");
            resp.getWriter().println("Attachment is successfully saved");

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            Part filePart = req.getPart("file");
            String fileName = "jsp/customer/attachment/" + filePart.getSubmittedFileName();
            for (Part part : req.getParts()) {
                part.write(getServletContext().getRealPath("/") + fileName);
            }
            Attachment attachment = Attachment.builder().title("User attachment").filePath(fileName).active(true).build();
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("Attachment", attachment);
            resp.sendRedirect("/jsp/admin/attachment-list.jsp");
            attachmentService.edit(attachment);
            resp.getWriter().println("Attachment is successfully edited");

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            attachmentService.remove(Long.valueOf(req.getParameter("id")));
            resp.sendRedirect("/jsp/admin/attachment-list.jsp");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
