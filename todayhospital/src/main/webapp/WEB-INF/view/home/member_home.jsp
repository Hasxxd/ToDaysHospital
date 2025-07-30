<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="net.daum.dto.MemberDTO" %>
<%
    MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
    String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>오늘의 병원 - 회원 홈</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- TailwindCSS -->
  <script src="https://cdn.tailwindcss.com"></script>

  <!-- Google Fonts: Inter -->
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet" />

  <!-- Custom CSS -->
  <link rel="stylesheet" href="<%=cp%>/resources/css/login.css" />
</head>

<body class="bg-gray-50 font-[Inter] min-h-screen flex flex-col">
  <!-- Header -->
  <header class="bg-white shadow-md p-4 flex justify-between items-center">
    <h1 class="text-2xl font-bold text-blue-700">오늘의 병원</h1>
    <div class="text-sm text-gray-600">
      <span class="font-medium"><%= loginUser.getMem_name() %></span> 님 환영합니다!
      <a href="<%=cp%>/logout.do" class="ml-4 text-blue-500 hover:underline">로그아웃</a>
    </div>
  </header>

  <!-- Main Content -->
  <main class="flex-grow p-6">
    <div class="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow">
      <h2 class="text-xl font-semibold text-gray-800 mb-4">회원 전용 서비스</h2>
      <ul class="space-y-2">
        <li class="border p-4 rounded hover:bg-gray-50 transition">
          <a href="<%=cp%>/mypage/reservation.do" class="block text-blue-600 font-medium">▶ 진료 예약 확인</a>
        </li>
        <li class="border p-4 rounded hover:bg-gray-50 transition">
          <a href="<%=cp%>/mypage/medical.do" class="block text-blue-600 font-medium">▶ 진료 내역 조회</a>
        </li>
        <li class="border p-4 rounded hover:bg-gray-50 transition">
          <a href="<%=cp%>/mypage/profile.do" class="block text-blue-600 font-medium">▶ 개인정보 수정</a>
        </li>
      </ul>
    </div>
  </main>

  <!-- Footer -->
  <footer class="bg-white text-center p-4 text-sm text-gray-400 border-t">
    ⓒ 오늘의 병원. All rights reserved.
  </footer>

  <!-- Custom JS -->
  <script src="<%=cp%>/resources/js/member_home.js"></script>
</body>
</html>
