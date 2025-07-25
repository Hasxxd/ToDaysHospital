<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>병원 예약</title>
  <link rel="stylesheet" href="reservation.css">
</head>
<body>
  <div class="reservation-container">
    <h1>🏥 병원 예약</h1>

    <section class="hospital-info">
      <h2 id="hospital-name">병원이름</h2>
      <p id="hospital-address">주소: </p>
      <p id="hospital-phone">전화번호: </p>
    </section>

    <section class="reservation-form">
      <label for="department">진료과 선택</label>
      <select id="department">
        <option value="">-- 선택하세요 --</option>
        <option value="내과">내과</option>
        <option value="외과">외과</option>
        <option value="소아과">소아과</option>
      </select>

      <label>진료 시간대</label>
      <div class="time-section">
        <label><input type="radio" name="time-period" value="오전"> 오전</label>
        <label><input type="radio" name="time-period" value="오후"> 오후</label>
      </div>

      <label for="time">상세 시간 선택</label>
      <select id="time" disabled>
        <option value="">먼저 오전/오후를 선택하세요</option>
      </select>

      <label for="date">진료 날짜</label>
      <input type="date" id="date">

      <label for="name">예약자 이름</label>
      <input type="text" id="name" placeholder="홍길동">

      <label for="phone">연락처</label>
      <input type="tel" id="phone" placeholder="010-1234-5678">

      <button id="reserveBtn">예약하기</button>
    </section>
  </div>

  <script src="reservation.js"></script>
</body>
</html>
