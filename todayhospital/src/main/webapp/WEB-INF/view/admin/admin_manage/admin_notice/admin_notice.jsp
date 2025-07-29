<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지사항 관리 - 오늘의 병원</title>
  <link rel="stylesheet" href="admin_notice.css" />
</head>
<body>
  <header class="admin-header">
    <h1>공지사항 관리</h1>
    <nav>
      <ul class="admin-nav">
        <li><a href="../../admin_home/admin_home.jsp">← 관리자 홈</a></li>
      </ul>
    </nav>
  </header>

  <main class="admin-main">
    <button class="create-button" onclick="createNotice()">+ 공지 등록</button>
    <table class="admin-table">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>등록일</th>
          <th>관리</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>서버 점검 안내</td>
          <td>2025-07-21</td>
          <td>
            <button onclick="editNotice(1)">수정</button>
            <button onclick="deleteNotice(1)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </main>

  <!-- 공지 등록 팝업 -->
  <div id="notice-popup" class="popup-overlay hidden">
    <div class="popup-content">
      <h2>공지 등록</h2>
      <form id="notice-form">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required />

        <label for="content">내용</label>
        <textarea id="content"s name="content" rows="6" required></textarea>

        <div class="popup-buttons">
          <button type="submit">등록</button>
          <button type="button" onclick="closeNoticePopup()">닫기</button>
        </div>
      </form>
    </div>
  </div>

  <script src="admin_notice.js"></script>
</body>
</html>
