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

    <form action="<c:url value="/database/create_database"/>" method="post">
        Database name: <input type="text" name="databaseName">
        <input type="submit" value="Create new database">
    </form>
    <br/>
    <form action="<c:url value="/database/upload_database"/>" method="post">
            Filename: <input type="text" name="fileName">
            <input type="submit" value="Upload from file">
        </form>

</body>
</html>