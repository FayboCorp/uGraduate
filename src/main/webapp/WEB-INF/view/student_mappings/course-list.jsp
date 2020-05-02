<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 3/30/2020
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hi ${student.firstName}! These are all the CS courses that you need:
<br>
<br>

<c:forEach items="${sections}" var="obj">
    CRN: ${obj.CRN} | ${obj.className} | Professor: ${obj.professor.name} <br>
</c:forEach>

</body>
</html>
