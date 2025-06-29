package com.downloader.utils;

import java.io.*;

public class YTDLPHelper {
    public static File downloadMedia(String url, String format) {
        String fileName = "download." + format;
        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "yt-dlp", "-f", "best", "-o", fileName, url
            );

            if (format.equals("mp3")) {
                pb.command("yt-dlp", "-x", "--audio-format", "mp3", "-o", fileName, url);
            }

            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();

            return new File(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
