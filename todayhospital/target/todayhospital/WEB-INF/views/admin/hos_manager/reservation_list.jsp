<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, yourpackage.ReservationDAO, yourpackage.ReservationDTO" %>
<%@ page session="true" %>
<%
    String hospitalId = (String) session.getAttribute("hospitalId"); // 로그인된 병원 관리자
    ReservationDAO dao = new ReservationDAO();
    List<ReservationDTO> list = dao.getReservationListByHospital(hospitalId);
%>

<!-- 위 내용들 다 연결해야 함 -->

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>예약자 목록</title>
  <link rel="stylesheet" href="reservation_list.css">
</head>
<body>
  <div class="list-container">
    <h2>병원 예약자 목록</h2>
    <table>
      <thead>
        <tr>
          <th>예약자 이름</th>
          <th>전화번호</th>
          <th>이메일</th>
          <th>예약일</th>
          <th>시간</th>
          <th>증상</th>
        </tr>
      </thead>
      <tbody>
        <%
          for (ReservationDTO dto : list) {
        %>
        <tr>
          <td><%= dto.getPatientName() %></td>
          <td><%= dto.getPhone() %></td>
          <td><%= dto.getEmail() %></td>
          <td><%= dto.getReservDate() %></td>
          <td><%= dto.getReservTime() %></td>
          <td><%= dto.getSymptom().length() > 20 ? dto.getSymptom().substring(0, 20) + "..." : dto.getSymptom() %></td>
        </tr>
        <%
          }
        %>
      </tbody>
   </table>
  </div>      
  
         <!-- 증상 상세 팝업 -->
    <div id="symptomModal" class="modal hidden">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>증상 상세 보기</h3>
            <p id="symptomText"></p>
        </div>
    </div>
    
  
</body>
</html>
