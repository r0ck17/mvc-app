<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Account menu</title>
    </head>
    <body>
        <h3>Account menu</h3>
        <form action="${pageContext.request.contextPath}/user" method="post">
            <label for="login">login</label>
            <input type="text" name="login" id="login" value="${sessionScope.user.login}" disabled><br>
            <label for="password">password</label>
            <input type="text" name="password" id="password" value="${sessionScope.user.password}"><br>
            <label for="email">email</label>
            <input type="text" name="email" id="email" value="${sessionScope.user.email}"><br>
            <label for="name">name</label>
            <input type="text" name="name" id="name" value="${sessionScope.user.name}"><br>
            <label for="age">age</label>
            <input type="text" name="age" id="age" value="${sessionScope.user.age}"><br>
            <button type="submit">Set new information</button>
        </form>
        <c:if test="${not empty sessionScope.flash}">
            <p>${sessionScope.flash}</p>
        </c:if>
        <c:if test="${not empty sessionScope.errors}">
            <c:forEach var="error" items="${sessionScope.errors}">
                <p>${error.message}</p>
            </c:forEach>
        </c:if>
        <% session.removeAttribute("errors"); %>
        <% session.removeAttribute("flash"); %>
        <a href="${pageContext.request.contextPath}/">logout</a>
    </body>
</html>
