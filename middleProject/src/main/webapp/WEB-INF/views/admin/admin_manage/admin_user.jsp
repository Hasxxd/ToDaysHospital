<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>회원 관리 - 오늘의 병원</title>
  <link rel="stylesheet" href="admin_user.css" />
</head>
<body>
  <header class="admin-header">
    <h1>회원 관리</h1>
    <nav>
      <ul class="admin-nav">
        <li><a href="../admin_home/admin_home.jsp">← 관리자 홈</a></li>
      </ul>
    </nav>
  </header>

  <main class="admin-main">
    <table class="admin-table">
      <thead>
        <tr>
          <th>아이디</th>
          <th>이름</th>
          <th>이메일</th>
          <th>가입일</th>
          <th>관리</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>mingkong123</td>
          <td>밍콩</td>
          <td>mingkong@hospital.com</td>
          <td>2025-07-01</td>
          <td>
            <button onclick="deleteUser(1)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </main>
  <script src="admin_user.js"></script>
  <script>
    function deleteUser(id) {
      if (confirm('정말 이 회원을 삭제하시겠습니까?')) {
        alert(id + '번 회원 삭제');
      }
    }
  </script>
</body>
</html>
