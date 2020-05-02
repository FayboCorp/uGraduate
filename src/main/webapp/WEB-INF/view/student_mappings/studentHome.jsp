<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 3/19/2020
  Time: 7:27 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Welcome, ${student.firstName}
<br>
<a href="${pageContext.request.contextPath}/student/courselist"> See All courses in your major </a> <br>
<a href="${pageContext.request.contextPath}/student/viewRegistered"> See all your registered courses </a> <br>
<a href="${pageContext.request.contextPath}/student/register"> Register for Course </a> <br>
</body>
</html>
