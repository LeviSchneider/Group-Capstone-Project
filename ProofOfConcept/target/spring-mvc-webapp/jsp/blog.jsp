<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello Controller Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Blog view</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/tinymce">Editor</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">
                    <p>
                        Editor will go here.
                    </p>
                    <button type="button" action="">Click to grab posts.</button>
                </div>
                <div id="display-blog-posts" class="col-md-6">
                    
                </div>

            </div>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>