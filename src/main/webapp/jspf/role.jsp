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
                <h3>Список ролей</h3>
                <table>
                    <thead>
                    <tr>
                        <th scope="col">Код</th>
                        <th scope="col">Права</th>
                        <th scope="col">Название роли</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Редактировать</th>
                        <th scope="col">Удалить</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="roles" items="${roles}">
                        <tr>
                            <td>${roles.getId()}</td>
                            <td>${roles.getPermitionId()}</td>
                            <td>${roles.getNameRole()}</td>
                            <td>${roles.getDescription()}</td>
                            <td width="20"><a
                                    href='<c:url value="/editrole?id=${roles.getId()}" />'
                                    role="button" class="btn btn-outline-primary">
                                <img alt="Редактировать" src="images/icon-edit.png"></a>
                            </td> <td width="20"><a
                                href='<c:url value="/deleterole?id=${roles.getId()}"/>'
                                role="button" class="btn btn-outline-primary"> <img
                                alt="Удалить" src="images/icon-delete.png"></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </aside>
        </section>
        <section>
            <article>
                <h3>Добавить роль</h3>
                <div class="text-article">
                    <form method="POST" action="">
                        <div class="mb-3 row">
                            <div class="col-sm-7">
                            <%--@declare id="permition"--%><label for="permition">Права</label>
                            <select name="permition" class="form-control">
                                <option disabled>Выберите права</option>
                                <c:forEach var="permitions" items="${permitions}">
                                    <option value="${permitions}">
                                            ${permitions.getPermitionName()}
                                    </option>
                                </c:forEach>
                            </select>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-7">
                            <%--@declare id="namerole"--%><label for="namerole" >Название роли</label>
                            <input type="text" name="namerole" class="form-control"/>
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <div class="col-sm-7">
                            <%--@declare id="descroption"--%><label for="descroption" >Описание роли</label>
                            <input type="text" name="descroption" class="form-control"/>
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