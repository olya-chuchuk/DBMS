<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/2/2017
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import ="domain.*" %>
<%
   scripting-language-statements
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
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
    <c:forEach items="${table.getRows()}" var="row">
        <tr>
            <c:forEach items="${row}" var="value" varStatus="status">
                <%
                    Table table = (Table) pageContent.getAttribute("table");
                    if(type.equals("PictureType")) {
                %>
                <td>
                    <a href=%{row}>${row}</a>
                </td>
                <% } else { %>
                <td>${value}</td>
                <% } %>
            </c:forEach>
            <td>
                <form action="/dbms/database/update_row">
                    <input type="hidden" name="tableName" value="${row.get(table.getKeyColumn())}">
                    <input type="submit" value="Update row">
                </form>
            </td>
            <td>
                <form action="/dbms/database/delete_row">
                    <input type="hidden" name="tableName" value="${row.get(table.getKeyColumn())}">
                    <input type="submit" value="Delete row">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br/>
<form action="/dbms/database/add_table">
    <input type="submit" value="Add row">
</form>
<br/>
<a href="<c:url value="/database/index"/>">Index page</a>

</body>
</html>