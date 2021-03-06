<%-- 
    Document   : home
    Created on : Mar 28, 2016, 1:16:09 PM
    Author     : apprentice
--%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Palo Alto CMS</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/cheese.png">
        <link href='https://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
    </head>
    <body>
        <div class="container">
            <div class="row row-offcanvas row-offcanvas-left">
                <div class="row-offcanvas row-offcanvas-right">
                    <!-- it looks strange but these rows need to wrap each other -->
                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarLeft" role="navigation">
                        <div class="well sidebar-nav"  data-spy="affix" >
                            <!-- all content needs to go in the included jsp; don't modify this -->
                            <%@include file="sidebar.jsp"%>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-8">
                        <%@ include file="header.jsp"%>
                        <jsp:include page="${page}.jsp"/>
                        <%@include file="footer.jsp"%>
                    </div>
                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarRight" role="navigation">
                        <div id="right-sidebars"  data-spy="affix" >
                            <div class="well sidebar-nav">
                                <%@include file="tagCloudSidebar.jsp"%>
                            </div>
                            <div class="well sidebar-nav">
                                <%@include file="categorySidebar.jsp"%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--
<div class="row">
    <div class='col-xs-12'>
            <%@include file="footer.jsp"%>
        </div>
    </div> -->
        </div>

        <!--
            <div class="container">
              <div class="row row-offcanvas row-offcanvas-left">
                  <div class="row-offcanvas row-offcanvas-right">
                     <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarLeft" role="navigation">
        -->

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" integrity="sha256-xNjb53/rY+WmG+4L6tTl9m6PpqknWZvRt0rO1SRnJzw=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/${js_page}"></script>
        <%@include file="scripts_js.jsp" %>
    </body>

</html>

