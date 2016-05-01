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
        external_plugins: {"youtube": "${pageContext.request.contextPath}/js/tiny_mce/plugins/youtube/plugin.min.js"},
        // menubar: "insert",
        //toolbar: "image styleselect fontsizeselect hr link preview",
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image| youtube",
        contextmenu: "link image inserttable | cell row column deletetable",
        inline: false
    });

</script>



<form>
    <input type="hidden" id="staticpage-to-edit-id" value="${editStaticPageId}"/>
    <select id="categories" name="categories">
        <option value="-1">Choose Existing Category</option>
    </select>

    <input id="add-category" type="text" placeholder="Add a NEW category...">

    <button onclick="addCategoryButton()" class="btn btn-primary">Add</button>

    Start Date: <input type="date" id="start-date" /> End Date: <input type="date" id="end-date"/>
    <br/><input type="text" id="staticpage-title" placeholder="A title is required..."/>
    <textarea id="htmlOutput"></textarea>

    <select id="staticpage-status" name="staticpage-status">
        <option value="DRAFT">DRAFT</option>
        <option value="READY_FOR_APPROVAL">READY_FOR_APPROVAL</option>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <option value="APPROVED">APPROVED</option>
            <option value="UNPUBLISHED">UNPUBLISHED</option>
            <option value="PUBLISHED">PUBLISHED</option>
        </sec:authorize>
    </select>
    <button id="tiny-save" type="button">Save</button>
    <input type="hidden" id="tiny-staticpage-id"/>
    <button id="tiny-publish" type="button">Publish</button>
</form>


