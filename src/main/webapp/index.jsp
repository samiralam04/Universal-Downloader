<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Universal Downloader</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="icon" href="assets/icons/favicon.ico" type="icon/x-icon">
</head>
<body>
<div class="container">
    <div class="header">
        <h1>Universal Downloader</h1>
        <p class="subtitle">Download videos and audio from various platforms</p>
    </div>

    <form id="downloadForm" action="download" method="post">
        <div class="input-group">
            <input type="text" name="url" placeholder="Enter video URL (YouTube, Vimeo, etc.)" required>
            <div class="select-wrapper">
                <select name="format">
                    <option value="mp4">Video (.mp4)</option>
                    <option value="mp3">Audio (.mp3)</option>
                </select>
            </div>
            <button type="submit" class="download-btn">
                <span class="btn-text">Download</span>

            </button>
        </div>
    </form>

    <div class="loading" id="loading" style="display: none;">
        <div class="spinner"></div>
        <p>Processing your request...</p>
    </div>

    <div class="instructions">
        <h2>How to use:</h2>
        <ol>
            <li>Paste the video URL in the input field</li>
            <li>Select your preferred format</li>
            <li>Click Download and wait for processing</li>
        </ol>
    </div>
</div>

<footer>
    <p>Samir alam © 2025 Universal Downloader. All rights reserved.</p>
</footer>

<script src="assets/js/script.js"></script>
</body>
</html>