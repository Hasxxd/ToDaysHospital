<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>로그인 성공</title>
  <style>
    body {
      font-family: 'SUIT', sans-serif;
      text-align: center;
      padding-top: 100px;
      background-color: #f7faff;
    }
    .message {
      font-size: 1.4em;
      font-weight: 500;
      color: #333;
    }
  </style>
  <script>
    window.onload = function () {
      if (window.opener && !window.opener.closed) {
        // 로그인된 사용자 메인으로 이동
        window.opener.location.href = "/todayhospital/home.do";
        window.close();
      } else {
        // 팝업 단독 실행일 경우 fallback
        location.href = "/todayhospital/home.do";
      }
    };
  </script>
</head>
<body>
  <div class="message">
    로그인 성공! 잠시 후 이동합니다...
  </div>
</body>
</html>
