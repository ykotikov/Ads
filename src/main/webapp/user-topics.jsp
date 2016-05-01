<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 09.04.2016
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Topics</title>
</head>
<body>
<c:forEach var="ad" items="${ads}">
    <table>
        <tbody>
            <tr>
                <td>
                    <a href="advertising/${ad.id}">${ad.body}</a>
                </td>
                <td>
                    <a href="/user/profile/topics/delete/${ad.id}">delete</a>
                </td>
            </tr>
        </tbody>
    </table>
    <hr>
</c:forEach>
</body>
</html>
