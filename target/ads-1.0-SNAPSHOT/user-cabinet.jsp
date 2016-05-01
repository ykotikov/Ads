<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 07.04.2016
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
User Cabinet<br>
Login:
${sessionScope.userSession.login}<br>
Password:
${sessionScope.userSession.password}<br>
Email:
${sessionScope.userSession.email}<br>

<a href="${pageContext.request.contextPath}/user/profile/topics">My Topics</a>
<a href="${pageContext.request.contextPath}/advertising" >Main</a>
<a href="${pageContext.request.contextPath}/user/logout">Logout</a>

</body>
</html>
