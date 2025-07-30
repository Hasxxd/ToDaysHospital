<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="net.daum.dto.MemberDTO" %>
<%
  String cp = request.getContextPath();
  MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>오늘의 병원</title>
  <link rel="stylesheet" href="<%=cp%>/resources/css/home.css" />
  <script>
    <% if (loginUser != null) { %>
      // 로그인 상태이면 바로 회원 홈으로 리디렉션
      window.location.href = "<%=cp%>/home.do";
    <% } %>
  </script>
</head>
<body>
  <div class="wrapper">
    <button class="menu-button">🛠️ 관리자 로그인</button>
    <div class="auth-buttons">
      <button onclick="openLoginPopup()">로그인</button>
      <button id="signupButton">회원가입</button>
    </div>
    <header>
      <div class="logo">
        <img src="<%=cp%>/resources/images/todayhos.png" alt="오늘의 병원 로고" />
      </div>
      <div class="slogan">지금 예약하고 오늘 진료받자!</div>
    </header>
    <div class="search">
      <input type="text" id="symptomInput" placeholder="증상으로 병원 검색하기" />
      <button onclick="searchBySymptom()">검색</button>
      <span id="loadingIndicator" class="loading-text" style="display: none;">🔍 검색 중입니다</span>
    </div>
  </div>
</body>
</html>