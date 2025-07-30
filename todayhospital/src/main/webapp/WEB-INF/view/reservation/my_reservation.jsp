<%@ page session="true" %>
<%
    String memberId = (String) session.getAttribute("memberId");
%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>내 예약 정보</title>
  <link rel="stylesheet" href="my_reservations.css" />
</head>
<body>
  <h2>예약 내역</h2>
  <table>
    <thead>
      <tr><th>예약일자</th><th>예약 시간</th><th>증상 내용</th><th>최근 해외 여행 여부</th>
          <th>가족력</th><th>임신 여부</th><th>최근 백신 접종 여부</th><th>혈액형</th>
          <th>기존 질환</th><th>알레르기</th><th>복용 중인 약</th><th>기타 유의 사항</th>
      </tr>
    </thead>
    <tbody>
<%
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
        Context init = new InitialContext();
        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/yourDB");
        conn = ds.getConnection();

        String sql = "SELECT RESERV_DATE, RESERV_TIME, RESERV_SYMPTOM, BM_TRAVEL, BM_FAMILY, BM_PREGNANCY, BM_VACCINE, PATIENT_BLOOD, PATIENT_DISEASE, PATIENT_ALLERGY, PATIENT_MEDICATION, BM_PRECAUTIONS FROM reservations WHERE member_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, memberId);
        rs = pstmt.executeQuery();
        while(rs.next()) {
%>
      <tr>
        <td><%= rs.getString("RESERV_DATE") %></td>
        <td><%= rs.getString("RESERV_TIME") %></td>
        <td><%= rs.getString("RESERV_SYMPTOM") %></td>
        <td><%= rs.getString("BM_TRAVEL") %></td>
        <td><%= rs.getString("BM_FAMILY") %></td>
        <td><%= rs.getString("BM_PREGNANCY") %></td>
        <td><%= rs.getString("BM_VACCINE") %></td>
        <td><%= rs.getString("PATIENT_BLOOD") %></td>
        <td><%= rs.getString("PATIENT_DISEASE") %></td>
        <td><%= rs.getString("PATIENT_ALLERGY") %></td>
        <td><%= rs.getString("PATIENT_MEDICATION") %></td>
        <td><%= rs.getString("BM_PRECAUTIONS") %></td>
      </tr>
<%
        }
    } catch(Exception e) {
        e.printStackTrace();
    } finally {
        if(rs != null) rs.close();
        if(pstmt != null) pstmt.close();
        if(conn != null) conn.close();
    }
%>
    </tbody>
  </table>
</body>
</html>
