<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/2/2017
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="domain.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>
<body>

<table>
    <thead>
        <tr>
            <td>Column</td>
            <td>Type</td>
            <td>Value</td>
        </tr>
    </thead>
    <tbody>
    <form action="<c:url value="update_constructed_row"/>" method="post">
        <c:forEach items="${columns}" var="column" varStatus="i">
            <tr>
                <td>${column.getName()}</td>
                <td>${column.getColumnType()}</td>
                <td>
                    <input type="text" name="${i.index}" value="${row.get(i.index)}">
                </td>
            </tr>

        </c:forEach>
        <input type="submit" value="Update row">
    </form>
    </tbody>
</table>

</body>
</html>