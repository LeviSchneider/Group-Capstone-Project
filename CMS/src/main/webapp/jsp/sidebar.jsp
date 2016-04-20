<%-- 
    Document   : sidebar
    Created on : Apr 20, 2016, 11:01:11 AM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <li>
        <a href="#">Home</a>
    </li>
    <li>
        <a href="#">Blog</a>
    </li>
    <li>
        <a href="#">Articles</a>
    </li>
    <li>
        <a href="#">Content-1</a>
    </li>
    <li>
        <a href="#">Content-2</a>
    </li>
    <li>
        <a href="#">Content-3</a>
    </li>
    <li>
        <a href="#">Contact Us</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/tinymce">Edit Page</a>
    </li>
</body>
</html>
