<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 07.04.2016
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:choose>
    <c:when test="${!empty sessionScope.userSession.login}">
        <strong style="Color:darkred">${sessionScope.userSession.login}</strong><br>
        <a href="user/profile">Кабинет</a><br>
    </c:when>
    <c:otherwise>
        <table>
            <tbody>
                <tr>
                    <td>
                        <a href="user/login">Login</a>

                    </td>
                    <td>
                        <a href="user/register">Register</a>

                    </td>
                </tr>
            </tbody>
        </table>

    </c:otherwise>
</c:choose>
---------------------------------------------------

</body>
</html>
