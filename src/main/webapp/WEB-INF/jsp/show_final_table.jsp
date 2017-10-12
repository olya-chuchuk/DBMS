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

    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>

<table>
    <thead>
    <tr>
        <c:forEach items="${table.getColumns()}" var="column">
            <th>"${column.getName()}"</th>
        </c:forEach>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${table.getRows()}" var="row">
        <tr>
            <c:forEach items="${row}" var="value" varStatus="status">
                <c:choose>
                    <c:when test="${table.getColumns().get(status.index).getColumnType().equals(ColumnType.PictureType)}">
                        <td>
                            <a href="${value}">${value}</a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>${value}</td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <td>
                <form action="<c:url value="update_row"/>" method="post">
                    <input type="hidden" name="tableName" value="${table.getName()}">
                    <input type="hidden" name="key" value="${row.get(table.getKeyColumn())}">
                    <input type="submit" value="Update row">
                </form>
            </td>
            <%--<td>--%>
                <%--<form action="delete_row">--%>
                    <%--<input type="hidden" name="key" value="${row.get(table.getKeyColumn())}">--%>
                    <%--<input type="submit" value="Delete row">--%>
                <%--</form>--%>
            <%--</td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
<a href="<c:url value="/database"/>">Back to database</a>
<br/>
<br/>
<a href="<c:url value="index"/>">Index page</a>

</body>
</html>