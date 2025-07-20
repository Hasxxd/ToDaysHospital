<form action="login.do" method="post">
    아이디: <input type="text" name="memberId" /><br />
    비밀번호: <input type="password" name="password" /><br />
    <input type="submit" value="로그인" />
</form>
<% String err = (String) request.getAttribute("error"); %>
<%= (err != null) ? err : "" %>