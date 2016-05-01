
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

            </li>
        </ul>
        <ul class="nav" id="custom-sidebar-list">

        </ul>      
        <ul class="nav" id="admin-sidebar-list">

            <!-- recomment this to lock down the sidebar -->
            <!--  <li>

            </div>
        </div>
        <h4>Blog Page</h4>
        <div class="nav grid span8" id="custom-sidebar-list"></div>
        <h4>Admin</h4>
        <div class="nav grid span8" id="admin-sidebar-list">
                <a href="/CMS/admin">Admin</a>
            </div>
            <div class="well-sm span2">
                <a href="/CMS/tinymce">Create Blog Entry</a>
            </div>
            <div class="well-sm span4">
                <a href="/CMS/pageTinyMCE">Create Article</a>
            </div>
        </div>
            </li>
             -->
            <!-- uncomment this to lock down the sidebar -->
          <!-- 
            <sec:authorize access="hasRole('ROLE_EDITOR')">
                <li>
                    <a href="/CMS/admin">Tools</a>
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
            -->
        </ul>
    </div>
</div>

