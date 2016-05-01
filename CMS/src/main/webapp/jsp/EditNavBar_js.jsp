
<script>
    $(function () {
        $(".grid").sortable({
            tolerance: 'pointer',
            revert: 'invalid',
            placeholder: 'span2 well placeholder tile',
            forceHelperSize: true
        });
    });

    $(function () {
        $("#sortable").sortable({
            update: function (event, ui) {
                var data = $(this).sortable('serialize');

                $(document).on("click", "button", function () { //that doesn't work
                    $.ajax({
                        data: data,
                        type: 'POST',
                        url: '/staticPageOrder/' + data
                    });
                });
            }
        }).disableSelection();
        $('button').on('click', function () {
            var r = $("#sortable").sortable("toArray");
            var a = $("#sortable").sortable("serialize", {
                attribute: "id"
            });
            console.log(r, a);
        });
    });
</script>
