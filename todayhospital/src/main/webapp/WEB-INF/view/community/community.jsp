<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시판</title>
  <link rel="stylesheet" href="community.css" />
</head>
<body>
  <div class="qna-container">
    <h1>커뮤니티</h1>
    <div class="qna-list" id="qnaList"></div>
    <button class="write-btn" onclick="openWritePopup()">질문 등록하기</button>
  </div>

  <!-- 상세보기 팝업 -->
  <div class="popup" id="detailPopup">
    <div class="popup-content">
      <span class="close-button" onclick="closePopup('detailPopup')">&times;</span>
      <h2 id="detailTitle"></h2>
      <p id="detailContent"></p>
    </div>
  </div>

  <!-- 글쓰기 팝업 -->
  <div class="popup" id="writePopup">
    <div class="popup-content">
      <span class="close-button" onclick="closePopup('writePopup')">&times;</span>
      <h2>질문 등록</h2>
      <form id="writeForm">
        <input type="text" id="qnaTitle" placeholder="제목" required />
        <textarea id="qnaContent" placeholder="내용" required></textarea>
        <button type="submit">등록</button>
      </form>
    </div>
  </div>

  <script src="community.js"></script>
</body>
</html>
