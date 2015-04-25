<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>
    <table>
        <tr>
            <th>Name</th>
            <th>Departments</th>
            <th>Details</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="#{employees}" var="employee">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.department}</td>
                <td>
                    <a href="/employees/${employee.id}">Go to page</a>
                </td>
                <td>
                    <sf:form action="employees/${employee.id}" method="delete">
                        <input type="submit" value="" />
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div>
        <a href="/">Go back</a>
    </div>
</body>
</html>