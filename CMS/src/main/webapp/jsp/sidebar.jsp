
<input type="hidden" id="sidebar-count">
<div class="col-sm-2 sidebar-offcanvas-left" id="sidebarLeft">
    <div class="well sidebar-nav" role="navigation">
        <h4>User</h4>
        <div class="nav grid span8" id="default-sidebar-list">

            <div class="well-sm span2">
                <a href="/CMS/home" id="position1" value="1">Home</a>
            </div>
            <div class="well-sm span2">
                <a href="/CMS/blog" id="position2" value="2">Blog</a>
            </div>
            <div class="well-sm span4">
                <a href="/CMS/articles" id="position3" value="3">Articles</a>
            </div>
        </div>
        <h4>Blog Page</h4>
        <div class="nav grid span8" id="custom-sidebar-list"></div>
        <h4>Admin</h4>
        <div class="nav grid span8" id="admin-sidebar-list">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="well-sm span2">
                    <a href="/CMS/admin" id="adminPosition1" value="1">Admin</a>
                </div>
            </sec:authorize>

            <sec:authorize access="hasRole('ROLE_EDITOR')">
                <div class="well-sm span2">
                    <a href="/CMS/tinymce" id="adminPosition2" value="2">Create Blog Entry</a>
                </div>
                <div class="well-sm span2">
                    <a href="/CMS/pageTinyMCE" id="adminPosition3" value="3">Create Article</a>
                </div>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <div class="well-sm span2">
                    <a href="${pageContext.request.contextPath}/j_spring_security_logout" id="adminPosition4" value="4">
                        Log Out
                    </a>
                </div>
            </sec:authorize>

            <sec:authorize access="isAnonymous()">
                <div class="well-sm span4">
                    <a href="/CMS/login" id="adminPosition5" value="5">Login</a>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>

