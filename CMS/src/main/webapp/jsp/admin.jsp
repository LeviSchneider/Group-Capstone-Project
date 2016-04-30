<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <title>Category Admin</title>
    </head>
    <body>
    <center>
        <h1>Admin Page</h1>
        <h5>Welcome Admin</h5>
    </center>
    <form class="navbar-form navbar-left">
        <div class="form-group">
            <input id="add-category" type="text" class="form-control" placeholder="Add Category Name">
        </div>
        <button onclick="addCategoryButton()" class="btn btn-primary">Submit</button>
    </form><br/><br/><br/>
    <h3>Existing Categories</h3>
    <table id="categoryTable" class="table table-hover">
        <tr>
            <th width="30%">Category Name</th>
            <th width="10%"></th>
        </tr>
        <tbody id="contentRows"></tbody>
    </table>
    <h3>Blog Posts</h3>
    <table id="blogPostsTable" class="table table-hover">
        <tr>
            <th width="100%">Blog Post Title</th>

        </tr>
        <tbody id="blog-post-display"></tbody>

    </table>        

    <h3>Static Pages</h3>
    <table id="staticPageTable" class="table table-hover">
        <tr>
            <th width="60%">Static Page Name</th>
            <th width="10%"></th>
            <th width="30%">Category Name</th>

        </tr>
        <tbody id="staticPageContentRows"></tbody>

    </table>    


    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
