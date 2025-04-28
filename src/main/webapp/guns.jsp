<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>AirSoftShop - Оружие</title>
    <%@include file="../includes/head.jsp"%>
    <style>
        .card {
            transition: transform 0.2s;
        }
        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .status-badge {
            position: absolute;
            top: 10px;
            right: 10px;
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <%@include file="../includes/header.jsp"%>

    <main>
        <div class="container mt-5">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Наше оружие</h2>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addGunModal">
                    Добавить оружие
                </button>
            </div>

            <div class="row" id="gunsContainer">
                <c:forEach items="${guns}" var="gun">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100 position-relative">
                            <span class="badge ${gun.status == 'AVAILABLE' ? 'bg-success' : 'bg-danger'} status-badge">
                                ${gun.status == 'AVAILABLE' ? 'В наличии' : 'Нет в наличии'}
                            </span>
                            <img src="${gun.img}" class="card-img-top" alt="${gun.name}">
                            <div class="card-body">
                                <h5 class="card-title">${gun.name}</h5>
                                <p class="card-text">${gun.description}</p>
                                <ul class="list-group list-group-flush mb-3">
                                    <li class="list-group-item"><strong>Производитель:</strong> ${gun.producer}</li>
                                    <li class="list-group-item"><strong>Модель:</strong> ${gun.model}</li>
                                    <li class="list-group-item"><strong>Цена:</strong> ${gun.price} руб.</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Modal для добавления нового оружия -->
        <div class="modal fade" id="addGunModal" tabindex="-1" aria-labelledby="addGunModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addGunModalLabel">Добавить новое оружие</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="addGunForm">
                            <div class="mb-3">
                                <label for="name" class="form-label">Название</label>
                                <input type="text" class="form-control" id="name" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Описание</label>
                                <textarea class="form-control" id="description" rows="3" required></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="price" class="form-label">Цена</label>
                                <input type="number" step="0.01" class="form-control" id="price" required>
                            </div>
                            <div class="mb-3">
                                <label for="model" class="form-label">Модель</label>
                                <input type="text" class="form-control" id="model" required>
                            </div>
                            <div class="mb-3">
                                <label for="producer" class="form-label">Производитель</label>
                                <input type="text" class="form-control" id="producer" required>
                            </div>
                            <div class="mb-3">
                                <label for="img" class="form-label">URL изображения</label>
                                <input type="url" class="form-control" id="img" required>
                            </div>
                            <div class="mb-3">
                                <label for="status" class="form-label">Статус</label>
                                <select class="form-select" id="status" required>
                                    <option value="AVAILABLE">В наличии</option>
                                    <option value="OUT_OF_STOCK">Нет в наличии</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                        <button type="button" class="btn btn-primary" id="submitGun">Добавить</button>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@include file="../includes/footer.jsp"%>
    <%@include file="../includes/scripts.jsp"%>

    <script>
        // Проверяем, что jQuery загружен
        if (typeof jQuery == 'undefined') {
            console.error('jQuery не загружен!');
            alert('Ошибка: jQuery не загружен. Пожалуйста, обновите страницу.');
        }

        $(document).ready(function() {
            console.log('jQuery загружен и готов к использованию');
            
            $('#submitGun').click(function() {
                console.log('Кнопка "Добавить" нажата');
                
                // Проверяем, что форма валидна
                if (!$('#addGunForm')[0].checkValidity()) {
                    $('#addGunForm')[0].reportValidity();
                    return;
                }

                const gunData = {
                    name: $('#name').val(),
                    description: $('#description').val(),
                    price: parseFloat($('#price').val()),
                    model: $('#model').val(),
                    producer: $('#producer').val(),
                    img: $('#img').val(),
                    status: $('#status').val()
                };

                console.log('Sending data:', gunData);
                console.log('Request URL:', '${pageContext.request.contextPath}/guns');

                $.ajax({
                    url: '${pageContext.request.contextPath}/guns',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(gunData),
                    success: function(response) {
                        console.log('Success response:', response);
                        $('#addGunModal').modal('hide');
                        location.reload();
                    },
                    error: function(xhr, status, error) {
                        console.error('Error details:', {
                            status: status,
                            error: error,
                            response: xhr.responseText,
                            statusCode: xhr.status
                        });
                        alert('Ошибка при добавлении оружия: ' + xhr.responseText);
                    }
                });
            });

            // Очистка формы при закрытии модального окна
            $('#addGunModal').on('hidden.bs.modal', function () {
                $('#addGunForm')[0].reset();
            });
        });
    </script>
</body>
</html>