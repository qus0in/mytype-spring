<%--
  Created by IntelliJ IDEA.
  User: morgan
  Date: 25. 3. 19.
  Time: 오후 2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>유저</h1>
    <p>
        <%= request.getAttribute("users") %>
    </p>
    <form method="post" action="<%= request.getContextPath() %>/user">
        <input type="hidden" name="user_id" value="">
        <label>
            이름
            <input name="name" required>
        </label>
        <label>
            이메일
            <input name="email" type="email" required>
        </label>
        <label>
            연락처
            <input name="phone" required>
        </label>
        <button>등록</button>
    </form>
</body>
</html>
