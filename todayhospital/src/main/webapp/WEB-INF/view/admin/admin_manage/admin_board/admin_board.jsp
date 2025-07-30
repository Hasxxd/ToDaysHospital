<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시판 관리</title>
  <link rel="stylesheet" href="admin_board.css" />
  <script defer src="admin_board.js"></script>
</head>
<body>
  <div class="board-container">
    <h2>게시판 관리</h2>

    <div class="tab-menu">
      <button class="tab active" data-tab="notice">공지사항</button>
      <button class="tab" data-tab="qna">Q&A</button>
      <button class="tab" data-tab="report">신고/비속어</button>
      <button class="tab" data-tab="stats">게시글 통계</button>
    </div>

    <div class="tab-content" id="notice">
      <%-- 공지사항 목록 테이블 삽입 예정 --%>
      <div class="board-section">
        <h3>공지사항 관리</h3>
        <table>
          <thead>
            <tr><th>ID</th><th>제목</th><th>작성일</th><th>관리</th></tr>
          </thead>
          <tbody id="noticeTable">
            <!-- 서버에서 공지사항 목록 출력 -->
          </tbody>
        </table>
      </div>
    </div>

    <div class="tab-content hidden" id="qna">
      <h3>Q&A 게시판 관리</h3>
      <table>
        <thead>
          <tr><th>ID</th><th>질문</th><th>작성자</th><th>관리</th></tr>
        </thead>
        <tbody id="qnaTable"></tbody>
      </table>
    </div>

    <div class="tab-content hidden" id="report">
      <h3>신고/비속어 게시물</h3>
      <table>
        <thead>
          <tr><th>ID</th><th>내용</th><th>신고사유</th><th>삭제</th></tr>
        </thead>
        <tbody id="reportTable"></tbody>
      </table>
    </div>

    <div class="tab-content hidden" id="stats">
      <h3>게시글 통계</h3>
      <div id="statsArea">
        <!-- 통계 차트 또는 요약 정보 표시 예정 -->
      </div>
    </div>
  </div>
</body>
</html>
