package com.downloader.utils;

import java.io.*;
import java.util.zip.*;

public class ZipUtil {
    public static String downloadAndZipPlaylist(String playlistUrl) throws IOException {
        File tempDir = new File("/tmp/playlist");
        tempDir.mkdirs();
        ProcessBuilder pb = new ProcessBuilder("yt-dlp", "--yes-playlist", "-o", tempDir.getAbsolutePath() + "/%(title)s.%(ext)s", playlistUrl);
        pb.redirectErrorStream(true);
        Process process = pb.start();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while (br.readLine() != null) {}
        } catch (Exception e) {
            return null;
        }
        String zipPath = "/tmp/playlist.zip";
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath))) {
            for (File file : tempDir.listFiles()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                    zos.closeEntry();
                }
            }
        }
        return zipPath;
    }
}