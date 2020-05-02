<%--
  Created by IntelliJ IDEA.
  User: Fabian
  Date: 3/30/2020
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function addProfessor() {
        
    }
</script>

<html>
<head>
    <title>Title</title>
</head>
<body>


<form:form action= "${pageContext.request.contextPath}/advisor/addedSection" modelAttribute= "section" method="post">


    Enter the class name  <form:input path="className"/> <br>
    <label>
    Enter the Professor's name (optional)    <input type="text" name="professorName">
    </label> <br>
    Pick Section's major
    <form:select path="major">
        <form:option value="1" label="Computer Science"   />
        <form:option value="2" label="Psychology"   />
        <form:option value="3" label="Industrial Design"   />
        <form:option value="4" label="Business"   />
        <form:option value="5" label="Economics"   />
        <form:option value="6" label="Undeclared"   />
    </form:select>

    <input type="submit" value="Submit">

</form:form>

</body>
</html>
<%--

<form:form action="${pageContext.request.contextPath}/advisor/studentById" method="get">
    <label>
    <form:option value="Computer Science" label="Computer Science"   />



        <input type="text" placeholder="Search by student id" name="studentId">
        <input type="submit" value="Submit">
    </label>
</form:form>

--%>
