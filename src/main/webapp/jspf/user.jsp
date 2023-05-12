<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Пользователи</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div id="main">
        <section>
            <aside class="leftAside">
                <h3>Данные о пользователе</h3>
                <table>
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">ФИО</th>
                        <th scope="col">Пароль</th>
                        <th scope="col">Email</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="users" items="${users}">
                        <tr>
                            <td>${users.getId()}</td>
                            <td>${users.getUserName()}</td>
                            <td>${users.getPassword()}</td>
                            <td>${users.getEmail()}</td>
                            <td>${users.getStatus()}</td>
                            <td width="20"><a
                                    href='<c:url value="/edituser?id=${users.getId()}"/>'
                                    role="button" class="btn btn-outline-primary">
                                <img alt="Редактировать" src=""></a>
                            </td>
                            <td width="20"><a
                                href='<c:url value="/deleteuser?id=${users.getId()}"/>'
                                role="button" class="btn btn-outline-primary">
                                <img alt="Удалить" src=""></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </aside>
        </section>
        <section>
            <article>
                <h3>Добавить пользователя</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <div class="col-sm-7">
                            <%--@declare id="username"--%><label for="username" class="col-sm-3 col-form-label">ФИО</label>
                            <input type="text" name="username" class="form-control"/>
                            </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-sm-7">
                            <%--@declare id="password"--%><label for="password" class="col-sm-3 col-form-label">Пароль</label>
                            <input type="text" name="password" class="form-control"/>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-sm-7">
                            <%--@declare id="email"--%><label for="email" class="col-sm-3 col-form-label">Email</label>
                            <input type="text" name="email" class="form-control"/>
                                </div>
                            </div>

                            <div class="mb-3 row">
                                <div class="col-sm-7">
                            <%--@declare id="status"--%><label for="status" class="col-sm-3 col-form-label">Статус</label>
                            <select name="status" class="form-control">
                                <option disabled>Выберите статус</option>
                                <option value="true">true</option>
                                <option value="false">false</option>
                            </select>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary">Добавить</button>


                    </form>
                </div>
            </article>
        </section>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>