<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>에러 발생</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-red-100 h-screen flex items-center justify-center">
  <div class="bg-white p-8 rounded-lg shadow-md text-center max-w-md">
    <h1 class="text-3xl font-bold text-red-600 mb-4">오류가 발생했습니다</h1>
    <p class="text-gray-700 mb-6">요청하신 페이지를 처리하는 중 문제가 발생했습니다.<br>관리자에게 문의하거나 잠시 후 다시 시도해주세요.</p>
    <a href="<%= request.getContextPath() %>/index.do" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition">홈으로 이동</a>
  </div>
</body>
</html>
