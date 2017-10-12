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
    <title>Error page</title>
</head>
<body>

<h1>Error!</h1>

${exceptionMessage}

<br/>
<br/>

<button onclick="window.history.back();">Go Back</button>
</body>
</html>