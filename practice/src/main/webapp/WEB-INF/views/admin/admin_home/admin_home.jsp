<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>관리자 페이지 - 오늘의 병원</title>
  <link rel="stylesheet" href="admin_home.css" />
</head>
<body>
  <header class="admin-header">
    <h1>오늘의 병원 관리자 페이지</h1>
    <nav>
      <ul class="admin-nav">
        <li><a href="#">대시보드</a></li>
        <li><a href="#">로그아웃</a></li>
      </ul>
    </nav>
  </header>

  <main class="admin-main">
    <section class="card-container">
      <div class="admin-card" onclick="movePage('notice')">
        <h2>공지사항 관리</h2>
        <p>공지사항을 등록하고 수정합니다.</p>
      </div>
      <div class="admin-card" onclick="movePage('hospital')">
        <h2>병원 관리</h2>
        <p>병원 목록을 확인하고 등록합니다.</p>
      </div>
      <div class="admin-card" onclick="movePage('user')">
        <h2>회원 관리</h2>
        <p>가입한 회원을 조회하고 관리합니다.</p>
      </div>
    </section>
  </main>

  <script src="admin_home.js"></script>
</body>
</html>
