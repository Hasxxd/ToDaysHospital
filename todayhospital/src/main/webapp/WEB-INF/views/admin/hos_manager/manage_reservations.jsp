<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, yourpackage.ReservationDTO" %>
<!-- 위 부분에 실제 데이터 연결해야 함 지금은 오류 떠서 상세 페이지 확인 불가 나중에 연결되면 수정 -->
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>예약 관리</title>
  <link rel="stylesheet" href="manage_reservations.css">
</head>
<body>
  <div class="container">
    <h2>병원 예약 관리</h2>

    <!-- 🔍 검색 필터 -->
    <div class="filters">
      <input type="text" id="searchName" placeholder="예약자 이름">
      <select id="searchStatus">
        <option value="">전체 상태</option>
        <option value="대기">대기</option>
        <option value="승인">승인</option>
        <option value="거절">거절</option>
      </select>
      <input type="date" id="searchDate">
      <button onclick="filterReservations()">검색</button>
    </div>

    <!-- 📋 예약 리스트 -->
    <table class="reservation-table">
      <thead>
        <tr>
          <th>이름</th>
          <th>예약일</th>
          <th>시간</th>
          <th>상태</th>
          <th>관리</th>
        </tr>
      </thead>
      <tbody id="reservationList">
        <%-- 여기에 JSTL 또는 scriptlet으로 예약 리스트 반복 출력 예정 --%>
      </tbody>
    </table>
  </div>

	<!-- 📌 상세보기 모달 -->
	<div id="detailModal" class="modal hidden">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h3>예약 상세 정보</h3>
			<div id="reservationDetail">
				<!-- 여기에 JS로 상세 항목 렌더링 -->
			</div>
			<div class="modal-buttons">
				<button class="approve" onclick="updateStatus('승인')">승인</button>
				<button class="reject" onclick="updateStatus('거절')">거절</button>
			</div>
		</div>
	</div>

	<script src="manage_reservations.js"></script>
</body>
</html>
