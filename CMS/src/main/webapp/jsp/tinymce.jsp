<%-- 
    Document   : tinymce
    Created on : 19-Apr-2016, 11:18:14 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sample TinyMCE instance</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: 'textarea',
                plugins: "image imagetools contextmenu advlist link colorpicker paste table textcolor ",
                // menubar: "insert",
                //toolbar: "image styleselect fontsizeselect hr link preview",
                contextmenu: "link image inserttable | cell row column deletetable",
                inline: false
            });</script>
    </head>
    <body>
        <h2>Here it is.</h2>
        <form action="render" method="POST">
            <textarea id="htmlOutput" name="htmlOutput"><b>Post rich content here.</b></textarea>
            <button id="tiny-submit"type="submit">Render this content</button>
        </form>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
