<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>병원 예약 캘린더</title>
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css' rel='stylesheet' />
    <link rel="stylesheet" href="admin_calendar.css" />
</head>
<body>
    <div class="calendar-container">
        <h2>📅 병원 예약 현황</h2>
        <div id='calendar'></div>
    </div>

	<div id="reservationModal" class="modal hidden">
		<div class="modal-content">
			<span id="modalClose" class="close">&times;</span>
			<h3>예약 상세 정보</h3>
			<div id="modalBody">
				<!-- 예약 상세 내용이 이곳에 들어감 -->
			</div>
		</div>
	</div>


	<script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js'></script>
    <script src="admin_calendar.js"></script>
</body>
</html>
