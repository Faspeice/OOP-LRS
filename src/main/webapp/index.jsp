<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AirSoftShop - Главная</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/header.jsp"%>

    <main>
        <div class="container mt-5">
            <div class="jumbotron text-center">
                <h1 class="display-4">Добро пожаловать в AirSoftShop!</h1>
                <p class="lead">Лучшее оборудование для страйкбола по доступным ценам</p>
                <hr class="my-4">
                <p>Ознакомьтесь с нашим ассортиментом и специальными предложениями</p>
                <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/products" role="button">Наши товары</a>
            </div>
        </div>
    </main>

    <%@include file="includes/footer.jsp"%>
    <%@include file="includes/scripts.jsp"%>
</body>
</html>