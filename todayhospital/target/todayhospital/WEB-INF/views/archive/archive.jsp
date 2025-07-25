<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>자료실</title>
  <link rel="stylesheet" href="archive.css" />
</head>
<body>
  <div class="container">
    <h1>📁 자료실</h1>
    <p class="subtext">병원 안내 자료와 건강 정보를 확인하세요.</p>

    <div class="search-filter">
      <input type="text" id="searchInput" placeholder="검색어 입력" />
      <select id="categoryFilter">
        <option value="전체">전체</option>
        <option value="병원자료">병원자료</option>
        <option value="건강정보">건강정보</option>
      </select>
    </div>

    <table class="resource-table">
      <thead>
        <tr>
          <th>카테고리</th>
          <th>제목</th>
          <th>작성일</th>
          <th>다운로드</th>
        </tr>
      </thead>
      <tbody id="resourceBody">
        <%-- 
          서버에서 자료 목록을 받아와서 동적으로 출력할 경우, 
          여기서 JSP 코드를 작성하거나 JSTL 태그를 사용하세요.
          예를 들어:
          <%-- 
          List<Resource> resources = (List<Resource>) request.getAttribute("resources");
          if(resources != null) {
            for(Resource r : resources) {
        %>
          <tr>
            <td><%= r.getCategory() %></td>
            <td><%= r.getTitle() %></td>
            <td><%= r.getDate() %></td>
            <td><a href="<%= r.getDownloadLink() %>">다운로드</a></td>
          </tr>
        <%
            }
          }
        --%>
      </tbody>
    </table>
    <a href="../home/home.html" class="back-button">메인 화면으로 돌아가기</a>
  </div>

  <script src="archive.js"></script>
</body>
</html>
