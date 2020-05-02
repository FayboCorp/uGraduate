<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 4/18/2020
  Time: 5:19 PM
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


<form:form action= "${pageContext.request.contextPath}/student/registered" method="post">

    <select name="section">
        <c:forEach items="${sections}" var="obj">
            <option value="${obj.className}">${obj.className}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Submit">

</form:form>


</body>
</html>
