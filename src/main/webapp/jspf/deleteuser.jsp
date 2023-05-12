<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<head>
  <meta charset="UTF-8">
  <title>Редактирование пользователя</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<div id="main">
  <section>
    <aside class="leftAside">
      <h3>Данные о пользователе</h3>
      <table class="table table-sm" id="table-info">
        <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">ФИО</th>
          <th scope="col">Пароль</th>
          <th scope="col">Email</th>
          <th scope="col">Активность</th>
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
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </aside>
  </section>
  <section>
    <article>
      <h3>Удалить пользователя</h3>
      <div class="text-article">
        <form method="POST" action="">
          <div class="mb-3 row">
            <div class="col-sm-7">
              <%--@declare id="userid"--%><label for="userid" class="col-sm-3 col-form-label">ID</label>
              <input type="number" name="userid" class="form-control" readonly value="${usersDelete[0].getId()}"/>
            </div>

          </div>

          <div class="mb-3 row">
            <div class="col-sm-7">
              <%--@declare id="username"--%><label for="username" class="col-sm-3 col-form-label">ФИО</label>
              <input type="text" name="username" class="form-control" value="${usersDelete[0].getUserName()}"/>
            </div>
          </div>

          <div class="mb-3 row">
            <div class="col-sm-7">
              <%--@declare id="password"--%><label for="password" class="col-sm-3 col-form-label">Пароль</label>
              <input type="text" name="password" class="form-control" value="${usersDelete[0].getPassword()}"/>
            </div>
          </div>

          <div class="mb-3 row">
            <div class="col-sm-7">
              <%--@declare id="email"--%><label for="email" class="col-sm-3 col-form-label">Email</label>
              <input type="text" name="email" class="form-control" value="${usersDelete[0].getEmail()}"/>
            </div>
          </div>

          <div class="mb-3 row">
            <div class="col-sm-7">
            <%--@declare id="status"--%><label for="status" class="col-sm-3 col-form-label">Статус</label>
              <select name="status" class="form-control" id="staticStatus">
                <option disabled>Выберите статус</option>
                <option value="true">true</option>
                <option value="false">false</option>
              </select>
            </div>
          </div>

          <button type="submit"
                  class="btn btn-primary">Удалить</button>
          <a href='<c:url value="/user" />'
             role="button"
             class="btn btn-secondary">Отменить/Возврат</a>
        </form>
      </div>
    </article>
    <jsp:include page="/jspf/footer.jsp" />
  </section>
</div>
</body>
</html>