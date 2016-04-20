<%-- 
    Document   : scripts_js
    Created on : Apr 6, 2016, 7:35:02 PM
    Author     : apprentice
--%>
<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<script>

    $(document).ready(function () {

        $('#click-me').click(function () {

            [].forEach.call($("*"), function (a) {
                a.style.outline = "1px solid #" + (~~(Math.random() * (1 << 24))).toString(16);
            });
        });
    });


</script>