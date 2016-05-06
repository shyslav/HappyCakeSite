<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content ctn__1">
    <div class="container">
        <div class="row">
            <div class="grid_12">
                    <div class="row">
                        <div class="grid_9">
                            <h2>Резервация</h2>
                            <form class="myForm" method="POST" action="/reservation/first.htm">
                                <input type="text" name="name" placeholder="Имя:" value="" required/>
                                <input type="text" name="phone" placeholder="Телефон: +380919096699:" value="" required/>
                                <input type="number" name="amountpeople" max="4" min="2" value="" placeholder="Количество человек" required>
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
                                <textarea name="message" placeholder="Пожелания"></textarea>
                                <input type="submit" data-type="submit" class="btn"/>
                            </form>
                        </div>
                        <div class="grid_3">
                            <h2>Правила</h2>
                            <ul class="list">
                                <li>Предложение действительно только для выбранного времени, даты, и количества человек.</li>
                                <li>
                                    Имя должно быть только с использованием кирилицы
                                </li>
                                <li>
                                    Телефон должен начинатьcя на +380 и иметь 9 цифр
                                </li>
                                <li>
                                    Количество человек должно быть от 2-4
                                </li>
                                <li>
                                    Предзаказ должен быть более чем 150 грн
                                </li>
                                <li>
                                    Если ответ не поступил в течении 2 часов, наберите нас по телефону указанному в разделе "Контакты"
                                </li>
                            </ul>
                        </div>
                    </div>
            </div>
            <div class="grid_8">
                <p><span class="color1 fw">Все кафе работает с 8.30 до 22.00. Резервация доступна с 10:00 - 19:00</span></p>
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
