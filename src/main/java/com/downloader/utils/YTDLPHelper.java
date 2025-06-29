package com.downloader.utils;

import java.io.*;

public class YTDLPHelper {

    public static File downloadMedia(String url, String format) throws IOException, InterruptedException {
        String filename = "yt_" + System.currentTimeMillis() + "." + format;
        File outputFile = new File(System.getProperty("java.io.tmpdir"), filename);

        String[] command;

        if ("mp3".equalsIgnoreCase(format)) {
            command = new String[]{
                    "yt-dlp",
                    "-x",
                    "--audio-format", "mp3",
                    "-o", outputFile.getAbsolutePath(),
                    url
            };
        } else if ("mp4".equalsIgnoreCase(format)) {
            command = new String[]{
                    "yt-dlp",
                    "-f", "bv[ext=mp4][vcodec^=avc1]+ba[ext=m4a]/best[ext=mp4]",
                    "-o", outputFile.getAbsolutePath(),
                    url
            };
        } else {
            System.err.println("Unsupported format: " + format);
            return null;
        }

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[yt-dlp] " + line);
            }
        }

        int exitCode = process.waitFor();

        if (exitCode == 0 && outputFile.exists()) {
            return outputFile;
        } else {
            System.err.println("yt-dlp failed with code " + exitCode);
            return null;
        }
    }
}
