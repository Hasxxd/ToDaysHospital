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
        <li><a href="../admin_home/admin_home.jsp">← 관리자 홈</a></li>
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
        <!-- 더미 데이터 -->
      </tbody>
    </table>
  </main>

  <script src="../admin_manage/admin_notice.js"></script>
	
  <script>
    function createNotice() {
      alert('공지 등록 페이지로 이동 예정');
    }

    function editNotice(id) {
      alert(id + '번 공지 수정 기능');
    }

    function deleteNotice(id) {
      if (confirm('정말 삭제하시겠습니까?')) {
        alert(id + '번 공지 삭제');
      }
    }
  </script>
</body>
</html>
