<%--
  Created by IntelliJ IDEA.
  User: Shyshkin Vladyslav
  Date: 28.04.2016
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle}</title>
    <meta charset="utf-8">
    <meta name = "format-detection" content = "telephone=no" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="icon" href="/images/favicon.ico">
    <link rel="shortcut icon" href="/images/favicon.ico" />
    <link rel="stylesheet" href="/css/stuck.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/touchTouch.css">
    <link rel="stylesheet" href="/css/form.css">
    <link rel="stylesheet" href="/css/myForm.css">
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery-migrate-1.1.1.js"></script>
    <script src="/js/script.js"></script>
    <script src="/js/superfish.js"></script>
    <script src="/js/jquery.equalheights.js"></script>
    <script src="/js/jquery.mobilemenu.js"></script>
    <script src="/js/jquery.easing.1.3.js"></script>
    <script src="/js/tmStickUp.js"></script>
    <script src="/js/jquery.ui.totop.js"></script>
    <script src="/js/touchTouch.jquery.js"></script>
    <script src="/js/TMForm.js"></script>
    <script src="/js/modal.js"></script>

    <script>
        $(document).ready(function(){

            $().UItoTop({ easingType: 'easeOutQuart' });
            $('#stuck_container').tmStickUp({});
            $('.gallery .gall_item').touchTouch();

        });
    </script>
    <!--[if lt IE 9]>
    <div style=' clear: both; text-align:center; position: relative;'>
        <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
            <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
        </a>
    </div>
    <script src="/js/hlink rel="stylesheet" media="screen" href="/css/ie.css">
    tml5shiv.js"></script>
    <

    <![endif]-->
</head>

<body class="page1" id="top">
<!--==============================
              header

=================================-->
<header>
    <div class = "hover-image" style="text-align: center;">
        <img src="/images/main/logo_cup5.png" alt="">
    </div>
    <!--==============================
                Stuck menu
    =================================-->
    <section id="stuck_container" >

        <div class="container" >

            <div class="row">
                <div class="grid_12 " >
                    <%--<h1>--%>
                        <%--<a href="index.html">--%>
                            <%--<img src="/images/main/logo_cup4.png" alt="Logo alt">--%>
                        <%--</a>--%>
                    <%--</h1>--%>
                    <div class="navigation" class = "text-center" >
                        <div class style="text-align: left;">
                        <nav>
                            <ul class="sf-menu" style="text-align: left;">
                                <c:forEach var="item" items="${webMenu}">
                                    <li><a href="${item.link}">${item.name}</a></li>
                                </c:forEach>
                                <%--<li class="current"><a href="index.html">home</a></li>--%>
                                <%--<li><a href="index-1.html">menu</a></li>--%>
                                <%--<li><a href="index-2.html">reservation</a></li>--%>
                                <%--<li><a href="index-3.html">blog</a></li>--%>
                                <%--<li><a href="index-4.html">contacts</a></li>--%>
                            </ul>
                        </nav>
                            <div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</header>
<c:if test="${not empty headModal}">
<%--<script>--%>
    <%--alert("<c:out value="${alert}"/>");--%>
<%--</script>--%>
    <jsp:include page="/modal.jsp" flush="true"/>
</c:if>
