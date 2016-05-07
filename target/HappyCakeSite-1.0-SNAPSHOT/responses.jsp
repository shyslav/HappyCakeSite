<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content">
    <div class="container">
        <div class="row">
            <%--Новости все--%>
            <br>
            <c:forEach var="items" items="${responses}">

                <div class="grid_6">
                    <div class="box" style="padding-left: 10px;">
                        <div class="blog">
                            <div class="blog_title">
                                <time datetime="${items.rDate}">
                                    <span class="fa fa-calendar"></span>
                                        ${items.rDate}
                                </time>
                                | <a href="#">${items.author} </a>
                            </div>
                            <p>${items.rText} </p>
                        </div>

                    </div>
                    <br>
                </div>

            </c:forEach>
        </div>
        <hr>
        <div class="grid_12">
            <p class="text-center"><span class="color1 fw">Всем спасибо за ваши отзывы. Нас очень греют Ваши радушные слова, и от них мы работаем еще лучше.</span>
            </p>
        </div>
        <hr>
    </div>
</section>

<div class="form_block">
    <div class="container">
        <div class="row">
            <div class="grid_12">
                <h2>Оставьте отзыв о нашем кафе</h2>
                <form class="myForm" action="/contacts/send.htm" method="POST">
                    <input type="text" name="name" placeholder="Имя:" value=""/>
                    <input type="email" name="email" placeholder="Почта" value=""/>
                    <input type="text" name="phone" placeholder="Телефон: +380919096699:" value=""/>
                    <textarea name="message" placeholder="Сообщение"></textarea>
                    <input type="submit" data-type="submit" class="btn"/>
                </form>
            </div>
        </div>
    </div>
</div>
<!--==============================
<jsp:include page="includes/footer.jsp" flush="true"/>
