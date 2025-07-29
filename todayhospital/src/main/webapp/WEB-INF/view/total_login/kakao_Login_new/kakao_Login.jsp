<!-- <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8" />
  <title>카카오 로그인</title>
  <link rel="stylesheet" href="kakao_Login.css" />
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script>
    Kakao.init('카카오_JAVASCRIPT_KEY');
    function kakaoLogin() {
      Kakao.Auth.authorize({
        redirectUri: "http://localhost:8080/프로젝트명/kakaoCallback"
      });
    }
  </script>
</head>
<body>
  <div class="container">
    <h2>Kakao 소셜로그인</h2>
    <button class="login-btn" onclick="kakaoLogin()">
      <img src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" alt="카카오 로그인 버튼" />
    </button>
  </div>
</body>
</html> -->
