
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
    <input type="hidden" id="post-to-edit-id" value="${editBlogPostId}"/>

    Start Date: <input type="date" id="start-date" /> End Date: <input type="date" id="end-date"/>
    <br/><input type="text" id="post-title" placeholder="A title is required..."/>
    <textarea id="htmlOutput"></textarea>

    <select id="post-status" name="post-status">
        <option value="DRAFT">DRAFT</option>
        <option value="READY_FOR_APPROVAL">READY_FOR_APPROVAL</option>
        <option value="APPROVED">APPROVED</option>
        <option value="UNPUBLISHED">UNPUBLISHED</option>
        <option value="PUBLISHED">PUBLISHED</option>
    </select>
    <button id="tiny-save" type="button">Save</button>
    <input type="hidden" id="tiny-blogpost-id"/>
    <button id="tiny-publish" type="button">Publish</button>
</form>


