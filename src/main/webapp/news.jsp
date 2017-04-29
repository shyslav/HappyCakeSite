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
            <div class="col-md-7">
                <c:forEach var="items" items="${news}">
                    <br>
                    <div class="box">
                        <div class="blog" style="padding-left: 10px;">
                            <div class="blog_title"><a href="#">${items.name} </a></div>
                            <img src="${items.imageLink}" alt="">
                            <p>${items.text} </p>
                            <table>
                                <tbody>
                                <tr>
                                    <td>
                                        <time datetime="${items.date}">
                                            <span class="fa fa-calendar"></span>
                                                ${items.date}
                                        </time>
                                    </td>
                                    <td><a href="#">
                                        <div class="fa fa-user"></div>
                                        HappyCake</a></td>
                                </tr>
                                <tr>
                                    <td colspan="1">
                                        <div class="fa fa-tag"></div>
                                            ${items.tags}</td>
                                    <td colspan="2"><a href="/news/like/${items.id}.htm">
                                        <div class="fa fa-thumbs-up"></div>
                                            ${items.view}</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <br>
                </c:forEach>

            </div>

            <div class="col-md-4">
                <%--Теги--%>
                <h2>Теги</h2>
                <ul class="list">

                    <c:forEach var="items" items="${newsAll}">
                        <li><a href="/news/${items.tags}.htm">${items.tags}</a></li>
                    </c:forEach>
                    <li><a href="/news.htm">Все теги</a></li>

                </ul>
                <%--Популярные новости--%>
                <h2>Найпопулярніші новини</h2>
                <c:forEach var="items" items="${popularNews}">
                    <%--<div class="box" style="padding-left: 10px;">--%>

                    <div class="block3">
                        <img src="${items.imageLink}" alt="" class="img_inner noresize fleft">
                        <div class="extra_wrapper">
                            <div class="text1 color1">
                                <time datetime="${items.date}">${items.date}</time>
                                <a href="/news/fullnews/${items.id}.htm">${items.name} </a>
                            </div>
                        </div>
                    </div>
                    <%--</div>--%>
                    <%--<br>--%>
                </c:forEach>

            </div>
        </div>
    </div>
</section>
<!--==============================
<jsp:include page="includes/footer.jsp" flush="true"/>
