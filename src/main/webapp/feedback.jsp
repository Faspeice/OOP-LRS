<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AirSoftShop - Обратная связь</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/header.jsp"%>
<main>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Обратная связь</h2>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <form>
                    <div class="form-group">
                        <label for="name">Ваше имя</label>
                        <input type="text" class="form-control" id="name" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" required>
                    </div>
                    <div class="form-group">
                        <label for="subject">Тема</label>
                        <input type="text" class="form-control" id="subject" required>
                    </div>
                    <div class="form-group">
                        <label for="message">Сообщение</label>
                        <textarea class="form-control" id="message" rows="5" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Отправить</button>
                </form>
            </div>
        </div>
    </div>
</main>
    <%@include file="includes/footer.jsp"%>
    <%@include file="includes/scripts.jsp"%>
</body>
</html>