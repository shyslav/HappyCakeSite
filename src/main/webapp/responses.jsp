<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content">
    <div class="container">
        <div class="col-md-12 text-center">
            <span style="font-size: 18px; font-weight: bold; color: #535151">
                <br>
                Відгуки про нас:
                <br>
            </span>
        </div>
        <div class="row">
            <br>
            <c:forEach var="items" items="${responses}">
                <div class="col-md-6">
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
                    <br>
                </div>
            </c:forEach>
        </div>
        <hr>
        <div class="col-md-12">
            <p class="text-center"><span
                    class="color1 fw">Дякуємо всім за Ваші відгуки! Вони дуже важливі для нас.</span>
            </p>
        </div>
        <hr>
    </div>
</section>

<div class="form_block">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h2>Залишіть відгук про наше кафе</h2>
                <form class="myForm" action="/contacts/send.htm" method="POST">
                    <input type="text" name="name" placeholder="Ім'я:" value=""/>
                    <input type="email" name="email" placeholder="Пошта" value=""/>
                    <input type="text" name="phone" placeholder="Телефон: +380919096699:" value=""/>
                    <textarea name="message" placeholder="Повідомлення"></textarea>
                    <input type="submit" value="Відправити" class="btn"/>
                </form>
            </div>
        </div>
    </div>
</div>
<!--==============================
<jsp:include page="includes/footer.jsp" flush="true"/>
