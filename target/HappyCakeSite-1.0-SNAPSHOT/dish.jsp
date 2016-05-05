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
                                    Вес: <span class="color1 fw">${items.amount} грам </span> <br>
                                    Цена: <span class="color1 fw"> ${items.price} гривны</span> <br>
                                    <c:if test="${items.readyORnot eq '+'}">
                                        <hr>
                                        Блюдо выдается не сразу, скорость выполнения зависит от загруженности кафе.
                                    </c:if>
                                    <c:if test="${items.readyORnot eq '-'}">
                                        <hr>
                                        Блюдо не требует приготовления, выдается сразу при оплате.
                                    </c:if>
                                <br>
                                <a href="category/${items.id}.htm" class="btn">Подробнее</a>
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
