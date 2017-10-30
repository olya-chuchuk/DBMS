<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/2/2017
  Time: 3:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

<h1>Database "${dbName}":</h1>

<table>
    <thead>
    <tr>
        <th>№</th>
        <th>Table name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tableList}" var="table" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${table.getName()}</td>
            <td>
                <form action="<c:url value="show_table"/>">
                    <input type="hidden" name="tableName" value="${table.getName()}">
                    <input type="submit" value="Show table">
                </form>
            </td>
            <td>
                <form action="<c:url value="delete_table"/>" method="post">
                    <input type="hidden" name="tableName" value="${table.getName()}">
                    <input type="submit" value="Delete table">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>

<a href="<c:url value="create_table_ajax"/>"><input type="submit" value="Add table"></a>

<br/>
<br/>
<form action="<c:url value="subtract_tables"/>" method="post">
    <input type="submit" value="Subtract tables">
</form>

<br/>
<br/>



<form action="<c:url value="save_to_file"/>" method="post">
    Filename: <input type="text" name="fileName">
    <input type="submit" value="Save to file">
</form>

<br/>

<a href="<c:url value="/index"/>">Index page</a>

</body>
</html>