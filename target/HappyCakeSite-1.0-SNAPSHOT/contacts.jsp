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
                <h2>Наше розташування</h2>
                <div class="map">
                    <div class="row">
                        <div class="grid_9">
                            <figure class="">
                                <iframe src="https://www.google.com/maps?q=Happy Cake % Kyiv&output=embed"
                                        style="border:0"></iframe>
                            </figure>
                        </div>
                        <c:forEach var="item" items="${contacts}">
                            <div class="grid_3">
                                <div class="map_block">
                                    <div class="map_title">Адреса:</div>
                                        ${item.address}
                                </div>
                                <div class="map_block">
                                    <div class="map_title">Телефон:</div>
                                        ${item.mobilePhone}
                                </div>
                                <div class="map_block">
                                    <div class="map_title">Пошта:</div>
                                    <a href="#">${item.cafeemail}</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="grid_8">
                <p><span class="color1 fw">Усі кафе працюють з 8.30 до 22.00</span></p>
                <p>Некоректна поведінка персоналу, хамство? Пишіть на пошту<span class="color1 fw"> reports@happycake.com </span>
                </p>
            </div>
        </div>
    </div>
</section>
<div class="form_block">
    <div class="container">
        <div class="row">
            <div class="grid_11">
                <h2>Залиши відгук про наше кафе</h2>
                <form class="myForm" action="/contacts/send.htm" method="POST">
                        <input type="text" name="name" placeholder="Имя:" value=""/>
                        <input type="email" name="email" placeholder="Почта" value=""/>
                        <input type="text" name="phone" placeholder="Телефон: +380919096699:" value=""/>
                        <textarea name="message" placeholder="Сообщение"></textarea>
                    <input type="submit" value="Відправити" class="btn"/>
                </form>
            </div>
        </div>
    </div>
</div>
<!--==============================
<jsp:include page="includes/footer.jsp" flush="true"/>
