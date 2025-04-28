<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AirSoftShop - Контакты</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/header.jsp"%>
<main>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Наши контакты</h2>

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Контактная информация</h5>
                        <p class="card-text">
                            <strong>Адрес:</strong> г. Москва, ул. Страйкбольная, д. 42<br>
                            <strong>Телефон:</strong> +7 (495) 123-45-67<br>
                            <strong>Email:</strong> info@airsoftshop.ru<br>
                            <strong>Режим работы:</strong> Пн-Пт: 10:00-20:00, Сб-Вс: 11:00-18:00
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Как нас найти</h5>
                        <div class="embed-responsive embed-responsive-16by9">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m24!1m12!1m3!1d1021163.8739697227!2d38.4040978423455!3d1.2149274188309918!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m9!3e6!4m3!3m2!1d0.8586172999999999!2d38.682704199999996!4m3!3m2!1d0.8733785!2d38.6861375!5e0!3m2!1sru!2sru!4v1745822414545!5m2!1sru!2sru"
                                    width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
    <%@include file="includes/footer.jsp"%>
    <%@include file="includes/scripts.jsp"%>
</body>
</html>