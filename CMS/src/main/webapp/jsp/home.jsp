<%-- 
    Document   : home
    Created on : Mar 28, 2016, 1:16:09 PM
    Author     : apprentice
--%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Index Page</title>

        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">

    </head>
    <body>


        <div class="container-fluid">

            <div class="row row-offcanvas row-offcanvas-left">

                <div class="row-offcanvas row-offcanvas-right">

                    <%@include file="sidebar.jsp" %>

                    <div class="col-xs-12 col-sm-8">

                        <%@ include file="header.jsp" %>
                        <jsp:include page="${page}.jsp"/>

                    </div>

                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarRight" role="navigation">
                        <div class="well sidebar-nav" >
                            <%@include file="tagCloudSidebar.jsp" %>
                        </div>
                    </div>

                </div>

            </div>

            <div class="row">
                <%@include file="footer.jsp" %>
            </div>


        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" integrity="sha256-xNjb53/rY+WmG+4L6tTl9m6PpqknWZvRt0rO1SRnJzw=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/${js_page}"></script>

        <%@include file="scripts_js.jsp" %>




    </body>

</html>

