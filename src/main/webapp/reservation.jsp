<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content gallery pad1">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <c:choose>
                        <c:when test="${step eq 'first'}">
                            <div class="col-md-9">
                                <h2>Бронювання столика</h2>
                                <form class="myForm" method="POST" action="/reservation/${step}.htm">
                                    <input type="text" name="name" placeholder="І'мя:" value="" required/>
                                    <input type="text" name="phone" placeholder="Телефон: +380919096699:" value=""
                                           required/>
                                    <input type="number" name="amountpeople" max="4" min="2" value=""
                                           placeholder="КІлькість людей" required>
                                    <input type="date" name="date" required>
                                    <select id="country" name="time" required>
                                        <option value="10:00">10:00</option>
                                        <option value="10:30">10:30</option>
                                        <option value="11:00">11:00</option>
                                        <option value="11:30">11:30</option>
                                        <option value="12:00">12:00</option>
                                        <option value="12:30">12:30</option>
                                        <option value="13:00">13:00</option>
                                        <option value="13:30">13:30</option>
                                        <option value="14:00">14:00</option>
                                        <option value="14:30">14:30</option>
                                        <option value="15:00">15:00</option>
                                        <option value="15:30">15:30</option>
                                        <option value="16:00">16:00</option>
                                        <option value="16:30">16:30</option>
                                        <option value="17:00">17:00</option>
                                        <option value="17:30">17:30</option>
                                        <option value="18:00">18:00</option>
                                        <option value="18:30">18:30</option>
                                        <option value="19:00">19:00</option>
                                    </select>
                                    <textarea name="message" placeholder="Побажання"></textarea>
                                    <input type="submit" value="Відправити" class="btn"/>
                                </form>
                            </div>
                            <div class="col-md-3">
                                <h2>Правила</h2>
                                <ul class="list">
                                        <%--<li>Предложение действительно только для выбранного времени, даты, и количества--%>
                                        <%--человек.--%>
                                    <li> Пропозиція дійсна тільки для обраного часу, дати та кількості людей.
                                    </li>
                                    <li>
                                            <%--Имя должно быть только с использованием кирилицы--%>
                                        Ім'я має бути тільки з використанням кирилиці.
                                    </li>
                                    <li>
                                            <%--Телефон должен начинатьcя на +380 и иметь 9 цифр--%>
                                        Телефон має починатися з +380 та містити 9 цифр.
                                    </li>
                                    <li>
                                            <%--Количество человек должно быть от 2-4--%>
                                        Кількість людей має бути від 2-4.
                                    </li>
                                    <li>
                                            <%--Предзаказ должен быть более чем 150 грн--%>
                                        Предзамовлення має бути від 150ти грн.
                                    </li>
                                    <li>
                                            <%--Если ответ не поступил в течении 2 часов, наберите нас по телефону указанному в--%>
                                            <%--разделе "Контакты"--%>
                                        Якщо відповідь не прийшла протягом 2-ох годин, наберіть на за телефоном,
                                        вказаному
                                        у розділі "Контакти".
                                    </li>
                                </ul>
                            </div>
                        </c:when>
                        <c:when test="${step eq 'second'}">
                            <div class="col-md-9">
                                <c:forEach var="items" items="${dish}">
                                    <form class="myForm" action="/reservation/addpreorder/${items.id}.htm"
                                          method="POST">
                                        <div class="col-md-4">
                                            <div class="maxheight">
                                                <h3 class="text-center"> ${items.name}</h3>
                                                <a href="/dishImage/${items.id}.htm" class="gall_item text-center" style="background-color: white">
                                                    <img style="width:150px; height:150px"
                                                         src="/dishImage/${items.id}.htm" alt="">
                                                </a>
                                                Вага: <span class="color1 fw">${items.amount} гр. </span> <br>

                                                <c:choose>
                                                    <c:when test="${items.discount != 0}">
                                                        Ціна: <span class="color1 fw"><s>${items.price}₴</s></span>
                                                        <span class="color1 fw"> ${items.price-items.price*items.discount/100} ₴</span>
                                                        <input type="hidden"
                                                               value="${items.price-items.price*items.discount/100}"
                                                               name="price">
                                                    </c:when>
                                                    <c:otherwise>
                                                        Ціна: <span class="color1 fw"> ${items.price} ₴</span>
                                                        <input type="hidden" value="${items.price}" name="price">
                                                    </c:otherwise>
                                                </c:choose>
                                                <input type="hidden" value="${items.name}" name="dishName">
                                                <input type="number" name="amount" placeholder="Количество" max="10"
                                                       required>
                                                <input type="submit" value="Додати в кошик">
                                            </div>
                                        </div>
                                    </form>
                                </c:forEach>
                            </div>
                            <div class="col-md-3">
                                <h2>Ваші дані: </h2>
                                <c:forEach var="var" items="${sessionScope.reservationConfig}">
                                    Ім'я: ${var.name} <br>
                                    Телефон: ${var.phone} <br>
                                    Кількість людей: ${var.amountPeople} <br>
                                    Дата: ${var.date} <br>
                                    Час: ${var.time} <br>
                                    Побажання: ${var.message} <br>
                                </c:forEach>
                                <form class="myForm" action="/reservation/changeData.htm">
                                    <input type="submit" value="Змінити дані">
                                </form>
                            </div>
                            <div class="col-md-3">
                                <h2>Чек</h2>
                                <table>
                                    <tbody>
                                    <c:set var="total" value="${0}"/>
                                    <c:forEach var="items" items="${sessionScope.preOrderList}">
                                        <tr>
                                            <td>${items.dishName} |</td>
                                            <td>${items.amount} |</td>
                                            <td>${items.price} грн |</td>
                                            <c:set var="total" value="${total + items.price}"/>
                                            <td><a href="/reservation/delete/${items.dishID}.htm">X</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <hr>
                                <p class="text-center">Сума вашого предзамовлення: ${total} грн</p>
                                <hr>
                                <c:if test="${total>=150}">
                                    <form class="myForm" action="/reservation/complite.htm">
                                        <input type="submit" value="Заказать">
                                    </form>
                                </c:if>
                            </div>
                            <div class="col-md-3">
                                <h2>Правила</h2>
                                <ul class="list">
                                        <%--<li>Предложение действительно только для выбранного времени, даты, и количества--%>
                                        <%--человек.--%>
                                    <li> Пропозиція дійсна тільки для обраного часу, дати та кількості людей.
                                    </li>
                                    <li>
                                            <%--Имя должно быть только с использованием кирилицы--%>
                                        Ім'я має бути тільки з використанням кирилиці.
                                    </li>
                                    <li>
                                            <%--Телефон должен начинатьcя на +380 и иметь 9 цифр--%>
                                        Телефон має починатися з +380 та містити 9 цифр.
                                    </li>
                                    <li>
                                            <%--Количество человек должно быть от 2-4--%>
                                        Кількість людей має бути від 2-4.
                                    </li>
                                    <li>
                                            <%--Предзаказ должен быть более чем 150 грн--%>
                                        Предзамовлення має бути від 150ти грн.
                                    </li>
                                    <li>
                                            <%--Если ответ не поступил в течении 2 часов, наберите нас по телефону указанному в--%>
                                            <%--разделе "Контакты"--%>
                                        Якщо відповідь не прийшла протягом 2-ох годин, наберіть на за телефоном,
                                        вказаному
                                        у розділі "Контакти".
                                    </li>
                                </ul>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <div class="col-md-9">
                                <h2>Бронювання столика</h2>
                                <form class="myForm" method="POST" action="/reservation/first.htm">
                                    <input type="text" name="name" placeholder="Ім'я:" value="" required/>
                                    <input type="text" name="phone" placeholder="Телефон: +380919096699:" value=""
                                           required/>
                                    <input type="number" name="amountpeople" max="4" min="2" value=""
                                           placeholder="Кількість людей" required>
                                    <input type="date" name="date" required>
                                    <select name="time" required>
                                        <option value="10:00">10:00</option>
                                        <option value="10:30">10:30</option>
                                        <option value="11:00">11:00</option>
                                        <option value="11:30">11:30</option>
                                        <option value="12:00">12:00</option>
                                        <option value="12:30">12:30</option>
                                        <option value="13:00">13:00</option>
                                        <option value="13:30">13:30</option>
                                        <option value="14:00">14:00</option>
                                        <option value="14:30">14:30</option>
                                        <option value="15:00">15:00</option>
                                        <option value="15:30">15:30</option>
                                        <option value="16:00">16:00</option>
                                        <option value="16:30">16:30</option>
                                        <option value="17:00">17:00</option>
                                        <option value="17:30">17:30</option>
                                        <option value="18:00">18:00</option>
                                        <option value="18:30">18:30</option>
                                        <option value="19:00">19:00</option>
                                    </select>
                                    <textarea name="message" placeholder="Пожелания"></textarea>
                                    <input type="submit" value="Відправити" class="btn"/>
                                </form>
                            </div>
                            <div class="col-md-3">
                                <h2>Правила</h2>
                                <ul class="list">
                                        <%--<li>Предложение действительно только для выбранного времени, даты, и количества--%>
                                        <%--человек.--%>
                                    <li> Пропозиція дійсна тільки для обраного часу, дати та кількості людей.
                                    </li>
                                    <li>
                                            <%--Имя должно быть только с использованием кирилицы--%>
                                        Ім'я має бути тільки з використанням кирилиці.
                                    </li>
                                    <li>
                                            <%--Телефон должен начинатьcя на +380 и иметь 9 цифр--%>
                                        Телефон має починатися з +380 та містити 9 цифр.
                                    </li>
                                    <li>
                                            <%--Количество человек должно быть от 2-4--%>
                                        Кількість людей має бути від 2-4.
                                    </li>
                                    <li>
                                            <%--Предзаказ должен быть более чем 150 грн--%>
                                        Предзамовлення має бути від 150ти грн.
                                    </li>
                                    <li>
                                            <%--Если ответ не поступил в течении 2 часов, наберите нас по телефону указанному в--%>
                                            <%--разделе "Контакты"--%>
                                        Якщо відповідь не прийшла протягом 2-ох годин, наберіть на за телефоном,
                                        вказаному
                                        у розділі "Контакти".
                                    </li>
                                </ul>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</section>
<%--<div class="form_block">--%>
<%--<div class="container">--%>
<%--<div class="row">--%>
<%--<div class="grid_11">--%>
<%--<h2>Правила резерации</h2>--%>
<%--<p>Предложение действительно только для выбранного времени, даты, и количества человек.</p>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<!--==============================
<jsp:include page="includes/footer.jsp" flush="true"/>
