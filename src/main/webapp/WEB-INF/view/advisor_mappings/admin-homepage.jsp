<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 3/2/2020
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Advisor Homepage</title>
</head>
<body>
Welcome to the advisor homepage <br>
select an option <br>

<a href="${pageContext.request.contextPath}/advisor/addStudent"> Add a Student </a> <br>
<a href="${pageContext.request.contextPath}/advisor/searchStudent"> Search for a Student </a> <br>
<a href="${pageContext.request.contextPath}/advisor/addSection"> Add a Section </a> <br>
<a href="${pageContext.request.contextPath}/advisor/addProfessor"> Add a Professor </a> <br>

<a href="${pageContext.request.contextPath}/advisor/attachProfessor"> Attach Professor to Section </a> <br>


</body>
</html>
