<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content gallery pad1">
    <div class="container">

        <a class="bq_title" style="font-size: x-large; padding-left: 30px;">
            Страви за категорією:
            <br><br>
        </a>

        <div class="row">
            <%int i = 0;%>
            <c:forEach var="items" items="${dish}">
                <div class="col-md-4">
                    <div class="gall_block">
                        <div class="maxheight">
                            <a href="/dishImage/${items.id}.htm" class="gall_item">
                                <img src="/dishImage/${items.id}.htm" alt="">
                            </a>
                            <div class="gall_bot">
                                <div class="text1"><a href="#">${items.name}</a></div>
                                    ${items.description}
                                <br>
                                Вага: <span class="color1 fw">${items.amount} гр </span> <br>
                                <c:choose>
                                    <c:when test="${items.discount != 0}">
                                        Ціна: <span class="color1 fw"><s>${items.price}₴</s></span>
                                        <span class="color1 fw"> ${items.price-items.price*items.discount/100} ₴</span>
                                        <input type="hidden" value="${items.price-items.price*items.discount/100}"
                                               name="price">
                                    </c:when>
                                    <c:otherwise>
                                        Ціна: <span class="color1 fw"> ${items.price} ₴</span>
                                        <input type="hidden" value="${items.price}" name="price">
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${items.needCook eq '+'}">
                                    <hr>
                                    Страва видається не відразу. Швидкість залежить від завантаження кафе.
                                </c:if>
                                <c:if test="${items.needCook eq '-'}">
                                    <hr>
                                    Страва не потребує приготування. Видається відразу.
                                </c:if>
                                <br>
                            </div>

                        </div>
                    </div>
                </div>
                <%i++;%>
                <%if (i == 3) {%>
                <div class="clear sep__1"></div>
                <% i = 0;
                }%>
            </c:forEach>
        </div>
    </div>
</section>
<jsp:include page="includes/footer.jsp" flush="true"/>
