
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

            <div class="row row-offcanvas row-offcanvas-left">
                <div class="row-offcanvas row-offcanvas-right">
                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarLeft" role="navigation">
                        <div class="well sidebar-nav">
                            <ul class="nav">
                                <!-- left sidebar fragment will start here -->
                                <li><a href="#">Home</a></li>
                                <li><a href="#">Blog</a></li>
                                <li><a href="#">Articles</a></li>
                                <li><a href="#">Content-1</a></li>
                                <li><a href="#">Content-2</a></li>
                                <li><a href="#">Content-3</a></li>
                                <li><a href="#">Admin</a></li>
                                <!-- left sidebar fragment should end here -->
                            </ul>
                        </div><!--/.well -->
                    </div><!--/span-->



                    <div class="col-xs-12 col-sm-8">
                        <div class="jumbotron">
                            <h1>I heard you like cheese.</h1>
                            <p>But check out the side-toggling navbars first. #dual-wielding</p>
                        </div>
                        <div class="row">

                            <div id="blog-post-display"></div>
                        </div><!--/row-->
                    </div><!--/.col-xs-12.col-sm-9-->

                    <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarRight" role="navigation">
                        <div class="well sidebar-nav">
                            <div id="tagcloud"></div>
                        </div><!--/.well -->
                    </div><!--/span-->
                </div><!--/row-->
            </div>

            <hr>

            <footer>
                <p>&copy; 2015 Company, Inc.</p>
            </footer>
        </div><!--/.container-->


        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
         <!--   <script src="${pageContext.request.contextPath}/js/${js_page}"></script> -->
      <!--  <script src="${pageContext.request.contextPath}/js/blog.js"></script> -->

        <%@include file="scripts_js.jsp" %>
        <%@include file="footer.jsp" %>

    </div>
</body>
</html>

