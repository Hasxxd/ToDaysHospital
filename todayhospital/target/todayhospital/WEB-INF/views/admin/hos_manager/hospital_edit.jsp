<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="yourpackage.HospitalDAO, yourpackage.HospitalDTO" %>
<%@ page session="true" %>
<%
    String hospitalId = (String) session.getAttribute("hospitalId");
    HospitalDAO dao = new HospitalDAO();
    HospitalDTO hospital = dao.getHospitalById(hospitalId);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>병원 정보 수정</title>
    <link rel="stylesheet" href="hospital_edit.css" />
</head>
<body>
    <div class="edit-container">
        <h2>병원 정보 수정</h2>
        <form action="hospital_update.jsp" method="post" id="hospitalEditForm">
            <input type="hidden" name="hospitalId" value="<%= hospital.getHospitalId() %>" />

            <label for="hospitalName">병원이름</label>
            <input type="text" id="hospitalName" name="hospitalName" value="<%= hospital.getHospitalName() %>" required />

            <label for="areaCode">지역코드</label>
            <input type="text" id="areaCode" name="areaCode" value="<%= hospital.getAreaCode() %>" maxlength="10" required />

            <label for="address">병원주소</label>
            <input type="text" id="address" name="address" value="<%= hospital.getAddress() %>" maxlength="200" required />

            <label for="phone">병원전화번호</label>
            <input type="text" id="phone" name="phone" value="<%= hospital.getPhone() %>" maxlength="20" required />

            <label for="hospitalType">병원유형</label>
            <input type="text" id="hospitalType" name="hospitalType" value="<%= hospital.getHospitalType() %>" maxlength="50" />

            <label for="department">진료과목</label>
            <input type="text" id="department" name="department" value="<%= hospital.getDepartment() %>" maxlength="100" />

            <label for="description">병원소개글</label>
            <textarea id="description" name="description" rows="5" required><%= hospital.getDescription() %></textarea>

            <label for="authorizationDate">인허가일자</label>
            <input type="date" id="authorizationDate" name="authorizationDate" value="<%= hospital.getAuthorizationDate() %>" />

            <label for="nightServiceYN">야간진료가능여부</label>
            <select id="nightServiceYN" name="nightServiceYN">
                <option value="Y" <%= "Y".equals(hospital.getNightServiceYN()) ? "selected" : "" %>>예</option>
                <option value="N" <%= "N".equals(hospital.getNightServiceYN()) ? "selected" : "" %>>아니오</option>
            </select>

            <label for="wheelchairYN">휠체어출입가능여부</label>
            <select id="wheelchairYN" name="wheelchairYN">
                <option value="Y" <%= "Y".equals(hospital.getWheelchairYN()) ? "selected" : "" %>>예</option>
                <option value="N" <%= "N".equals(hospital.getWheelchairYN()) ? "selected" : "" %>>아니오</option>
            </select>

            <label for="updateDate">데이터갱신일자</label>
            <input type="date" id="updateDate" name="updateDate" value="<%= hospital.getUpdateDate() %>" />

            <label for="staffNum">의료인수</label>
            <input type="number" id="staffNum" name="staffNum" value="<%= hospital.getStaffNum() %>" min="0" />

            <label for="state">영업상태</label>
            <input type="number" id="state" name="state" value="<%= hospital.getState() %>" min="0" max="9" />

            <label for="roomNum">입원실수</label>
            <input type="number" id="roomNum" name="roomNum" value="<%= hospital.getRoomNum() %>" min="0" />

            <label for="bedNum">병상수</label>
            <input type="number" id="bedNum" name="bedNum" value="<%= hospital.getBedNum() %>" min="0" />

            <label for="areaSize">면적(m²)</label>
            <input type="number" id="areaSize" name="areaSize" value="<%= hospital.getAreaSize() %>" step="0.01" min="0" />

            <button type="submit">수정 완료</button>
        </form>
    </div>
</body>
</html>
