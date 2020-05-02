<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 4/7/2020
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action= "${pageContext.request.contextPath}/advisor/addedProfessor" modelAttribute= "professor" method="post">

    Enter the professor's full name  <form:input path="name"/> <br>

    <input type="submit" value="Submit">

</form:form>

</body>
</html>
