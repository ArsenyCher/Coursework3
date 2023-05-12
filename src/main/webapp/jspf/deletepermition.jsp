<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="header.jsp"/>
<div id="main">
    <section>
        <aside class="leftAside">
            <h3>Список прав</h3>
            <table class="table table-sm" id="table-info">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Название прав</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Дата создания</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="permitions" items="${permitions}">
                    <tr>
                        <td>${permitions.getId()}</td>
                        <td>${permitions.getPermitionName()}</td>
                        <td>${permitions.getDescription()}</td>
                        <td>${permitions.getDateCreate()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </aside>
    </section>
    <section>
        <article>
            <h3>Удалить права</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="idpermition"--%><label for="idpermition" class="col-sm-3 col-form-label">Номер прав</label>
                            <input type="number" name="idpermition" class="form-control" readonly value="${permitionsDelete[0].getId()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="namepermition"--%><label for="namepermition">Название прав</label>
                            <input type="text" name="namepermition" class="form-control" id="staticIdvote"  value="${permitionsDelete[0].getPermitionName()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="descroption"--%><label for="descroption" class="col-sm-3 col-form-label">Описание</label>
                            <input type="text" name="descroption" class="form-control" value="${permitionsDelete[0].getDescription()}"/>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-sm-7">
                            <%--@declare id="datecreate"--%><label for="datecreate" class="col-sm-6 col-form-label">Дата создания прав</label>
                            <input type="date" name="datecreate" class="form-control" value="${permitionsDelete[0].getDateCreate()}"/>
                        </div>
                    </div>
                    <button type="submit"
                            class="btn btn-primary">Удалить</button>
                    <a href='<c:url value="/permition" />'
                       role="button"
                       class="btn btn-secondary">Отменить/Возврат</a>
                </form>
            </div>
        </article>
    </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>