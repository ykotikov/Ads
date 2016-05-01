<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yura5
  Date: 07.04.2016
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="<c:url value="${pageContext.request.contextPath}/css/user-cabinet.css"/>" rel="stylesheet"/>
    <style>
        <%@include file='/css/top.css' %>
    </style>

</head>
<body>
<jsp:include page="cabinet-bar.jsp"/>
-------------------------------------------------------------<br>
<a href="/advertising/create">New ad</a><br>
<table>
    <tbody>
    <for:forEach var="ad" items="${ads}">
        <tr>
            <td>
                <table>
                <tbody>
                    <tr>
                        <td style="font-size: 30px">
                            <a href="/advertising/${ad.id}" >${ad.body}</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            ${ad.subject}
                         </td>
                        <td>
                            <p>
                                <a href="/user/profile/${ad.authorId}">${ad.author.login}</a>
                            </p>
                        </td>
                    </tr>

                    <hr>

                </tbody>
            </table>
            </td>
        </tr>
       </for:forEach>
    </tbody>
</table>
<c:if test="${!empty ads}">
    <div class="panel-default">
        <div class="row">
            <ul class="pager">
                <c:if test="${!empty paging}">
                    <li>
                        <c:if test="${paging.page != 1}">

                            <a type="button" href="/advertising?page=${paging.page - 1}" name="prev"
                               class="btn btn-sm btn-success  prev">&larr;
                                Previous
                            </a>

                        </c:if>
                    </li>
                    <li>
                        <c:if test="${paging.page lt paging.pagesCount}">
                            <a type="bytton" href="/advertising?page=${paging.page + 1}" name="next"
                               class="btn btn-sm btn-success next">
                                Next &rarr;</a>
                        </c:if>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</c:if>
</body>
</html>
