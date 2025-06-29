package com.downloader.utils;

public class FFMPEGHelper {
    public static void mergeSubtitle(String videoPath, String subtitlePath, String outputPath) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("ffmpeg", "-i", videoPath, "-vf", "subtitles=" + subtitlePath, outputPath);
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();
    }
}