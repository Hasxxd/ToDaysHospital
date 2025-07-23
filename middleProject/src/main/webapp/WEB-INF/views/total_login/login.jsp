<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>로그인 - 오늘의 병원</title>

  <!-- Tailwind CDN -->
  <script src="https://cdn.tailwindcss.com"></script>

  <!-- Inter 폰트 -->
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet" />

  <!-- reCAPTCHA -->
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>

  <!-- CSS -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/login.css">
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen p-4">
  <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
    <h2 class="text-2xl font-semibold text-center text-gray-800 mb-6">로그인</h2>

    <!-- 로그인 폼 -->
    <form id="loginForm" onsubmit="return handleLogin(event)" class="flex flex-col space-y-4">
      <input
        type="text"
        id="username"
        placeholder="아이디"
        required
        class="p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <input
        type="password"
        id="password"
        placeholder="비밀번호"
        required
        class="p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <button
        type="submit"
        class="w-full bg-blue-600 text-white p-3 rounded-md font-medium hover:bg-blue-700 transition duration-300 ease-in-out shadow-md"
      >
        로그인
      </button>

      <!-- 소셜 로그인 -->
      <button type="button" onclick="openKakaoLoginPage()" id="kakaoLoginBtn"
        class="w-full bg-yellow-400 text-gray-800 p-3 rounded-md font-medium hover:bg-yellow-500 transition duration-300 ease-in-out shadow-md">
        카카오 로그인
      </button>
      <button type="button" onclick="openGoogleLoginPage()" id="googleLoginBtn"
        class="w-full bg-red-500 text-white p-3 rounded-md font-medium hover:bg-red-600 transition duration-300 ease-in-out shadow-md">
        구글 로그인
      </button>
      <button type="button" onclick="openNaverloginPage()" id="naverLoginBtn"
        class="w-full bg-green-500 text-white p-3 rounded-md font-medium hover:bg-green-600 transition duration-300 ease-in-out shadow-md">
        네이버 로그인
      </button>
    </form>

    <!-- 추가 링크 -->
    <div class="find-links text-center text-sm mt-6 space-x-4">
      <a href="<%= request.getContextPath() %>/signup/signup.jsp" class="text-blue-800 hover:underline">회원가입</a>
      <span class="text-gray-400">|</span>
      <a href="#" id="findId" class="text-blue-600 hover:underline">아이디 찾기</a>
      <span class="text-gray-400">|</span>
      <a href="#" id="findPassword" class="text-blue-600 hover:underline">비밀번호 찾기</a>
    </div>

    <!-- reCAPTCHA 영역 -->
    <div class="mt-6 flex justify-center">
      <div class="g-recaptcha" data-sitekey="6Lc2OYsrAAAAALPnug9lY6T-VHCMsnWBz6_9Z0R8"></div>
    </div>
  </div>

  <!-- JS -->
  <script src="<%= request.getContextPath() %>/resources/js/login.js"></script>
</body>
</html>
