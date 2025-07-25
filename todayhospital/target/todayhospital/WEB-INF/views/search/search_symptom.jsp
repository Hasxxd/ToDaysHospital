<%@ page contentType="application/json; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 나중에 연결해야 함 -->
<%@ page import="java.util.*, java.sql.*, org.json.*" %>

<%
String symptom = request.getParameter("symptom");
String department = "일반의"; // 기본값

// 증상 키워드 → 진료과 매핑
Map<String, String> symptomMap = new HashMap<>();
symptomMap.put("어깨", "정형외과");
symptomMap.put("무릎", "정형외과");
symptomMap.put("귀", "이비인후과");
symptomMap.put("배", "내과");
symptomMap.put("속", "내과");
symptomMap.put("감기", "내과");

for (String keyword : symptomMap.keySet()) {
    if (symptom.contains(keyword)) {
        department = symptomMap.get(keyword);
        break;
    }
}

// DB 연결
Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
JSONArray result = new JSONArray();

try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospitaldb", "root", "yourpassword");

    String sql = "SELECT name, department, address FROM hospitals WHERE department LIKE ?";
    pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, "%" + department + "%");
    rs = pstmt.executeQuery();

    while (rs.next()) {
        JSONObject obj = new JSONObject();
        obj.put("name", rs.getString("name"));
        obj.put("department", rs.getString("department"));
        obj.put("address", rs.getString("address"));
        result.put(obj);
    }

    out.print(result.toString());

} catch (Exception e) {
    e.printStackTrace();
    response.setStatus(500);
    out.print("[]"); // 오류 시 빈 배열 반환
} finally {
    if (rs != null) rs.close();
    if (pstmt != null) pstmt.close();
    if (conn != null) conn.close();
}
%>
