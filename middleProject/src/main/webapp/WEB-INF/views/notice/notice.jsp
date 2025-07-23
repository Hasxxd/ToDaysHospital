<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>공지사항</title>
  <link rel="stylesheet" href="notice.css">
</head>
<body>
  <div class="notice-container">
    <h1>📢 공지사항</h1>
    <ul class="notice-list" id="noticeList"></ul>
    <div class="pagination" id="pagination"></div>
    <a href="write.html" class="write-link">+ 공지 작성</a>
  </div>

  <!-- 상세 팝업 -->
  <div id="popup" class="popup hidden">
    <div class="popup-content">
      <span class="close-button">&times;</span>
      <h2 id="popup-title"></h2>
      <p id="popup-date"></p>
      <div id="popup-content"></div>
    </div>
  </div>

  <script src="notice.js"></script>
</body>
</html>
