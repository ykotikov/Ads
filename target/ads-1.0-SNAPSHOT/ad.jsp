<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 09.04.2016
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="cabinet-bar.jsp"/>
<hr>
${ad.body}<br>
${ad.subject}<br>
${ad.author.login}
<footer>
    <a href="/advertising">
        <img height="100px" width="100px" src="https://image.freepik.com/free-icon/_318-34824.jpg"/>
    </a>
</footer>


</body>
</html>

