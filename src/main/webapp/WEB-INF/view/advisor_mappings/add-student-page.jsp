<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

add student page

<form:form action= "${pageContext.request.contextPath}/advisor/addedStudent" modelAttribute= "student" method="post">

    Enter Student's first name <form:input path="firstName"/>
    Enter Student's last name  <form:input path="lastName"/>

    Pick Student's major
    <form:select path="major">
        <form:option value="Computer Science" label="Computer Science"   />
        <form:option value="Psychology" label="Psychology"   />
        <form:option value="Industrial Design" label="Industrial Design"   />
        <form:option value="Business" label="Business"   />
        <form:option value="Economics" label="Economics"   />
        <form:option value="Undeclared" label="Undeclared"   />
    </form:select>

    <input type="submit" value="Submit">

</form:form>

</body>
</html>
