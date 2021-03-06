<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-4 center-block">
                <div class="banner">
                    <div class="block">
                        <img src="images/main/pin/cof.jpg" alt="">
                        <div class="bann_capt ">
                            <div class="maxheight">
                                <img src="images/icon1.png" alt="">
                                <div class="bann_title">Меню</div>
                                <a href="/menu.htm.htm">Детальніше</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4 center-block">
                <div class="banner">
                    <div class="block">
                        <div class="bann_capt  bn__1">
                            <div class="maxheight">
                                <br>
                                <img src="images/icon_news.png" alt="">
                                <div class="bann_title"> Новини</div>
                                <a href="/news.htm">Детальніше</a>
                            </div>
                        </div>
                        <img src="images/main/pin/buter.jpg" alt="">
                    </div>
                </div>
            </div>

            <div class="col-md-4 center-block">
                <div class="banner">
                    <div class="gall_block">
                        <img src="images/main/pin/bright.jpg" alt="">
                        <div class="bann_capt ">
                            <div class="maxheight">
                                <br>
                                <img src="images/icon_cont.png" alt="">
                                <div class="bann_title"> Контакти</div>
                                <a href="/contacts.htm">Детальніше</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%int i = 1;%>
            <c:forEach var="item" items="${hotPrice}" begin="0" end="2">
                <div class="col-md-4">
                    <div class="block1">
                        <div class="count">Акція</div>
                            ${item.description}
                        <br>
                        Діє до ${item.dateEnd}
                            <%--<a href="#" class="btn">more</a>--%>
                    </div>
                </div>
                <% i++;%>
            </c:forEach>
            <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-6">
                            <h2>Смак із добрим настроєм!</h2>
                            <p>Вітаємо Вас у нашому кафе<strong> Happy Cake</strong></p>
                            <p>Тут Ви завжди зможете смачно поїсти та приємно провести час. У нас затишний інтер'єр та
                                чудові страви. </p><br>

                        </div>
                        <div class="col-lg-6">
                            <h2>На нашому сайті...</h2>
                            Усі страви розділені на категорії. За будь-якою категорією Ви зможете перейти на сторінку
                            страв. Також на нашому сайті наявна можливість залишити відгук або побажання.<br>
                        </div>
                </div>
            </div>
            <div class="col-md-12 text-center">
                <h2>Страви, які варто спробувати</h2>
            </div>
            <%--ouput 3 random dishes--%>
            <c:forEach var="item" items="${randCategory}">
                <div class="gallery">
                    <div class="col-md-4">
                        <a href="/dishImage/${item.id}.htm" class="gall_item text-center" style="background-color: white">
                            <img src="/dishImage/${item.id}.htm" alt="" style="max-width:250px;height: 250px;">
                        </a>
                        <div class="map_block">${item.name}</div>
                        <a href="category/${item.id}.htm" class="link1">
                            <img src="images/icon_more.png" alt="">
                        </a>
                        <div class="clear"></div>
                    </div>
                </div>
            </c:forEach>
            <%--/ouput 3 random dishes--%>
        </div>
    </div>
</section>
<jsp:include page="includes/footer.jsp" flush="true"/>
