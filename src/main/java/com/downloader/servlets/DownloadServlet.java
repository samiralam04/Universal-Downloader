package com.downloader.servlets;

import com.downloader.utils.YTDLPHelper;

import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.*;


public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        String format = request.getParameter("format"); // mp3, mp4, etc.

        File file = YTDLPHelper.downloadMedia(url, format);

        if (file != null && file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            try (FileInputStream in = new FileInputStream(file); OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
        } else {
            request.setAttribute("error", "Download failed.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
