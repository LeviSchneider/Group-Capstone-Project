<%@page import="java.util.Enumeration"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <title>Admin</title>
    </head>
    <body>
    <center>
        <h1>Admin Page</h1>
        <h5>Welcome Admin</h5>
    </center>



<!--    <sec:authorize access="hasRole('ROLE_ADMIN')">

        <div id="usersCollapsible" data-collapse="accordion persist">
            <h3>Users</h3>

            <table id="userTable" class="table table-hover">
                <tr>
                    <th width="50%">Username</th>
                    <th width="50%">Role</th>
                </tr>
                <tbody id="userContentRows"></tbody>
            </table>

        </div>
    </sec:authorize>-->

    <div id="addCategoryCollapsible" data-collapse="accordion persist">
        <h3>Add Category</h3>

        <form class="navbar-form navbar-left">
            <div class="form-group">
                <input id="add-category" type="text" class="form-control" placeholder="Add Category Name">
            </div>
            <button onclick="addCategoryButton()" class="btn btn-primary">Submit</button>
        </form>

    </div>


    <sec:authorize access="hasRole('ROLE_ADMIN')">

        <div id="categoryCollapsible" data-collapse="accordion persist">
            <h3>Categories</h3>

            <table id="categoryTable" class="table table-hover">
                <tr>
                    <th width="30%">Category Name</th>
                    <th width="10%"></th>
                </tr>
                <tbody id="contentRows"></tbody>
            </table>
        </div>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">

        <div id="tagCollapsible" data-collapse="accordion persist">
            <h3>HashTags</h3>

            <table id="categoryTable" class="table table-hover">
                <tr>
                    <th width="30%">Tag Name</th>
                    <th width="10%"></th>
                </tr>
                <tbody id="tag-display"></tbody>
            </table>
        </div>
    </sec:authorize>


    <div id="unPublishedBlogPostsCollapsible" data-collapse="accordion persist">

        <h3>Unpublished Blog Posts</h3>
        <table id="unpublishedBlogPostsTable" class="table table-hover">
            <tr>
                <th width="100%">Blog Post Title</th>

            </tr>
            <tbody id="unpublished-blog-post-display"></tbody>

        </table>        

    </div>

    <div id="blogPostsCollapsible" data-collapse="accordion persist">

        <h3>All Blog Posts - All Statuses</h3>
        <table id="blogPostsTable" class="table table-hover">
            <tr>
                <th width="100%">Blog Post Title</th>

            </tr>
            <tbody id="blog-post-display"></tbody>

        </table>        

    </div>

    <div id="unPublishedStaticPagesCollapsible" data-collapse="accordion persist">
        <h3>Unpublished Static Pages</h3>
        <table id="unpublishedStaticPageTable" class="table table-hover" >
            <tr>
                <th width="100%">Static Page Name</th>


            </tr>
            <tbody id="unpublished-static-page-display"></tbody>

        </table>    
    </div>

    <div id="staticPagesCollapsible" data-collapse="accordion persist">
        <h3>All Static Pages - All Statuses</h3>
        <table id="staticPageTable" class="table table-hover" >
            <tr>
                <th width="60%">Static Page Name</th>
                <th width="10%"></th>
                <th width="30%">Category Name</th>

            </tr>
            <tbody id="staticPageContentRows"></tbody>

        </table>    
    </div>
    <!--
        <div id="tagCloudCollapsible" data-collapse="accordion persist">
            <h3>HashTags</h3>
    
            <table id="tagTable" class="table table-hover">
                <tr>
                    <th width="30%">Tag Name</th>
                    <th width="10%"></th>
                </tr>
                <tbody id="tagContentRows"></tbody>
            </table>
        </div>-->

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.collapse.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.collapse_cookie_storage.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.collapse_storage.js"></script>

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
                    alert(sideBarUrlList);
                    for (var i = 0; i < sideBarUrlList.length; i++)
                    {
                        alert(counter + ' ' + sideBarUrlList[i]);
                        $.ajax({
                            type: 'PUT',
                            url: '/CMS/staticPage/' + counter + '/' + sideBarUrlList[i]
                        });
                        counter++;
                    }
                    alert('Now it should have saved');
                    loadSideBarItems();
                }
    </script>
    </sec:authorize>
</body>
</html>
