<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>오늘의 병원</title>
  <link rel="stylesheet" href="<%=cp%>/resources/css/home.css" />
  <link rel="icon" href="data:," />
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

    <!-- 증상으로 병원 검색 -->
    <div class="search">
      <input type="text" id="symptomInput" placeholder="증상으로 병원 검색하기" />
      <button onclick="searchBySymptom()">검색</button>
      <span id="loadingIndicator" class="loading-text" style="display: none;">🔍 검색 중입니다...</span>
    </div>

    <!-- 검색 결과 출력 영역 -->
    <div id="resultArea" class="search-result-area"></div>

    <div class="main-content">
      <div class="card">
        <h3>홈페이지<br>이용 가이드</h3>
        <button onclick="openGuidePopup()">확인하기</button>
      </div>

      <div class="card calendar-card">
        <h3>예약현황조회</h3>
        <p>진료예약현황을 조회</p>
        <img src="https://cdn-icons-png.flaticon.com/512/747/747310.png" alt="달력" />
      </div>

      <div class="card search-doctor">
        <h3>내 주변<br>병원 찾기</h3>
        <button onclick="window.location.href='<%=cp%>/find_hospital/map/map.jsp'">찾기</button>
      </div>

      <div class="round-card">
        <a href="<%=cp%>/archive/archive.jsp">자료실</a>
        <a href="<%=cp%>/notice/notice.jsp">공지사항</a>
        <a href="<%=cp%>/community/community.jsp">커뮤니티</a>
      </div>
    </div>
  </div>

  <div id="signupModal" class="modal" style="display:none;">
    <div class="modal-content">
      <span class="close-button">&times;</span>
      <iframe id="signupIframe" src="<%=cp%>/signup/signup.jsp" frameborder="0" style="width:100%; height: 1000px;"></iframe>
    </div>
  </div>

  <div id="successModal" class="modal" style="display:none;">
    <div class="modal-content" style="text-align: center; padding: 40px; max-width: 400px;">
      <h2>✅ 회원가입 완료!</h2>
      <p style="font-size: 1.1em; color: #555;">잠시 후 메인 화면으로 이동합니다.</p>
      <img src="<%=cp%>/resources/images/check-mark.png" alt="Success Icon" style="width: 100px; height: 100px; margin-top: 20px;">
    </div>
  </div>

  <div id="loginStatus" style="text-align: right; margin-top: 10px; padding-right: 20px;"></div>

  <script src="<%=cp%>/resources/js/index_home.js"></script>
</body>
</html>
