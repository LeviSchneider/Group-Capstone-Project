<%-- 
    Document   : scripts_js
    Created on : Apr 6, 2016, 7:35:02 PM
    Author     : apprentice
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<script>

    $(document).ready(function () {
        loadTags();

        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });


        $('#click-me').click(function () {

            [].forEach.call($("*"), function (a) {
                a.style.outline = "1px solid #" + (~~(Math.random() * (1 << 24))).toString(16);
            });
        });
    });

    function loadTags() {
        var tagCloud = $('#tagcloud');
        var tagString = "";
        var counter = 0;
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/tags/10'
        }).success(function (data, status) {
            $.each(data, function (index, tagMap) {
                if (counter === 2)
                {
                    var newKey;
                    for (var key in tagMap) {
                        newKey = key.replace("#", "");
                        if (tagMap[key] <= 3) {
                            tagString += "<a onclick='populatedTagPosts(\""+newKey+"\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        } else if (tagMap[key] <= 7) {
                            tagString += "<a style='font-size:150%' onclick='populatedTagPosts(\""+newKey+"\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        } else {
                            tagString += "<a style='font-size:200%' onclick='populatedTagPosts(\""+newKey+"\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        }
                    }

                    tagCloud.addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append(tagString)
                            .append($('<div>'));
                }
                counter++;
            });
        });
    }
</script>