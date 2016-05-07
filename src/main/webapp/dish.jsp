<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:include page="includes/header.jsp" flush="true"/>
<!--=====================
Content
======================-->
<section class="content gallery pad1">
    <div class="container">
        <div class="row">
            <%int i = 0;%>
            <c:forEach var="items" items="${dish}">
                <div class="grid_4">
                    <div class="gall_block">
                        <div class="maxheight">
                            <a href="${items.image}" class="gall_item"><img src="${items.image}" alt=""></a>
                            <div class="gall_bot">
                                <div class="text1"><a href="#">${items.name}</a></div>
                                    ${items.description}
                                <br>
                                    Вес: <span class="color1 fw">${items.amount} грамм </span> <br>
                                <c:choose>
                                    <c:when test="${not empty items.sell }">
                                        Цена: <span class="color1 fw"><s>${items.price}₴</s></span>
                                        <span class="color1 fw"> ${items.price-items.price*items.sell/100} ₴</span>
                                        <input type="hidden" value="${items.price-items.price*items.sell/100}" name = "price">
                                    </c:when>
                                    <c:otherwise>
                                        Цена: <span class="color1 fw"> ${items.price} ₴</span>
                                        <input type="hidden" value="${items.price}" name="price">
                                    </c:otherwise>
                                </c:choose>
                                    <c:if test="${items.readyORnot eq '+'}">
                                        <hr>
                                        Блюдо выдается не сразу, скорость выполнения зависит от загруженности кафе.
                                    </c:if>
                                    <c:if test="${items.readyORnot eq '-'}">
                                        <hr>
                                        Блюдо не требует приготовления, выдается сразу при оплате.
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
