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

Choose 2 tables with same structure: <br/>

<br/>Add column: <br/>
<form action="subtract_chosen_tables" method="post">
    First table:
    <select name="table1">
        <c:forEach items="${tables}" var="table">
            <option value="${table}">${table}</option>
        </c:forEach>
    </select>
    Second table:
    <select name="table2">
        <c:forEach items="${tables}" var="table">
            <option value="${table}">${table}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Subtract">
</form>
<br/>
<br/>
<a href="<c:url value="/database"/>">Back to database</a>

</body>
</html>