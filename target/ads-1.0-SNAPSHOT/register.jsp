<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 07.04.2016
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container">

    <form action="/user/register" method="post">

        <div class="form-group">
            <label for="login">Login</label>
            <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                <input type="text" class="form-control" id="login"
                       placeholder="Enter login" name="login"
                       value="${user.login}" maxlength="20"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                <input type="text" class="form-control" id="password"
                       placeholder="Enter last name" name="password"
                       value="${user.password}" maxlength="20"/>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                <input type="text" class="form-control" id="email"
                       placeholder="Enter login" name="email"
                       value="${user.email}" maxlength="20"/>
            </div>
        </div>

        <div class=" row button-bar text-center">
            <button type="submit" class="btn btn-primary btn-lg" id="send_form">Register</button>
        </div>
        <div class="error">
            <c:forEach var="error" items="${errors}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    <%--<span class="sr-only">Error:</span>--%>
                        <span style="color:darkred;font-size: 15px"> ${error}</span>
                </div>
            </c:forEach>
        </div>
    </form>
</div>

</body>
</html>