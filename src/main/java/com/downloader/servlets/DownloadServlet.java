package com.downloader.servlets;

import com.downloader.utils.YTDLPHelper;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String format = request.getParameter("format"); // e.g., mp3 or mp4



        try {
            File file = YTDLPHelper.downloadMedia(url, format);

            if (file != null && file.exists()) {
                response.setContentType("application/octet-stream");
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    out.flush();
                } finally {
                    file.delete(); // Clean up after download
                }
            } else {
                request.setAttribute("error", "Download failed or file not found.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Internal error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
