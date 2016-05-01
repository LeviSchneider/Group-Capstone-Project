<input type="hidden" id="sidebar-count">
<div class="col-sm-2 sidebar-offcanvas-left" id="sidebarLeft">
    <div class="well sidebar-nav" role="navigation">
        <h4>User</h4>
        <div class="nav grid span8" id="default-sidebar-list">

            <div class="well-sm span2">
                <a href="/CMS/home">Home</a>
            </div>
            <div class="well-sm span2">
                <a href="/CMS/blog">Blog</a>
            </div>
            <div class="well-sm span4">
                <a href="/CMS/articles">Articles</a>
            </div>
        </div>
        <h4>Blog Page</h4>
        <div class="nav grid droppable span8" id="custom-sidebar-list"></div>
        <h4>Admin</h4>
        <div class="nav grid span8" id="admin-sidebar-list">
            <sec:authorize access="hasRole('ROLE_EDITOR')">
                <div class="well-sm span2">
                    <a href="/CMS/admin">Tools</a>
                </div>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_EDITOR')">
                <div class="well-sm span2">
                    <a href="/CMS/tinymce">Create Blog Entry</a>
                </div>
                <div class="well-sm span2">
                    <a href="/CMS/pageTinyMCE">Create Article</a>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="well-sm span2">
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout">
                        Log Out
                    </a>
                </div>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <div class="well-sm span4">
                    <a href="/CMS/login">Login</a>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>