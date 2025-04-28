<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AirSoftShop - Товары</title>
    <%@include file="includes/head.jsp"%>
</head>
<body>
    <%@include file="includes/header.jsp"%>
<main>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Наши товары</h2>

        <div class="row">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="https://live.staticflickr.com/3102/3228910825_132b74eb7b_b.jpg" width="300" height="200" class="card-img-top" alt="Пневматическое оружие">
                    <div class="card-body">
                        <h5 class="card-title">Пневматическое оружие</h5>
                        <p class="card-text">Широкий выбор пневматического оружия для страйкбола.</p>
                        <a href="${pageContext.request.contextPath}/guns" class="btn btn-primary">Подробнее</a>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="https://images1.ynet.co.il/PicServer5/2017/11/16/8158279/815826701000100980654no.jpg" width="300" height="200" class="card-img-top" alt="Экипировка">
                    <div class="card-body">
                        <h5 class="card-title">Экипировка</h5>
                        <p class="card-text">Все необходимое для комфортной и безопасной игры.</p>
                        <button class="btn btn-secondary" disabled>В разработке</button>
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-4">
                <div class="card">
                    <img src="https://swa-store.ru/image/cache/import_files/66/66c0979bdefb11eb818d0015175b484d_b500baa8dfe811eb818d0015175b484d-1300x1000-product_popup.jpg" width="300" height="200" class="card-img-top" alt="Аксессуары">
                    <div class="card-body">
                        <h5 class="card-title">Аксессуары</h5>
                        <p class="card-text">Полезные мелочи для улучшения вашего игрового опыта.</p>
                        <button class="btn btn-secondary" disabled>В разработке</button>
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