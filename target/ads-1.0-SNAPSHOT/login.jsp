<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 10.04.2016
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">

    <form action="/user/login" method="post">

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


        <div class=" row button-bar text-center">
            <button type="submit" class="btn btn-primary btn-lg" id="send_form">Login</button>
        </div>
        <div class="error">
            <c:forEach var="error" items="${errors}">
                <div class="alert alert-danger" role="alert">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span style="color: darkred;font-size: 20px">${error}</span>
                </div>
            </c:forEach>
        </div>
    </form>
</div>

</body>
</html>
