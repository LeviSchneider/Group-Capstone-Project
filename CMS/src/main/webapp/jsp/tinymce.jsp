<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script type="text/javascript">
    tinymce.init({
        selector: 'textarea',
        plugins: ["image imagetools contextmenu advlist link colorpicker paste table textcolor ",
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table contextmenu paste youtube",
            "autoresize"],
        autoresize_bottom_margin: 50,
        external_plugins: {"youtube": "${pageContext.request.contextPath}/js/tiny_mce/plugins/youtube/plugin.min.js",
            "respanner": "${pageContext.request.contextPath}/js/tiny_mce/plugins/respanner/respanner.js"},
        // menubar: "insert",
        //toolbar: "image styleselect fontsizeselect hr link preview",
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | youtube | respanner",
        contextmenu: "link image inserttable | cell row column deletetable",
        inline: false
    });
</script>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="text-center">New blog post</h3>
    </div>
    <div class="panel-body">
        <div class="container-fluid">
            <form class="form-horizontal">
                <input type="hidden" id="post-to-edit-id" value="${editBlogPostId}"/>
                <div class="form-group">
                    <label class="control-label">Start Date:</label>
                    <input class="form-control" type="date" id="start-date"/>
                </div>

                <div class="form-group">
                    <label class="control-label">End Date:</label>
                    <input class="form-control" type="date" id="end-date"/>
                </div>

                <!-- end of second form row -->

                <div class="form-group">
                    <label class="control-label">Title:</label>
                    <input class="form-control" type="text" id="post-title" placeholder="Title"/>
                </div>

                <div class="form-group">
                    <textarea id="htmlOutput" class="form-control"></textarea>
                </div>


                <div class="form-inline">
                    <div class="form-group">
                        <label for="post-status" class="control-label">Status</label>
                        <select class="form-control" id="post-status" name="post-status">
                            <option value="DRAFT">DRAFT</option>
                            <option value="READY_FOR_APPROVAL">READY_FOR_APPROVAL</option>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <option value="APPROVED">APPROVED</option>
                                <option value="UNPUBLISHED">UNPUBLISHED</option>
                                <option value="PUBLISHED">PUBLISHED</option>
                            </sec:authorize>
                        </select>
                        <button class="btn btn-default" id="tiny-save" type="button">Save</button>
                        <input type="hidden" id="tiny-blogpost-id"/>
                        <button class="btn btn-success" id="tiny-publish" type="button">Publish</button>
                    </div>
                </div>
            </form>
                <br/>
            <span id="last-saved-field" class="text-center col-md-12"></span>
        </div>
    </div>
</div>