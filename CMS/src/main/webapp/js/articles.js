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
                    .append($('<div>')
                            .addClass("panel panel-default")
                            .append('<div>')
                            .addClass("panel-body")
                            .append('<span>')

                            .append($('<a>')
                                    .attr({
                                        'href': '/CMS/pagelink/' + staticPage.titleNumber
                                    }).text(staticPage.title))

                            .append($('<a href="/CMS/pageTinyMCE/' + staticPage.pageId + '"><button type="button" class="btn btn-default btn-xs">'
                                    + '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></a>'))

                            .append($('<a>')
                                    .attr({
                                        'onclick': 'deleteStaticPage(' + staticPage.pageId + ')'
                                    })
                                    .text(' |Delete'))

                            );
        });

    });

}


function clearStaticPageTable() {
    $('#staticPageContentRows').empty();
}

