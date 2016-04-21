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
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Simple Sidebar - Start Bootstrap Template</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/blog.css" rel="stylesheet">
        <style>
            #wrapper {
                padding-left: 0;
                -webkit-transition: all 0.5s ease;
                -moz-transition: all 0.5s ease;
                -o-transition: all 0.5s ease;
                transition: all 0.5s ease;
                overflow: hidden;
            }

            #wrapper.toggled {
                padding-left: 250px;
                overflow: scroll;
            }

            #sidebar-wrapper {
                z-index: 1000;
                position: absolute; 
                left: 250px;
                width: 0;
                height: 100%;
                margin-left: -250px;
                overflow-y: auto;
                background: #000;
                -webkit-transition: all 0.5s ease;
                -moz-transition: all 0.5s ease;
                -o-transition: all 0.5s ease;
                transition: all 0.5s ease;
            }

            #sidebar-wrapper-right {
                z-index: 1000;
                position: absolute; 
                right: 500px;
                width: 0;
                height: 100%;
                margin-right: 250px;
                overflow-y: auto;
                background: #000;
                -webkit-transition: all 0.5s ease;
                -moz-transition: all 0.5s ease;
                -o-transition: all 0.5s ease;
                transition: all 0.5s ease;
            }
            #wrapper.toggled #sidebar-wrapper #sidebar-wrapper-left {
                width: 250px;
            }

            #page-content-wrapper {
                position: absolute;
                padding: 15px;
                width: 100%;  
                overflow-x: hidden; 
            }
            .xyz{
                min-width: 360px;
            }
            #wrapper.toggled #page-content-wrapper {
                position: relative;
                margin-right: 0px; 
            }
            .fixed-brand{
                width: auto;
            }

            /* Sidebar Styles*/

            .sidebar-nav {
                position: absolute;
                top: 0;
                width: 250px;
                margin: 0;
                padding: 0;
                list-style: none;
                margin-top: 2px;
            }

            .sidebar-nav li {
                text-indent: 15px;
                line-height: 40px;
            }

            .sidebar-nav li a {
                display: block;
                text-decoration: none;
                color: #999999;
            }

            .sidebar-nav li a:hover {
                text-decoration: none;
                color: #fff;
                background: rgba(255,255,255,0.2);
                border-left: red 2px solid;
            }

            .sidebar-nav li a:active,
            .sidebar-nav li a:focus {
                text-decoration: none;
            }

            .sidebar-nav > .sidebar-brand {
                height: 65px;
                font-size: 18px;
                line-height: 60px;
            }

            .sidebar-nav > .sidebar-brand a {
                color: #999999;
            }

            .sidebar-nav > .sidebar-brand a:hover {
                color: #fff;
                background: none;
            }
            .no-margin{
                margin:0;
            }

            @media(min-width:768px) {
                #wrapper {
                    padding-left: 250px;
                }
                .fixed-brand{
                    width: 250px;
                }
                #wrapper.toggled {
                    padding-left: 0;
                }

                #sidebar-wrapper #sidebar-wrapper-right {
                    width: 250px;
                }

                #wrapper.toggled #sidebar-wrapper #sidebar-wrapper-right {
                    width: 250px;
                }
                #wrapper.toggled-2 #sidebar-wrapper #sidebar-wrapper-right {
                    width: 50px;
                }
                #wrapper.toggled-2 #sidebar-wrapper:hover #sidebar-wrapper-right:hover {
                    width: 250px;
                }


                #page-content-wrapper {
                    padding: 20px;
                    position: relative;
                    -webkit-transition: all 0.5s ease;
                    -moz-transition: all 0.5s ease;
                    -o-transition: all 0.5s ease;
                    transition: all 0.5s ease;
                }

                #wrapper.toggled #page-content-wrapper {
                    position: relative;
                    margin-right: 0;
                    padding-left: 250px;
                }
                #wrapper.toggled-2 #page-content-wrapper {
                    position: relative;
                    margin-right: 0;
                    margin-left: -200px;
                    -webkit-transition: all 0.5s ease;
                    -moz-transition: all 0.5s ease;
                    -o-transition: all 0.5s ease;
                    transition: all 0.5s ease;
                    width: auto;

                }
            }

        </style>
    </head>

    <body>

        <div id="wrapper">
            <div id="sidebar-wrapper">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="#">
                            Start Bootstrap
                        </a>
                    </li>
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                    <li>
                        <a href="#">Shortcuts</a>
                    </li>
                    <li>
                        <a href="#">Overview</a>
                    </li>
                    <li>
                        <a href="#">Events</a>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
        <div id="wrapper">
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1>Simple Sidebar</h1>
                            <p>This template has a responsive menu toggling system. The menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will appear/disappear. On small screens, the page content will be pushed off canvas.</p>
                            <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>.</p>
                            <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sidebar-wrapper-right">
                <ul class="sidebar-nav">
                    <li class="sidebar-brand">
                        <a href="#">
                            Tag menu
                        </a>
                    </li>
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                    <li>
                        <a href="#">Shortcuts</a>
                    </li>
                    <li>
                        <a href="#">Overview</a>
                    </li>
                    <li>
                        <a href="#">Events</a>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            $("#menu-toggle").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled");
            });
            $("#menu-toggle-2").click(function (e) {
                e.preventDefault();
                $("#wrapper").toggleClass("toggled-2");
                $('#menu ul').hide();
            });

            function initMenu() {
                $('#menu ul').hide();
                $('#menu ul').children('.current').parent().show();
                //$('#menu ul:first').show();
                $('#menu li a').click(
                        function () {
                            var checkElement = $(this).next();
                            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                                return false;
                            }
                            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                                $('#menu ul:visible').slideUp('normal');
                                checkElement.slideDown('normal');
                                return false;
                            }
                        }
                );
            }
            $(document).ready(function () {
                initMenu();
            });
        </script>

    </body>

</html>

