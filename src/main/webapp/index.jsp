<!DOCTYPE html>
<html>
<head>
    <title>Universal Downloader</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Universal Downloader</h1>
        <form action="download" method="post">
            <input type="text" name="url" placeholder="Enter video URL" required>
            <select name="format">
                <option value="mp4">Video (.mp4)</option>
                <option value="mp3">Audio (.mp3)</option>
            </select>
            <button type="submit">Download</button>
        </form>
    </div>
</body>
</html>
