<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title> </title>
<head>
    <meta charset="UTF-8">
    <title>Редактирование должности</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/jspf/header.jsp"/>
<div id="main">
    <section>
        <aside class="leftAside">
            <h3>Данные о назначенных правах</h3>
            <table class="table table-sm" id="table-info">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">ID пользователя</th>
                    <th scope="col">ID роли</th>
                    <th scope="col">Дата создания</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="assigment" items="${assigments}">
                    <tr>
                        <td>${assigment.getId()}</td>
                        <td>${assigment.getUserId()}</td>
                        <td>${assigment.getRoleId()}</td>
                        <td>${assigment.getDateCreate()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </aside>
    </section>
    <section>
        <article>
            <h3>Изменить назначение прав</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="userid"--%><label for="userid">Пользователь</label>
                            <select name="userid" class="form-control">
                                <option disabled>Активность</option>
                                <c:forEach var="users" items="${users}">
                                    <option value="${users}">
                                            ${users.getUserName()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="roleid"--%><label for="roleid">Роль</label>
                            <select name="roleid" class="form-control">
                                <option disabled>Активность</option>
                                <c:forEach var="roles" items="${roles}">
                                    <option value="${roles}">
                                            ${roles.getNameRole()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="datecreate"--%><label for="datecreate">Дата</label>
                            <input type="date" name="datecreate" class="form-control"/>
                        </div>
                    </div>
                    <button type="submit"
                            class="btn btn-primary">Редактировать</button>
                    <a href='<c:url value="/assigment" />'
                       role="button"
                       class="btn btn-secondary">Отменить/Возврат</a>

                </form>
            </div>
        </article>
    </section>
</div>
<jsp:include page="/jspf/footer.jsp"/>
</body>
</html>