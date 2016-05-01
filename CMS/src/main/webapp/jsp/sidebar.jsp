
<input type="hidden" id="sidebar-count">
<div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebarLeft" role="navigation">
    <div class="well sidebar-nav">
        <ul class="nav" id="default-sidebar-list">
            <li>
                <a href="/CMS/home">Home</a>
            </li>
            <li>
                <a href="/CMS/blog">Blog</a>
            </li>
            <li>
                <a href="/CMS/articles">Articles</a>
            </li>
        </ul>
        <ul class="nav" id="custom-sidebar-list">

        </ul>      
        <ul class="nav" id="admin-sidebar-list">

            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li>
                    <a href="/CMS/admin">Admin</a>
                </li>
            </sec:authorize>
            
            <sec:authorize access="hasRole('ROLE_EDITOR')">
                <li>
                    <a href="/CMS/tinymce">Create Blog Entry</a>
                </li>
                <li>
                    <a href="/CMS/pageTinyMCE">Create Article</a>
                </li>
            </sec:authorize>
            
            <sec:authorize access="isAuthenticated()">
            <li>
                <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                    Log Out
                </a>
            </li>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <li>
                    <a href="/CMS/login">Login</a>
                </li>

            </sec:authorize>

        </ul>



    </div>
</div>

