<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
    <head>
        <title>Authentication page</title>
    </head>
    <body>
        <h3>Login</h3>
        <form action="${pageContext.request.contextPath}/" method="post">
            <label for="name">login</label>
            <input type="text" name="login" id="name"><br>
            <label for="password">password</label>
            <input type="password" name="password" id="password"><br>
            <button type="submit">Sign in</button>
        </form>
        <c:if test="${not empty sessionScope.flash}">
            ${sessionScope.flash}
        </c:if>
        <div>
            <p><a href="${pageContext.request.contextPath}/registration">Sign up</a></p>
        </div>
        <% session.removeAttribute("flash"); %>
    </body>
</html>
