<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
          Content
======================-->
<section class="content"><div class="ic">More Website Templates @ TemplateMonster.com - July 30, 2014!</div>

    <div class="container">
        <div class="row">
            <div class="grid_4">
                <div class="banner">
                    <div class="gall_block">
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
            <div class="grid_4">
                <div class="banner">
                    <div class="gall_block">
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
            <div class="grid_4">
                <div class="banner ">
                    <div class="gall_block">
                        <img src="images/main/pin/bright.jpg" alt="">
                        <div class="bann_capt  bn__2">
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
            <div class="grid_4">
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
            <%--<div class="grid_4">--%>
                <%--<div class="block1">--%>
                    <%--<div class="count">2.</div>--%>
                    <%--Dasamus at magna non nunc tristique rhoncus. Kliquam nibh ante, egestas id dictum a, commodo. Praesent faucibus malesuada faucibus. Donec laeet metus id laoreet malesuadarem ipsumer--%>
                    <%--<br>--%>
                    <%--<a href="#" class="btn">more</a>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="grid_4">--%>
                <%--<div class="block1">--%>
                    <%--<div class="count">3.</div>--%>
                    <%--Tervamus at magna non nunc tristique rhoncus. Oliquam nibh ante, egestas id dictum a, commodo. Iraesent faucibus malesuada faucibus. Donec laeet etus id laoreet malesuadarem ipsolo--%>
                    <%--<br>--%>
                    <%--<a href="#" class="btn">more</a>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="grid_12">
                <div class="box">
                    <div class="row">
                        <div class="grid_5 preffix_1">
                            <h2>Смак із добрим настроєм!</h2>
                            <p>Вітаємо Вас у нашому кафе<strong> Happy Cake</strong> </p>
                            <p>Тут Ви завжди зможете смачно поїсти та приємно провести час. У нас затишний інтер'єр та чудові страви. </p><br>

                        </div>
                        <div class="grid_5">
                            <h2>На нашому сайті...</h2>
                            Усі страви розділені на категорії. За будь-якою категорією Ви зможете перейти на сторінку страв. Також на нашому сайті наявна можливість залишити відгук або побажання.<br>

                        </div>
                    </div>
                </div>
            </div>
            <div class="grid_12">
                <h2>Страви, які варто спробувати</h2>
            </div>
            <c:forEach var="item" items="${randCategory}">
            <div class="gallery">
                <div class="grid_4"><a href="${item.image}" class="gall_item"><img src="${item.image}" alt=""></a><div class="map_block">${item.name}</div><a href="category/${item.id}.htm" class="link1"><img src="images/icon_more.png" alt=""></a><div class="clear"></div></div>

                <%--<div class="grid_4"><a href="images/big2.jpg" class="gall_item"><img src="images/page1_img5.jpg" alt=""></a><a href="#" class="link1">+</a><div class="clear"></div></div>--%>
                <%--<div class="grid_4"><a href="images/big3.jpg" class="gall_item"><img src="images/page1_img6.jpg" alt=""></a><a href="#" class="link1">+</a><div class="clear"></div></div>--%>
            </div>
            </c:forEach>
            <%--<div class="grid_4">--%>
                <%--<h2>Testimonials</h2>--%>
                <%--<blockquote class="bq1">--%>
                    <%--<img src="images/page1_img7.jpg" alt="" class="img_inner fleft noresize">--%>
                    <%--<div class="extra_wrapper">--%>
                        <%--<div class="bq_title color1">Mark Wood</div>--%>
                        <%--Sivamus at magna non nuncer tristique rhoncus. Aliquame nibh ante, egestas id dictumertolom  commodo. Praesent faucib mal.--%>
                        <%--<a href="#"><span class="fa fa-chevron-right"></span>more testimonials</a>--%>
                    <%--</div>--%>
                <%--</blockquote>--%>
            <%--</div>--%>
            <%--<div class="grid_4">--%>
                <%--<h2>What’s new</h2>--%>
                <%--<div class="block2">--%>
                    <%--<time datetime="2014-01-01">11<br>June</time>--%>
                    <%--<div class="extra_wrapper">--%>
                        <%--<div class="text1"><a href="#">Vivamus at magna non nunc </a></div>Rehoncus. Aliquam nibh antegestas id dictum a, commodo. Praesenterto faucibus malesuada faucibus--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="block2">--%>
                    <%--<time datetime="2014-01-01">15<br>APR</time>--%>
                    <%--<div class="extra_wrapper">--%>
                        <%--<div class="text1"><a href="#">Tivamus at magna non nunc </a></div>Rehoncus. Aliquam nibh antegestas id dictum a, commodo. Praesenterto faucibus malesuada faucibu--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="grid_4">--%>
                <%--<h2>Opening Hours</h2>--%>
                <%--<ul class="shed">--%>
                    <%--<li><span>Breakfast:</span> 8AM - 11AM</li>--%>
                    <%--<li><span>Grill Menu:</span> 12AM - 12PM</li>--%>
                    <%--<li><span>Live Musiс:</span> 8AM - 11AM</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        </div>
    </div>
</section>
<jsp:include page="includes/footer.jsp" flush="true"/>
