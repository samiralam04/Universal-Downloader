package com.downloader.servlets;

import com.downloader.utils.ZipUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/playlist")
public class PlaylistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String playlistUrl = request.getParameter("url");
        String zipPath = ZipUtil.downloadAndZipPlaylist(playlistUrl);

        if (zipPath == null) {
            response.getWriter().write("Failed to download playlist");
            return;
        }

        File zipFile = new File(zipPath);
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"playlist.zip\"");

        try (FileInputStream in = new FileInputStream(zipFile); OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }
}