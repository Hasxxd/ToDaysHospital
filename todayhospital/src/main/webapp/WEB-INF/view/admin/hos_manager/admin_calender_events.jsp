<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, yourpackage.ReservationDTO, yourpackage.ReservationDAO" %>
<%@ page import="org.json.simple.JSONArray" %>
<%@ page import="org.json.simple.JSONObject" %>


<%
    // 예: 병원 관리자 세션에서 병원 ID 가져오기
    String hospitalId = (String) session.getAttribute("hospitalId");
    if (hospitalId == null) {
        hospitalId = "defaultHospitalId"; // 임시 기본값, 실제로는 로그인 처리 필요
    }

    // 상태별 색상 매핑
    Map<String, String> statusColorMap = new HashMap<>();
    statusColorMap.put("대기", "#6c757d");      // 회색
    statusColorMap.put("승인", "#198754");      // 초록
    statusColorMap.put("거절", "#dc3545");      // 빨강
    statusColorMap.put("취소", "#ffc107");      // 노랑(옵션)

    // DAO 호출하여 예약 리스트 조회
    ReservationDAO dao = new ReservationDAO();
    List<ReservationDTO> list = dao.getReservationListByHospital(hospitalId);

    JSONArray events = new JSONArray();

    for (ReservationDTO r : list) {
        JSONObject obj = new JSONObject();
        obj.put("title", r.getPatientName());
        obj.put("start", r.getReservDate()); // 날짜만
        obj.put("color", statusColorMap.getOrDefault(r.getStatus(), "#0d6efd")); // 기본 파랑색

        // FullCalendar의 extendedProps로 상세 데이터 전달
        JSONObject extendedProps = new JSONObject();
        extendedProps.put("reservTime", r.getReservTime());
        extendedProps.put("status", r.getStatus());
        extendedProps.put("symptom", r.getSymptom());
        extendedProps.put("phone", r.getPhone());
        extendedProps.put("email", r.getEmail());

        obj.put("extendedProps", extendedProps);

        events.add(obj);
    }

    out.print(events.toJSONString());
%>
