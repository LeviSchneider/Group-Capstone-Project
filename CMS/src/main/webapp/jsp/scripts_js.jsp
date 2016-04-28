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
        loadSideBarItems();

        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas').toggleClass('active');
        });
        $('[data-toggle=offcanvas]').click(function () {
            $('.row-offcanvas-left').toggleClass('active');
        });

        $('[data-toggle=offcanvasright]').click(function () {
            $('.row-offcanvas-right').toggleClass('active');
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
                        if (tagMap[key] <= 2) {
                            tagString += "<a style='font-size:75%' onclick='populatedTagPosts(\"" + newKey + "\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        } else if (tagMap[key] <= 3) {
                            tagString += "<a style='font-size:121%' onclick='populatedTagPosts(\"" + newKey + "\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        } else {
                            tagString += "<a style='font-size:196%' onclick='populatedTagPosts(\"" + newKey + "\")'>";
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

    function readMap() {
        $.ajax({
            type: 'GET',
            url: 'tag/3'
        }).success(function (data, tagMap) {
            var tagRanking = [];
            for (var key in tag.rankedTags)
            {
                tagRanking.push(tag.rankedTags[key] + " :" + key);
            }
            $.each(tagRanking, function (index, tag) {
                $.append();
            });
        });
    }


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

    function clearPopulatedPosts() {
        $('#blog-post-display').empty();
    }

    function populatedTagPosts(data) {
        clearPopulatedPosts();
        var blogPanel = $('#blog-post-display');
        $.ajax({
            type: 'GET',
            url: '/CMS/taggedPosts/' + data
        }).success(function (data, status) {
            $.each(data, function (index, blogPostContainer) {

                var tagList = blogPostContainer.tagContainer.tagList;
                var categoryList = blogPostContainer.categoryContainer.categoryList;

                blogPanel
                        .append($('<div>')
                                .addClass("panel panel-default")
                                .append($('<div>')
                                        .addClass('panel-heading')
                                        .append(blogPostContainer.blogPost.title + ' by: Mayor McCheese (' + blogPostContainer.blogPost.dateSubmitted + ')'
                                                + ' (Status: ' + blogPostContainer.blogPost.status + ')'
                                                + '<a href="/CMS/tinymce/' + blogPostContainer.blogPost.postId + '"><button type="button" class="btn btn-default btn-xs">'
                                                + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'
                                                + '<a href="/CMS/link/' + blogPostContainer.blogPost.titleNumber + '"><button type="button" class="btn btn-default btn-xs">'
                                                + '<span class="glyphicon glyphicon-link" aria-hidden="true"></span></button></a>'))
                                .append($('<div>')
                                        .addClass('panel-body')
                                        .append(blogPostContainer.blogPost.postBody))
                                .append($('<div>')
                                        .addClass('panel-footer')
                                        .attr({'id': 'post' + blogPostContainer.blogPost.postId})
                                        ));
                $.each(tagList, function (index, tag) {

                    $('#post' + blogPostContainer.blogPost.postId)
                            .append($('<span>')
                                    .addClass('panel-body-blogtags')
                                    .append(tag + " "));
                });
                $.each(categoryList, function (index, category) {
                    $('#post' + blogPostContainer.blogPost.postId)
                            .append($('<span>')
                                    .addClass('panel-body-blogcategories')
                                    .append("(In category: " + category.categoryName + ")"));
                });
            });
        });
    }
    ;

    function loadSideBarItems() {

        var sideBar = $('#sidebar-list');

        $.ajax({
            type: 'GET',
            url: '/CMS/sideBarLinks'

        }).success(function (data, status) {

            $.each(data, function (index, sideBarLink) {

                
                sideBar.append($('<li>')
                        .append('<a href="/CMS/' + sideBarLink.sideBarLinkUrl + '">' + sideBarLink.sideBarLinkName + '</a>')
                        );
            });

        });

    }
</script>