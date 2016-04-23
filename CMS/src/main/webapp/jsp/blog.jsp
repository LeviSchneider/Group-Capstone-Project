
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

        <style>
            html,
            body {
                overflow-x: hidden; /* Prevent scroll on narrow devices */
            }

            body {
                padding-top: 70px;
            }
            footer {
                padding-left: 15px;
                padding-right: 15px;
            }

            /*
             * Off Canvas
             * --------------------------------------------------
             */
            @media screen and (max-width: 767px) {
                .row-offcanvas {
                    position: relative;
                    -webkit-transition: all .25s ease-out;
                    -o-transition: all .25s ease-out;
                    -moz-transition: all .25s ease-out;
                    transition: all .25s ease-out;
                }

                .row-offcanvas-left
                #sidebarLeft {
                    left: -40%;
                }

                .row-offcanvas-left.active {
                    left: 40%;
                }

                .row-offcanvas-right 
                #sidebarRight {
                    right: -40%;
                }

                .row-offcanvas-right.active {
                    right: 40%;
                }

                .sidebar-offcanvas {
                    position: absolute;
                    top: 0;
                    width: 40%;
                    margin-left: 10px;
                }
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-fixed-top navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <button style="color: white;" type="button" class="navbar-toggle collapsed" data-toggle="offcanvasright">
                        <span class="sr-only">Toggle right sidebar</span>
                        <span>#</span>
                    </button>

                    <button style="color: white;" type="button" class="navbar-toggle collapsed" data-toggle="offcanvas">
                        <span class="sr-only">Toggle left sidebar</span>
                        <span>#</span>
                    </button>



                    <a class="navbar-brand" href="#">Palo Alto CMS</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">Camembert</a></li>
                        <li><a href="#about">Cheddar</a></li>
                        <li><a href="#contact">Gruyere</a></li>
                    </ul>
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </nav><!-- /.navbar -->

        <div class="container">

            

            <hr>
            
            <div class="container-fluid">
                <div class="col-md-1">
                <div class="sidebar-nav-fixed pull-right affix">

                    <ul class="nav ">
                        <br/>

                        <%@include file="tagCloudSidebar.jsp" %>

                    </ul>

                </div> 


            </div>
            </div>
            <footer>
                <p>&copy; 2015 Company, Inc.</p>
            </footer>
            
        </div><!--/.container-->


        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>


        <%@include file="footer.jsp" %>
        <%@include file="scripts_js.jsp" %>

    </div>
</body>
</html>

