<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Construct table</title>
</head>

<style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
    }
</style>

<body>

Columns: <br/>
<table id="columns">
    <thead>
    <tr>
        <th>Name</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>

<br/>Add column: <br/>
    Name: <input type="text" name="newColumnName" id="columnName">
    Type:
    <select name="newColumnType" id="columnType">
        <option value="IntegerType">int</option>
        <option value="RealType">real</option>
        <option value="CharType">char</option>
        <option value="PictureType">picture</option>
        <option value="RealIntervalType">realInterval</option>
    </select>
    <button id="addButton">Add</button>
<br/>

<br/>
<form action="set_primary_key" method="post">
    Table name: <input type="text" name="tableName"/>
    <input type="hidden" value="${config}" name="config"/>
    <input type="submit" value="Create table"/>
</form>
<br/>
<a href="<c:url value="/database"/>">Back to database</a>

<script src="../js/jquery-3.2.1.js"></script>
<script src="../js/main.js"></script>

</body>
</html>