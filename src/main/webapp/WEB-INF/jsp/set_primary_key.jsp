<%--
  Created by IntelliJ IDEA.
  User: Olha_Chuchuk
  Date: 10/3/2017
  Time: 8:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Start page</title>
</head>
<body>

<form action="create_constructed_table" method="post">
    <c:forEach items="${config.getColumns()}" var="column" varStatus="i">
        <input type="radio" value="${i.index}" name="keyColumn">${column.getName()} <br/>
    </c:forEach>
    <input type="submit" value="Create table"/>
</form>

</body>
</html>