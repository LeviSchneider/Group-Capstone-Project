
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: 'textarea',
                plugins: "image imagetools contextmenu advlist link colorpicker paste table textcolor ",
                menubar: "insert",
                toolbar: "image styleselect fontsizeselect hr link preview",
                contextmenu: "link image inserttable | cell row column deletetable",
                inline: false
            });</script>

        <form action="render" method="POST">
            <textarea id="htmlOutput"><b>Post rich content here.</b></textarea>
            <button id="tiny-submit"type="submit">Render this content</button>
        </form>
