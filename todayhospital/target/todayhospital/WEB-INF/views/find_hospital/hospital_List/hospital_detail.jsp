<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>

<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");

String department = "";
String address = "";
String phone = "";
String description = "";

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb", "root", "yourpassword");

    String sql = "SELECT * FROM hospitals WHERE name = ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, name);
    rs = pstmt.executeQuery();

    if (rs.next()) {
        department = rs.getString("department");
        address = rs.getString("address");
        phone = rs.getString("phone");
        description = rs.getString("description");
    }
} catch (Exception e) {
    e.printStackTrace();
} finally {
    if (rs != null) rs.close();
    if (pstmt != null) pstmt.close();
    if (conn != null) conn.close();
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title><%= name %> 상세정보</title>
  <link rel="stylesheet" href="hospital_detail.css" />
</head>
<body>
  <div class="container">
    <h1><%= name %></h1>
    <p><strong>진료과:</strong> <%= department %></p>
    <p><strong>주소:</strong> <%= address %></p>
    <p><strong>전화번호:</strong> <%= phone %></p>
    <p><strong>설명:</strong></p>
    <p><%= description %></p>

	<!-- 만들어서 링크 연결해야 함 -->
    <a href="../reservation/reservation.jsp?hospital=<%= java.net.URLEncoder.encode(name, "UTF-8") %>" class="reserve-btn">이 병원 예약하기</a>
  </div>
</body>
</html>
