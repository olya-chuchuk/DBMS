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

Columns: <br/>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${config.getColumns()}" var="column">
        <tr>
            <th>${column.getName()}</th>
            <th>${column.getColumnType()}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>Add column: <br/>
<form action="add_column" method="post">
    Name: <input type="text" name="newColumnName">
    Type:
    <select name="newColumnType">
        <option value="IntegerType">int</option>
        <option value="RealType">real</option>
        <option value="CharType">char</option>
        <option value="PictureType">picture</option>
        <option value="RealIntervalType">realInterval</option>
    </select>
    <input type="submit" value="Add">
</form>
<br/>
<form action="set_primary_key" method="post">
    Table name: <input type="text" name="tableName"/>
    <input type="hidden" value="${config}" name="config"/>
    <input type="submit" value="Create table"/>
</form>
<br/>
<a href="<c:url value="/database"/>">Back to database</a>

</body>
</html>