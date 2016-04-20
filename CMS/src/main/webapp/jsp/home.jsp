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

            <div class="row">
                <%@ include file="header.jsp" %>
            </div>
            <div class="row">

                <div class="col-md-1">
                    <div class="sidebar-nav-fixed affix">

                        <ul class="nav ">
                            <%@include file="sidebar.jsp" %>

                        </ul>

                    </div>
                </div>
                <div class="col-md-10">



                    <div class="row">

                        <div class="col-lg-12">        

                            <jsp:include page="${page}.jsp"/>

                        </div>

                    </div>

                </div>
                <div class="col-md-1">
                    <div id="sidebar-nav-fixed pull-right affix">

                        <ul class="nav ">
                            <br/>

                            <%@include file="tagCloudSidebar.jsp" %>

                        </ul>

                    </div> 


                </div>

            </div>



            <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/${js_page}"></script>

            <%@include file="scripts_js.jsp" %>
            <%@include file="footer.jsp" %>

        </div>
    </body>
</html>

