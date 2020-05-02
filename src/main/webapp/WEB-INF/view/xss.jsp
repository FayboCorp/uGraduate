<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 3/21/2020
  Time: 10:33 AM
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="${pageContext.request.contextPath}/advisor/XSSExample-next" method="get">
    <label>
        <input type="text" placeholder="Enter Message" name="inputValue">
        <input type="submit" value="Submit">
    </label>
</form:form>

</body>
</html>
