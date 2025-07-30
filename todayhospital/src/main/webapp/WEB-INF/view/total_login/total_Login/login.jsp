<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
  String cp = request.getContextPath();
  String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>로그인 - 오늘의 병원</title>

  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet" />

  <!-- reCAPTCHA v2 -->
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>

  <!-- 소셜 로그인 SDK -->
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script src="https://accounts.google.com/gsi/client" async defer></script>

  <!-- Custom CSS -->
  <link rel="stylesheet" href="<%=cp%>/resources/css/login.css" />
</head>

<body class="bg-gray-100 flex items-center justify-center min-h-screen p-4 font-[Inter]">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">

    <!--  헤더 -->
    <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">로그인</h2>

    <!--  오류 메시지 -->
    <% if (msg != null) { %>
      <div class="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 text-sm rounded">
        <%= msg %>
      </div>
    <% } %>

    <!--  로그인 폼 -->
    <form method="post" action="<%=cp%>/login_check.do" class="flex flex-col space-y-4">
      <input type="text" name="patientLoginId" id="username" placeholder="아이디" required
        class="p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <input type="password" name="patientPw" id="password" placeholder="비밀번호" required
        class="p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500" />

      <!--  reCAPTCHA -->
      <div class="g-recaptcha" data-sitekey="6Lc2OYsrAAAAALPnug9lY6T-VHCMsnWBz6_9Z0R8"></div>

      <button type="submit"
        class="bg-blue-600 text-white p-3 rounded-md font-semibold hover:bg-blue-700 transition duration-300">
        로그인
      </button>
    </form>

    <!--  소셜 로그인 -->
    <div class="mt-6 space-y-3">
      <button onclick="kakaoLogin()" type="button"
        class="w-full flex items-center gap-4 bg-white border border-gray-300 text-gray-800 px-5 py-3 rounded-lg shadow-md hover:brightness-95 transition">
        <img src="<%=cp%>/resources/images/kakao_login.png" alt="카카오" class="w-6 h-6" />
        <span class="flex-1 text-center font-medium">카카오 로그인</span>
      </button>

      <button onclick="triggerGoogleLogin()" type="button"
        class="w-full flex items-center gap-4 bg-white border border-gray-300 text-gray-800 px-5 py-3 rounded-lg shadow-md hover:bg-gray-50 transition">
        <img src="<%=cp%>/resources/images/google_login.png" alt="구글" class="w-6 h-6" />
        <span class="flex-1 text-center font-medium">Google 로그인</span>
      </button>

      <button type="button" id="naverLoginBtn"
        class="w-full flex items-center gap-4 bg-white border border-gray-300 text-gray-800 px-5 py-3 rounded-lg shadow-md hover:bg-gray-50 transition">
        <img src="<%=cp%>/resources/images/naver_login.png" alt="네이버" class="w-6 h-6" />
        <span class="flex-1 text-center font-medium">네이버 로그인</span>
      </button>
    </div>

    <!--  부가 링크 -->
    <div class="find-links text-center text-sm mt-6 space-x-4">
      <a href="<%=cp%>/signup/signup.jsp" class="text-blue-800 hover:underline">회원가입</a>
      <span class="text-gray-400">|</span>
      <a href="#" class="text-blue-600 hover:underline">아이디 찾기</a>
      <span class="text-gray-400">|</span>
      <a href="#" class="text-blue-600 hover:underline">비밀번호 찾기</a>
    </div>
  </div>

  <!-- Custom JS -->
  <script src="<%=cp%>/resources/js/login.js"></script>
</body>
</html>
