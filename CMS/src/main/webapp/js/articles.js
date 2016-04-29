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
                            .append($('<a onclick="addPageToNavBar(' + staticPage.pageId + ')"><button type="button" class="btn btn-default btn-xs">'
                                    + '<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span></button></a>'))

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

function loadSideBarItems() {

    $('#custom-sidebar-list').empty();
    var sideBar = $('#custom-sidebar-list');

    $.ajax({
        type: 'GET',
        url: '/CMS/sideBarLinks'

    }).success(function (data, status) {
        $('#custom-sidebar-list').val(data.length);
        $.each(data, function (index, sideBarLink) {

            //nextNavBarId++;
            sideBar.append($('<li>')
                    .append('<a href="/CMS/' + sideBarLink.sideBarLinkUrl + '">' + sideBarLink.sideBarLinkName + '</a>')
                    );
        });

    });
}
function addPageToNavBar(pageId) {

    var numberOfLinks = $('#custom-sidebar-list').val();
    numberOfLinks++;
    $.ajax({
        type: 'PUT',
        url: '/CMS/staticPage/' + pageId + '/' + numberOfLinks + '/'

    }).success(function (data, status) {

        loadSideBarItems();
    });
}
