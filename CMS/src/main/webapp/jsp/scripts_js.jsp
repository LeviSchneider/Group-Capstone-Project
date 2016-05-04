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
        loadCategoriesSideBar();
        $('#toggle-left').click(function () {
            if (!$('.row-offcanvas-left').hasClass('active')) {
                $('.row-offcanvas-left').addClass('active');
                $('.row-offcanvas-right').removeClass('active');
                $('#toggle-left').addClass('active');
            } else {
                $('.row-offcanvas-left').removeClass('active');
            }
        });
        $('#toggle-right').click(function () {
            if (!$('.row-offcanvas-right').hasClass('active')) {
                $('.row-offcanvas-right').addClass('active');
                $('.row-offcanvas-left').removeClass('active');
                $('#toggle-right').addClass('active');
            } else {
                $('.row-offcanvas-right').removeClass('active');
            }
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
            url: '${pageContext.request.contextPath}/tags/20'
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
                        } else if (tagMap[key] <= 7) {
                            tagString += "<a style='font-size:121%' onclick='populatedTagPosts(\"" + newKey + "\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        } else {
                            tagString += "<a style='font-size:196%' onclick='populatedTagPosts(\"" + newKey + "\")'>";
                            tagString += " " + key;
                            tagString += "</a>";
                        }
                    }

                    tagCloud.append(tagString);
                }
                counter++;
            });
        });
    }

    function loadCategoriesSideBar() {
        var categoryContent = $('#categories');
        var categoryString = "";
        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/categories'
        }).success(function (data, status) {
            $.each(data, function (index, category) {

                categoryContent.append($('<span>')
                        .append($('<a>')
                                .attr({
                                    'href': '/CMS/staticPages/' + category.categoryId + '/'
                                })
                                .html(category.categoryName))
                        .append($('<br/>')
                                ));

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
            });
        });
    }

    function loadSideBarItems() {

        $('#custom-sidebar-list').empty();
        var sideBar = $('#custom-sidebar-list');
        var row = "";
        var counter = 0;
        $.ajax({
            type: 'GET',
            url: '/CMS/sideBarLinks'
        }).success(function (data, status) {
            $('#custom-sidebar-list').val(data.length);
            $.each(data, function (index, sideBar) {

                if (counter !== sideBar.length) {
                    row += "<div class='well-sm span2'>";
                    row += "<a href='/CMS/pagelink/" + sideBar.sideBarLinkUrl + "'>" + sideBar.sideBarLinkName + "</a>";
                    row += "<input id='sideBarPosition" + sideBar.sideBarLinkUrl + "' type='hidden' value='" + sideBar.sideBarLinkUrl + "'/></div>";
                } else if (counter === sideBar.length) {
                    row += "<div class='well-sm span4'>";
                    row += "<a href='/CMS/pagelink/" + sideBar.sideBarLinkUrl + "'>" + sideBar.sideBarLinkName + "</a>";
                    row += "<input id='sideBarPosition" + sideBar.sideBarLinkUrl + "' type='hidden' value='" + sideBar.sideBarLinkUrl + "'/></div>";
                }
                counter++;
            });
            sideBar.append(row);
        });
    }
</script>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <script>
        var sideBarPositionList = [];
        var sideBarUrlList = [];



        $(function () {
            $(".droppable").sortable({
                tolerance: 'pointer',
                revert: 'invalid',
                placeholder: 'span2 well placeholder tile',
                forceHelperSize: true,
                update: function (event, ui) {
                    Dropped();
                }
            });
        });

        function Dropped(event, ui) {
            sideBarPositionList = [];
            sideBarUrlList = [];
            var counter = 1;
            $(".droppable").children().each(function () {
                //var p = $(this).position();
                sideBarUrlList[sideBarUrlList.length] = $(this).find('input').val();
            });
            for (var i = 0; i < sideBarUrlList.length; i++)
            {
                $.ajax({
                    type: 'PUT',
                    url: '/CMS/staticPage/' + counter + '/' + sideBarUrlList[i]
                });
                counter++;
            }
            loadSideBarItems();
        }
    </script>
</sec:authorize>