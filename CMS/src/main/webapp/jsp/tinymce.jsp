
<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script type="text/javascript">
    tinymce.init({
        selector: 'textarea',
        plugins: ["image imagetools contextmenu advlist link colorpicker paste table textcolor ",
            "advlist autolink lists link image charmap print preview anchor",
            "searchreplace visualblocks code fullscreen",
            "insertdatetime media table contextmenu paste youtube"],
        external_plugins: {"youtube": "${pageContext.request.contextPath}/js/tiny_mce/plugins/youtube/plugin.min.js"},
        // menubar: "insert",
        //toolbar: "image styleselect fontsizeselect hr link preview",
        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image| youtube",
        contextmenu: "link image inserttable | cell row column deletetable",
        inline: false
    });</script>


<form>
    <input type="text" id='csvHashTags'/>
    <select id="categories" name="categories">
        <option value="none">Choose a Category</option>
    </select>
    <textarea id="htmlOutput"><b>Post rich content here.</b></textarea>
    <button id="tiny-submit" type="button">Render this content</button>
</form>
<div id="contentDiv"></div>

