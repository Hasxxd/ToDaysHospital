<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%
    String memberId = (String) session.getAttribute("memberId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>병원 예약 폼</title>
  <link rel="stylesheet" href="reservation_form.css">
</head>
<body>
  <div class="form-container">
    <h2>병원 예약 신청</h2>
    <form action="submitReservation.jsp" method="post" id="reservationForm">
      <label for="RESERV_DATE">예약일자</label>
      <input type="date" id="RESERV_DATE" name="RESERV_DATE" required>

      <label for="RESERV_TIME">예약 시간</label>
      <select id="RESERV_TIME" name="RESERV_TIME" required>
        <option value="">-- 선택 --</option>
        <option value="morning">오전</option>
        <option value="afternoon">오후</option>
      </select>

      <label for="BM_SYMPTOM">증상 내용</label>
      <textarea id="BM_SYMPTOM" name="BM_SYMPTOM" rows="3" required></textarea>

      <label for="BM_TRAVEL">최근 해외 여행 여부</label>
      <select id="BM_TRAVEL" name="BM_TRAVEL" required>
        <option value="">-- 선택 --</option>
        <option value="yes">예</option>
        <option value="no">아니오</option>
      </select>

      <label for="BM_FAMILY">가족력</label>
      <input type="text" id="BM_FAMILY" name="BM_FAMILY">

      <label for="BM_PREGNANCY">임신 여부</label>
      <select id="BM_PREGNANCY" name="BM_PREGNANCY">
        <option value="">-- 선택 --</option>
        <option value="yes">예</option>
        <option value="no">아니오</option>
      </select>

      <label for="BM_VACCINE">최근 백신 접종 여부</label>
      <select id="BM_VACCINE" name="BM_VACCINE" required>
        <option value="">-- 선택 --</option>
        <option value="yes">예</option>
        <option value="no">아니오</option>
      </select>

      <label for="PATIENT_BLOOD">혈액형</label>
      <select id="PATIENT_BLOOD" name="PATIENT_BLOOD" required>
        <option value="">-- 선택 --</option>
        <option value="A">A형</option>
        <option value="B">B형</option>
        <option value="AB">AB형</option>
        <option value="O">O형</option>
      </select>

      <label for="PATIENT_DISEASE">기존 질환</label>
      <input type="text" id="PATIENT_DISEASE" name="PATIENT_DISEASE">

      <label for="PATIENT_ALLERGY">알레르기</label>
      <input type="text" id="PATIENT_ALLERGY" name="PATIENT_ALLERGY">

      <label for="PATIENT_MEDICATION">복용 중인 약</label>
      <input type="text" id="PATIENT_MEDICATION" name="PATIENT_MEDICATION">

      <label for="BM_PRECAUTIONS">기타 유의사항</label>
      <textarea id="BM_PRECAUTIONS" name="BM_PRECAUTIONS" rows="2"></textarea>

      <input type="hidden" name="memberId" value="<%= memberId %>">

      <button type="submit">예약하기</button>
    </form>
  </div>

  <script src="reservation_form.js"></script>

</body>
</html>
