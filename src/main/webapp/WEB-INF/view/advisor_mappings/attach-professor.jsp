<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 4/9/2020
  Time: 4:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--

<script>
    var select = document.getElementById("dynamicProfessors");
    var professors = {
        "name": "${professor}"
    };

    for(var i = 0; i< professors.length; i++){

        console.log(professors);

    }
</script>
--%>


<html>
<head>
    <title>Title</title>
</head>
<body>


<form:form action= "${pageContext.request.contextPath}/advisor/attachedProfessor" method="post">

    <select name="section">
        <c:forEach items="${section}" var="obj">
            <option value="${obj.className}">${obj.className}</option>
        </c:forEach>
    </select>

    <select name="prof">
        <c:forEach items="${professor}" var="obj">
            <option value="${obj.name}">${obj.name}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Submit">

</form:form>


</body>
</html>



<%--

<label>
        <input type="text" placeholder="Search by student name" name="studentName">
        <input type="submit" value="Submit">
    </label>

--%>