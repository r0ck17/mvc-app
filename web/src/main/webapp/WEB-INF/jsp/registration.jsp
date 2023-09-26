<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h3>Registration</h3>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            <label for="login">login</label>
            <input type="text" name="login" id="login" value="${sessionScope.user.login}" required><br>
            <label for="password">password</label>
            <input type="text" name="password" id="password" value="${sessionScope.user.password}" required><br>
            <label for="name">name</label>
            <input type="text" name="name" id="name" value="${sessionScope.user.name}" required><br>
            <label for="email">email</label>
            <input type="text" name="email" id="email" value="${sessionScope.user.email}" required><br>
            <label for="age">age</label>
            <input type="text" name="age" id="age" value="${sessionScope.user.age}" required><br>
            <button type="submit">Confirm</button>
        </form>
        <c:if test="${not empty sessionScope.errors}">
            <c:forEach var="error" items="${sessionScope.errors}">
                <p>${error.message}</p>
            </c:forEach>
        </c:if>
        <% session.removeAttribute("errors"); %>
    </body>
</html>
