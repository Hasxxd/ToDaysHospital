<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>병원 관리 - 오늘의 병원</title>
  <link rel="stylesheet" href="admin_hospital.css" />
</head>
<body>
  <header class="admin-header">
    <h1>병원 관리</h1>
    <nav>
      <ul class="admin-nav">
        <li><a href="../admin_home/admin_home.jsp">← 관리자 홈</a></li>
      </ul>
    </nav>
  </header>

  <main class="admin-main">
    <button class="create-button" onclick="addHospital()">+ 병원 등록</button>
    <table class="admin-table">
      <thead>
        <tr>
          <th>병원명</th>
          <th>주소</th>
          <th>전화번호</th>
          <th>관리</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>오늘의 내과</td>
          <td>서울특별시 강남구 역삼동</td>
          <td>02-123-4567</td>
          <td>
            <button onclick="editHospital(1)">수정</button>
            <button onclick="deleteHospital(1)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </main>
  <script src="admin_hospital.js"></script>
  <script>
    function addHospital() {
      alert('병원 등록 기능 준비 중');
    }

    function editHospital(id) {
      alert(id + '번 병원 수정');
    }

    function deleteHospital(id) {
      if (confirm('정말 삭제하시겠습니까?')) {
        alert(id + '번 병원 삭제');
      }
    }
  </script>
</body>
</html>
