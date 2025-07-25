<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>회원가입</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="signup.css" />
</head>
<body>
  <div class="container">
    <div class="logo">
      <img src="../../images/todayhos.png" alt="오늘의 병원 로고" />
    </div>
    <form id="signup-form" novalidate>
      <div class="input-group with-icon">
        <input type="text" id="userid" name="userid" placeholder="아이디" required />
      </div>
      <div class="input-group with-icon">
        <input type="password" id="password" name="password" placeholder="비밀번호" required minlength="6" />
        <button type="button" class="toggle-pw" aria-label="비밀번호 보기/숨기기">👁️</button>
      </div>
      <div class="input-group with-icon">
        <input type="email" id="email" name="email" placeholder="이메일 주소" />
        <span class="input-icon">✉️</span>
      </div>
      <div class="input-group with-icon">
        <input type="text" name="mem_zip" id="zipcode" placeholder="우편번호" readonly />
        <input type="button" id="zipbtn" value="우편번호 찾기" />
      </div>
      <div class="input-group with-icon">
        <input type="text" name="mem_add1" id="addr1" placeholder="주소" readonly />
      </div>
      <div class="input-group with-icon">
        <input type="text" name="mem_add2" id="addr2" placeholder="상세주소" />
        <input type="text" id="extraAddress" readonly placeholder="참고항목" />
      </div>
      <div class="input-group with-icon">
        <input type="text" id="name" name="name" placeholder="이름" required />
        <span class="input-icon">👤</span>
      </div>
      <div class="input-group with-icon">
        <input type="text" id="birth" name="birth" placeholder="생년월일 8자리" maxlength="8" pattern="\d{8}" required />
        <span class="input-icon">📅</span>
      </div>
      <div class="btn-group">
        <button type="button" class="toggle-btn" data-name="gender" data-value="남자">남자</button>
        <button type="button" class="toggle-btn" data-name="gender" data-value="여자">여자</button>
        <button type="button" class="toggle-btn" data-name="nationality" data-value="내국인">내국인</button>
        <button type="button" class="toggle-btn" data-name="nationality" data-value="외국인">외국인</button>
      </div>
      <input type="hidden" name="gender" id="gender" />
      <input type="hidden" name="nationality" id="nationality" />
      <div class="input-group with-icon">
        <select id="carrier" name="carrier" required>
          <option value="" disabled selected>통신사 선택</option>
          <option value="SKT">SKT</option>
          <option value="KT">KT</option>
          <option value="LGU+">LG U+</option>
          <option value="알뜰폰">알뜰폰</option>
        </select>
        <span class="input-icon">📡</span>
      </div>
      <div class="input-group with-icon">
        <input type="tel" id="phone" name="phone" placeholder="휴대전화번호" required pattern="010\d{8}" />
        <span class="input-icon">📱</span>
      </div>
      <div class="checkbox-group">
        <label>
          <input type="checkbox" id="agree" required />
          <span>[필수] 개인정보 활용 동의</span>
        </label>
      </div>
      <button type="submit" class="submit-btn">회원가입</button>
    </form>
    <div id="message" role="alert" aria-live="polite"></div>
  </div>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="signup.js"></script>
</body>
</html>
