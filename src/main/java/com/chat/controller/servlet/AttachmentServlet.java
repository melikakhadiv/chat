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
            req.getSession().setAttribute("attachments", attachmentService.findAll());
            resp.sendRedirect("/panel.jsp");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {


                /* Receive file uploaded to the Servlet from the HTML5 form */
                Part filePart = req.getPart("file");
                String fileName = filePart.getSubmittedFileName() + req.getSession().getAttribute("username");
                System.out.println("File : "+ fileName);
                for (Part part : req.getParts()) {
                    part.write("c:\\root\\"+fileName);
                }
                resp.getWriter().print("The file uploaded sucessfully.");

//            String title = req.getParameter("title");
//            String filePath = req.getParameter("filePath");
//            FileType fileType = FileType.valueOf(req.getParameter("fileType"));
//            Attachment attachment = Attachment
//                    .builder()
//                    .title(title)
//                    .fileType(fileType)
//                    .filePath(filePath)
//                    .build();
//            MultipartRequest multipartRequest = new MultipartRequest(req,filePath);
//            attachmentService.save(attachment);
//            HttpSession httpSession = req.getSession();
//            httpSession.setAttribute("Attachment", attachment);
//            resp.sendRedirect("/panel.jsp");
//            resp.getWriter().println("Attachment is successfully saved");

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            Long id= Long.valueOf(req.getParameter("id"));
            String title = req.getParameter("title");
            String filePath = req.getParameter("filePath");
            FileType fileType = FileType.valueOf(req.getParameter("fileType"));
            Attachment attachment = Attachment
                    .builder()
                    .id(id)
                    .title(title)
                    .fileType(fileType)
                    .filePath(filePath)
                    .build();
//            MultipartRequest multipartRequest = new MultipartRequest(req,filePath);
            attachmentService.edit(attachment);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("Attachment", attachment);
            resp.sendRedirect("/panel.jsp");
            resp.getWriter().println("Attachment is successfully edited");

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            attachmentService.remove(Long.valueOf(req.getParameter("id")));
            resp.sendRedirect("/panel.jsp");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
