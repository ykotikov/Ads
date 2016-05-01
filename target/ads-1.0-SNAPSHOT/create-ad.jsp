<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 12.04.2016
  Time: 0:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Create Ad
<form action="/advertising/create" method="post">
<div class="form-group">
    <label for="body">Body</label>
    <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
        <input type="text" class="form-control" id="body"
               placeholder="Enter login" name="body"
                maxlength="20"/>
    </div>
</div>
<div class="form-group">
    <label for="subject">Subject</label>
    <div class="input-group">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-user"></span>
                            </span>
                                <textarea class="form-control" id="subject"
                                          placeholder="Enter last name" name="subject">

                                </textarea>
    </div>
</div>


<div class=" row button-bar text-center">
    <button type="submit" class="btn btn-primary btn-lg" id="send_form">Send</button>
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
</body>
</html>
