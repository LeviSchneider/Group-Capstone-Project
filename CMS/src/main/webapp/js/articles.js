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

                            

                            );
        });

    });

}


function clearStaticPageTable() {
    $('#staticPageContentRows').empty();
}

