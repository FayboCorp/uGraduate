<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 4/18/2020
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action= "${pageContext.request.contextPath}/student/changedPassword" method="post">

    <label>
        Enter your current password: <input type="text" name="currentPass"> <br>

        Enter your new password: <input type="text" name="newPassword"> <br>

    </label>
            <%--
    <form:form action="${pageContext.request.contextPath}/advisor/studentByName" method="get">
        <label>
            <input type="text" placeholder="Search by student name" name="studentName">
            <input type="submit" value="Submit">
        </label>
    </form:form>
    --%>
</form:form>

</body>
</html>
