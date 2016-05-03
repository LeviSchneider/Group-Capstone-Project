$(document).ready(function () {
    loadStaticPages();
});

function loadStaticPages(data, status) {

    var staticPagePanel = $('#staticPageContentRows');

    clearStaticPageTable();
    $.ajax({
        type: 'GET',
        url: '/CMS/staticPages'

    }).success(function (data, status) {

        $.each(data, function (index, staticPage) {
            staticPagePanel
                    .append($('<tr>')
                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span>')

                                            .append($('<a>')
                                                    .attr({
                                                        'href': '/CMS/pagelink/' + staticPage.titleNumber
                                                    }).text(staticPage.title))
                                            ))
                            .append($('<td>')
                                    .append($('<div>')
                                            .addClass("panel panel-default")
                                            .append('<div>')
                                            .addClass("panel-body")
                                            .append('<span id="category' + staticPage.pageId + '">')

                                            )
                                    ));

            if (staticPage.categoryIdFK) {

                $.ajax({
                    type: 'GET',
                    url: '/CMS/category/' + staticPage.categoryIdFK

                }).success(function (category, status) {


                    $('#category' + staticPage.pageId).text(category.categoryName);

                });

            }
        });

    });

}


function clearStaticPageTable() {
    $('#staticPageContentRows').empty();
}

