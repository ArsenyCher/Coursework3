<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



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
      <h3>Список прав</h3>
      <table>
        <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Название права</th>
          <th scope="col">Описание</th>
          <th scope="col">Дата создания</th>
          <th scope="col">Редактировать</th>
          <th scope="col">Удалить</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="permition" items="${permitions}">
          <tr>
            <td>${permition.getId()}</td>
            <td>${permition.getPermitionName()}</td>
            <td>${permition.getDescription()}</td>
            <td>${permition.getDateCreate()}</td>
            <td width="20"><a
                    href='<c:url value="/editpermition?id=${permition.getId()}" />'
                    role="button" class="btn btn-outline-primary">
              <img alt="Редактировать" src="images/icon-edit.png"></a>
            </td> <td width="20"><a
                  href='<c:url value="/deletepermition?id=${permition.getId()}"/>'
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
      <h3>Добавить права</h3>
      <div class="text-article">
        <form method="POST" action="">
          <div class="mb-3 row">
            <div class="col-sm-7">
            <%--@declare id="namepermition"--%><label for="namepermition" class="col-sm-6 col-form-label">Название прав</label>
              <input type="text" name="namepermition" class="form-control"/>
            </div>
          </div>
          <div class="mb-3 row">
            <div class="col-sm-7">
            <%--@declare id="descroption"--%><label for="descroption" class="col-sm-6 col-form-label">Описание</label>
            <input type="text" name="descroption" class="form-control"/>
            </div>
          </div>
          <div class="mb-3 row">
            <div class="col-sm-7">
            <%--@declare id="datecreate"--%><label for="datecreate" class="col-sm-6 col-form-label">Дата создания</label>
            <input type="date" name="datecreate" class="form-control"/>
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
