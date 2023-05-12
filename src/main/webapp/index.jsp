<%@page language="java" contentType="text/html"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>
<jsp:include page="jspf/header.jsp" />
<div id="main">
    <h2>Функции системы</h2>
    <ul>
        <li><a href="user">Пользователи</a>
        <li><a href="permition">Права</a>
        <li><a href="role">Должность</a>
        <li><a href="assigment">Права</a>
    </ul>
</div>
<jsp:include page="jspf/footer.jsp" />
</body>
</html>